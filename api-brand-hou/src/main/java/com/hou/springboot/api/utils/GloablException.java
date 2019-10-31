package com.hou.springboot.api.utils;

import com.hou.springboot.api.common.ResponseEnum;

public class GloablException extends RuntimeException{

    private ResponseEnum responseEnum;


    public GloablException(ResponseEnum responseEnum) {

        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {

        return responseEnum;
    }
}
