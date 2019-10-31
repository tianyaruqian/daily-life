package com.fh.shop.api.brand.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
public class BrandApi {
*/
  /*  @Resource(name="brandService")
    private IBrandService brandService;

    *//**
     * 分页条件
     *//*
    @GetMapping
    public ServerResult findBrandPageList(BrandParam brandParam){

        return  brandService.findBrandPageList(brandParam);
    }

    *//**
     *
     * @return
     *//*
 *//*   @RequestMapping(method = RequestMethod.GET)
    public ServerResult findAll(){
        return brandService.findAll();
    }*//*
    *//**
     * 增加
     *//*
    @PostMapping
    public ServerResult addBrand(Brand brand){

        return brandService.addBrand(brand);
    }

    *//**
     * 删除
     *//*
    @DeleteMapping(value = "/{id}")
    public ServerResult deleteBrand(@PathVariable Long id){
        return brandService.deleteBrand(id);
    }

    *//**
     * 回显
     *//*

    @GetMapping(value = "/{id}")
    public ServerResult selectBrand(@PathVariable Long id){
        return brandService.selectBrand(id);
    }

    *//**
     * 修改
     *//*
    @PutMapping
    public ServerResult updateBrand(@RequestBody Brand brand){
        return brandService.updateBrand(brand);
    }
    *//**
     * 批量删除
     *//*
    @DeleteMapping
    public ServerResult deleteBatch(@RequestParam("ids[]") List<Integer> ids){
        return brandService.deleteBatch(ids);
    }
}
*/