package com.hou.springboot.api.brand.hou.address.controller;


import com.hou.springboot.api.brand.hou.address.biz.IAddressService;
import com.hou.springboot.api.brand.hou.address.po.Address;
import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.member.vo.MemberVo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
public class AddressApi {
    @Resource(name="addressService")
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
