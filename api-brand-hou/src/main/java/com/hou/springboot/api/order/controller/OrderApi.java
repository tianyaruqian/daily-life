package com.hou.springboot.api.order.controller;

import com.hou.springboot.api.common.Check;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.member.vo.MemberVo;
import com.hou.springboot.api.order.biz.IOrderService;
import com.hou.springboot.api.param.OrderParam;
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
