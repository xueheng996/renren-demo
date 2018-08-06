package com.xiaoxue.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.List;


public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    List<SysMenuEntity> queryListParentId(Long parentId);

    List<SysMenuEntity> queryNotButtonList();

}
