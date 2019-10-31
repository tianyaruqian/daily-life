package com.hou.springboot.api.param;


import com.hou.springboot.api.common.Page;


public class BrandParam extends Page {


    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


}
