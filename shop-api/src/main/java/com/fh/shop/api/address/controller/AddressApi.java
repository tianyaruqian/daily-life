package com.fh.shop.api.address.controller;

import com.fh.shop.api.address.biz.IAddressService;
import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
public class AddressApi {
    @Resource
    private IAddressService addressService;


    /**
     * 新增
     */
    @PostMapping
    public ServerResult insertAddress(Address address, MemberVo memberVo){
        Long memberVoId = memberVo.getId();
        return  addressService.insertAddress(address,memberVoId);
    }


    /**
     * 删除
     */
    @DeleteMapping
    public ServerResult deleteAddress(Long id){

        return addressService.deleteAddress(id);
    }





}
