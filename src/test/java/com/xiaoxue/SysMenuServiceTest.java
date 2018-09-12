package com.xiaoxue;

import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysMenuService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysDeptService sysDeptService;


    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void test(){

//        List<SysMenuEntity> list= sysMenuService.queryListParentId((long) 0);
//        logger.info("listsize="+list.size());
//        for (SysMenuEntity menuEntity:list){
//            logger.info("menuId="+menuEntity.getMenuId());
//        }

//        SysUserEntity user=new SysUserEntity();
//        user.setUsername("admin");
//        user=sysUserDao.selectOne(user);
//        logger.info(user.toString());

        List<Long> subDeptIdList= sysDeptService.getSubDeptIdList(Long.valueOf(3));
        logger.info(""+subDeptIdList.size());
    }
}
