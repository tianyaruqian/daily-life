package com.fh.shop.api.token.controller;

import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.token.biz.ITokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tokens")
public class TokenApi {
    @Resource(name="tokenService")
    private ITokenService tokenService;



    @GetMapping
    public ServerResult createToken (){

        return tokenService.createToken();
    }
}
