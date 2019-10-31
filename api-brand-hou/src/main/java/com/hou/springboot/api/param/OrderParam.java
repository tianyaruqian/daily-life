package com.hou.springboot.api.param;

import com.hou.springboot.api.product.po.Product;

public class OrderParam extends Product {

    private Integer payType;


    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
