package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.IAreaService;
import com.fh.shop.api.common.ServerResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
@RequestMapping("/areas")
public class AreaApi {
    @Resource
    private IAreaService areaService;

    @GetMapping
    public ServerResult findAreaList(Long id){
        return areaService.findAreaList(id);
    }
}
