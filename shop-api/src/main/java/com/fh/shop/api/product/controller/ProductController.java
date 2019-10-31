package com.fh.shop.api.product.controller;

import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.product.biz.IProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("products")
public class ProductController {
    @Resource(name="productService")
    private IProductService productService;

    /**
     * 查询
     *
     */
    @GetMapping
    public ServerResult findList(){

        return productService.findProductList();
    }



}
