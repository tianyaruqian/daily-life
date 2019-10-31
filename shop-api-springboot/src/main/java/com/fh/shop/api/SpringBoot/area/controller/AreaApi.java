package com.hou.springboot.api.brand.hou.area.controller;
import com.hou.springboot.api.brand.hou.area.biz.IAreaService;
import com.hou.springboot.api.brand.hou.common.ServerResult;
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
