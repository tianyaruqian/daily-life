package com.hou.springboot.api.area.controller;

import com.hou.springboot.api.area.biz.IAreaService;
import com.hou.springboot.api.common.ServerResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
