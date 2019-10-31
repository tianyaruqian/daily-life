package com.fh.shop.api.utils;

import com.fh.shop.api.common.ResponseEnum;

public class GloablException extends RuntimeException{

    private ResponseEnum responseEnum;


    public GloablException(ResponseEnum responseEnum) {

        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {

        return responseEnum;
    }
}
