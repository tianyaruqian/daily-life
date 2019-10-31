package com.fh.shop.api.order.controller;

import com.fh.shop.api.common.Check;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.order.biz.IOrderService;
import com.fh.shop.api.param.OrderParam;
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
