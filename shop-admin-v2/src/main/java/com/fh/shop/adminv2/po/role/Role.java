package com.fh.shop.adminv2.po.role;

import com.fh.shop.adminv2.common.Page;

import java.io.Serializable;

public class Role extends Page implements Serializable {

    private Integer id;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }




}
