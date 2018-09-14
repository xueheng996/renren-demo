package com.xiaoxue.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.shop.entity.ShopOrderEntity;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


public interface ShopOrderService extends IService<ShopOrderEntity> {

     PageUtils queryPage(Map<String, Object>map);

}
