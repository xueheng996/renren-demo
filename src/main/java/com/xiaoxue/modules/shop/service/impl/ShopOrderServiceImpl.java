package com.xiaoxue.modules.shop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.annotation.DataFilter;
import com.xiaoxue.common.utils.Constant;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.Query;
import com.xiaoxue.modules.shop.dao.ShopOrderDao;
import com.xiaoxue.modules.shop.entity.ShopOrderEntity;
import com.xiaoxue.modules.shop.service.ShopOrderService;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ShopOrderServiceImpl  extends ServiceImpl<ShopOrderDao,ShopOrderEntity> implements ShopOrderService {

    @Autowired
    private SysDeptService sysDeptService;

    @Override
    @DataFilter(subDept = true,user = false)
    public PageUtils queryPage(Map<String, Object> map) {

        Page<ShopOrderEntity> page=this.selectPage(new Query<ShopOrderEntity>(map).getPage(),
                new EntityWrapper<ShopOrderEntity>().addFilterIfNeed(map.get(Constant.SQL_FILTER)!=null,(String)map.get(Constant.SQL_FILTER)));
        for (ShopOrderEntity shopOrderEntity:page.getRecords()){
            SysDeptEntity deptEntity=sysDeptService.selectById(shopOrderEntity.getDeptId());
            shopOrderEntity.setDeptName(deptEntity.getName());

        }
        return new PageUtils(page);
    }
}
