package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
