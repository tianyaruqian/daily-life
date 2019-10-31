package com.fh.shop.api.cart.controller;
import com.fh.shop.api.cart.biz.ICartBiz;
import com.fh.shop.api.common.Check;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("carts")
public class CartController {

    @Resource
    private ICartBiz cartBiz;
    @Resource
    private HttpServletRequest request;

    @GetMapping
    @Check
    public ServerResult findCart(MemberVo memberVo){

        Long id = memberVo.getId();
        return cartBiz.findCart(id);
    }

    @PostMapping
    @Check
    public ServerResult addCart(Long productId,Long count,MemberVo memberVo){
        Long mid = memberVo.getId();
        return cartBiz.addCart(productId,count,mid);
    }

    @DeleteMapping("/{productId}")
    @Check
    public ServerResult deleteCart(@PathVariable Long productId,MemberVo memberVo){
        Long userVipVoId = memberVo.getId();
        return cartBiz.deleteCart(userVipVoId,productId);
    }
}
