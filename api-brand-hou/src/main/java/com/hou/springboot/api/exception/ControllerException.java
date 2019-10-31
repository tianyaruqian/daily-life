package com.hou.springboot.api.exception;


import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.utils.GloablException;
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
