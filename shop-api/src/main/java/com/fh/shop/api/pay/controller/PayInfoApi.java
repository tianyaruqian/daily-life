package com.fh.shop.api.pay.controller;

import com.fh.shop.api.common.Check;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.pay.biz.IPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pays")
public class PayInfoApi {

    @Resource(name="payService")
    private IPayService payService;



    @GetMapping
    @Check
    public ServerResult createNative(MemberVo memberVo){
        Long memberVoId = memberVo.getId();
        return payService.createNative(memberVoId);

    }

    /**
     * 查询订单状态
     * @param memberVo
     * @return
     */
    @GetMapping(value = "queryOrder")
    @Check
    public ServerResult queryOrder(MemberVo memberVo){
        Long mid = memberVo.getId();
        return payService.queryOrder(mid);
    }

}
