package com.hou.springboot.api.brand.hou.type.controller;


import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.type.biz.ITypeService;
import com.hou.springboot.api.brand.hou.type.po.Type;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Resource(name="typeService")
    private ITypeService typeService;

    @RequestMapping("toType")
    public String toType(){

        return "type/index";
    }

    /**
     * 查询
     */

    @PostMapping("findList")
    public ServerResult findList(Long id){
        //根据传过来的id，找到对应的子节点
        ServerResult list = typeService.findList(id);
        return list;
    }

    /**
     * 分类树
     * @return
     */
    @PostMapping("findAllList")
    public ServerResult findAllList(){
        List<Type> typeList = typeService.findAllList();
        return ServerResult.success(typeList);
    }


    /**
     * 新增
     */
    @PostMapping("addType")
    public ServerResult addType(Type type){
        typeService.addType(type);
        return ServerResult.success(type.getId());
    }
    /**
     * 修改
     */
    @PostMapping("updateType")
   public ServerResult updateType(Type type){

        return typeService.updateType(type);
    }
    /**
     * 删除
     */
    @DeleteMapping("deleteType")
    public ServerResult deleteType(@RequestParam("ids[]") List<Integer> ids){
        typeService.deleteType(ids);
        return ServerResult.success();
    }

}
