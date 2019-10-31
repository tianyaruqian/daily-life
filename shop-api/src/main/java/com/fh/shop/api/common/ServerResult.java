package com.fh.shop.api.common;

import java.io.Serializable;

public class ServerResult implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    //私有化无参
    private ServerResult() {
        super();
    }
    //私有化有参
    private ServerResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //静态方法(无参数类型)
    public static ServerResult success (){

        return new ServerResult(200,"ok",null);
    }
    //  (有参数类型)

    public static ServerResult success(Object data){
            return new ServerResult(200,"ok",data);
    }

    //错误
    public static ServerResult error(){
        return new ServerResult(-1,"操作失败",null);
    }

    public static ServerResult error(int code,String msg){
        return new ServerResult(code,msg,null);
    }

    public static ServerResult error(ResponseEnum responseEnum){

        return new ServerResult(responseEnum.getCode(),responseEnum.getMsg(),null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
