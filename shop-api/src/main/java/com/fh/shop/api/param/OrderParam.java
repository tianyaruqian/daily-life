package com.fh.shop.api.param;

import com.fh.shop.api.product.po.Product;

public class OrderParam extends Product {

    private Integer payType;


    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
