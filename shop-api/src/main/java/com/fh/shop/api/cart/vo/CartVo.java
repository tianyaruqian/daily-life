package com.fh.shop.api.cart.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartVo implements Serializable {

    private String totalPrice;
    private Long totalCount;
    private List<CartItem> list = new ArrayList<>();

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }
}
