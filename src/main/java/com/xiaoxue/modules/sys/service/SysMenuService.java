package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;

import java.util.List;


public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @param menuList
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId,List<Long> menuList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包按钮的菜单列表
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long userId);

    /**
     * 删除
     * @param menuId
     */
    void delete(Long menuId);

}
