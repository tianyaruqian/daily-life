package com.hou.springboot.api.brand.hou.order.controller;

import com.hou.springboot.api.brand.hou.common.Check;
import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.member.vo.MemberVo;
import com.hou.springboot.api.brand.hou.order.biz.IOrderService;
import com.hou.springboot.api.brand.hou.param.OrderParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Resource
    private IOrderService orderService;

    @PostMapping
    @Check
    public ServerResult addOrder(OrderParam orderParam, MemberVo member){
        Long memberId = member.getId();
        return orderService.addOrder(orderParam,memberId);
    }




}
