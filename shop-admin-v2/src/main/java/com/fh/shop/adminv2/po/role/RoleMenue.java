package com.fh.shop.adminv2.po.role;

import java.io.Serializable;

public class RoleMenue implements Serializable {

    private Integer id;

    private Integer roleId;

    private Integer menueId;

    public RoleMenue( Integer roleId, Integer menueId) {

        this.roleId = roleId;
        this.menueId = menueId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenueId() {
        return menueId;
    }

    public void setMenueId(Integer menueId) {
        this.menueId = menueId;
    }
}
