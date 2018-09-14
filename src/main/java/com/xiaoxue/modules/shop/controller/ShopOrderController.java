package com.xiaoxue.modules.shop.controller;

import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.shop.service.ShopOrderService;
import com.xiaoxue.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/shop/order")
public class ShopOrderController extends AbstractController {


    @Autowired
    private ShopOrderService shopOrderService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String,Object> params){

        PageUtils pageUtils=shopOrderService.queryPage(params);
        return R.ok().put("page",pageUtils);
    }


}
