package com.hou.springboot.api.mapper;

import lombok.Data;

@Data
public class Brand {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
