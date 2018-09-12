package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


public interface SysDeptService extends IService<SysDeptEntity> {

    List<SysDeptEntity> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     */
    List<Long> queryDeptIdList(Long parentId);

    /**
     * 获取子部门ID，用于数据过滤
     */
    List<Long> getSubDeptIdList(Long deptId);

}
