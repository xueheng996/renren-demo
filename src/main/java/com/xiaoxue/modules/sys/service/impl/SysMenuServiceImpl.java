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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {


    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList=queryListParentId(parentId);
        if(menuList==null){
            return menuList;
        }
        List<SysMenuEntity> userMenuList=new ArrayList<>();
        for (SysMenuEntity menu:menuList){
            if(menuIdList.contains(menu.getMenuId())){
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

        }
        return null;
    }

    /**
     * 获取所有菜单列表
     * @param menuList
     * @return
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        List<SysMenuEntity> menuList=queryListParentId(0L,menuIdList);
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
        List<SysMenuEntity> sbuMenuList=new ArrayList<SysMenuEntity>();
        for(SysMenuEntity entity:menuList){


        }
        return null;
    }

    @Override
    public void delete(Long menuId){

    }

}
