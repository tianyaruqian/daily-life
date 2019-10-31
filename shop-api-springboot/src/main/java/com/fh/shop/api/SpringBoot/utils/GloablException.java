package com.hou.springboot.api.brand.hou.utils;

import com.hou.springboot.api.brand.hou.common.ResponseEnum;

public class GloablException extends RuntimeException{

    private ResponseEnum responseEnum;


    public GloablException(ResponseEnum responseEnum) {

        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {

        return responseEnum;
    }
}
