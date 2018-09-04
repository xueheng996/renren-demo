package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.Constant;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {

    @Autowired
    private SysDeptService sysDeptService;


    @RequestMapping("/list")
    @ResponseBody
    public List<SysDeptEntity> list(@RequestParam Map<String, Object> params) {
        logger.info("params=" + params);
        List<SysDeptEntity> deptEntityList = sysDeptService.queryList(new HashMap<String, Object>());

        return deptEntityList;
    }

    /**
     * 选择部门（添加、修改菜单）
     */
    @RequestMapping("/select")
    @ResponseBody
    public R select(){
        List<SysDeptEntity> deptList=sysDeptService.queryList(new HashMap<String, Object>());

        //添加一级部门
        if(getUserId()==Constant.SUPER_ADMIN){
            SysDeptEntity root=new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }
        return R.ok().put("deptList",deptList);
    }
    /**
     * 上级部门Id（管理员则为0）
     */
    @RequestMapping("/info")
    @ResponseBody
    public R info(){
        long deptId=0;
        Long userId;
        //userId=getUserId();
        userId=Long.valueOf(1);
        if(userId!=Constant.SUPER_ADMIN){
            List<SysDeptEntity> deptList=sysDeptService.queryList(new HashMap<String, Object>());
            Long parentId=null;
            for (SysDeptEntity sysDeptEntity:deptList){
                if(parentId==null){
                    parentId=sysDeptEntity.getParentId();
                    continue;
                }
                if(parentId>sysDeptEntity.getParentId().longValue()){
                    parentId=sysDeptEntity.getParentId();
                }
            }
            deptId=parentId;
        }
        return R.ok().put("parentId",deptId);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @ResponseBody
    public R info(@PathVariable("deptId") Long deptId){
        SysDeptEntity dept=sysDeptService.selectById(deptId);
        return R.ok().put("dept",dept);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysDeptEntity dept){
        sysDeptService.insert(dept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody SysDeptEntity dept){
        sysDeptService.updateById(dept);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(long deptId){
        List<Long> deptList=sysDeptService.querDeptIdList(deptId);
        if(deptList.size()>0){
            return R.error("请先删除子部门");
        }
        sysDeptService.deleteById(deptId);

        return R.ok();
    }


}
