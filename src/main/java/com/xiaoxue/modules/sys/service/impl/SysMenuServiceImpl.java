package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.utils.Contant;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.dao.SysMenuDao;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysMenuService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        logger.info("parentId="+parentId);
        List<SysMenuEntity> menuList=queryListParentId(parentId);
        logger.info("listsize="+menuList.size());
        if(menuIdList==null){

            return menuList;
        }
        List<SysMenuEntity> userMenuList=new ArrayList<>();
        for (SysMenuEntity menu:menuList){
            logger.info("menuId="+menu.getMenuId());
            if(menuList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        if(userId==Contant.SUPER_ADMIN){
            return getAllMenuList(null);
        }
        List<Long> menuIdList=sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     * @param
     * @return
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        List<SysMenuEntity> menuList=queryListParentId(0L,menuIdList);
        logger.info("menulistsize="+menuList.size());
        logger.info("menu="+menuList.get(0).getName());
        getMenuTreeList(menuList,menuIdList);
        return menuList;
    }

    /**
     * 递归
     * @param menuList
     * @param menuIdList
     * @return
     */
    public List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList,List<Long> menuIdList){
        List<SysMenuEntity> subMenuList=new ArrayList<SysMenuEntity>();
        logger.info("menuTreeelistsize="+menuList.size());

        for(int i=0;i<menuList.size();i++){
            SysMenuEntity entity=new SysMenuEntity();
            //entity=menuIdList.get(i);
            logger.info("menuEntity =",entity.getName());
            if (entity.getType()==Contant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(),menuIdList),menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    @Override
    public void delete(Long menuId){

    }

}