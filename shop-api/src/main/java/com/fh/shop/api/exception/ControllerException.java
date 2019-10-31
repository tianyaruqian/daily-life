package com.fh.shop.api.exception;

import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.utils.GloablException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerException{
    @ExceptionHandler(GloablException.class)
    @ResponseBody
    public ServerResult ControllerException(GloablException g){
        ResponseEnum responseEnum = g.getResponseEnum();
        return ServerResult.error(responseEnum);
    }

}
