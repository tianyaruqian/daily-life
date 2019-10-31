package com.hou.springboot.api.brand.po;

import java.io.Serializable;

public class Brand implements Serializable {

    private Integer id;

    private String brandName;

    private Integer status;

    private Integer vieworder;

    public Integer getVieworder() {
        return vieworder;
    }

    public void setVieworder(Integer vieworder) {
        this.vieworder = vieworder;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
