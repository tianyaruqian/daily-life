package com.hou.springboot.api.brand.hou.param;

import com.hou.springboot.api.brand.hou.product.po.Product;

public class OrderParam extends Product {

    private Integer payType;


    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
