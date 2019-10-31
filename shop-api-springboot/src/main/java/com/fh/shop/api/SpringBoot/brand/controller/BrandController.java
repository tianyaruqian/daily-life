package com.hou.springboot.api.brand.hou.brand.controller;


import com.hou.springboot.api.brand.hou.brand.biz.IBrandService;
import com.hou.springboot.api.brand.hou.common.ServerResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Resource(name="brandService")
    private IBrandService brandService;

    /**
     * 查询
     */
    @GetMapping
    public ServerResult queryList(){

        return brandService.queryList();
    }




}
