package com.hou.springboot.api.pay.controller;

import com.hou.springboot.api.common.Check;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.member.vo.MemberVo;
import com.hou.springboot.api.pay.biz.IPayService;
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
