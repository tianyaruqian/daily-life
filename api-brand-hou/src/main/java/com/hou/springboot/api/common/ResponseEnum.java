package com.hou.springboot.api.common;

public enum ResponseEnum {

    BRAND_IS_NULL(1000,"查询的不存在"),
    CODE_ERROR(1005,"验证码错误"),
    CODE_SEND_FAILED(1006,"验证码发送失败"),
    CODE_SEND_SUCEEESS(1007,"验证码发送成功"),
    MEMBER_IS_NULL(1008,"请输入用户名"),
    PHONENUMBER_NULL(1009,"手机号码为空"),
    MEMBERNAME_IS_EXIST(1010,"用户名已存在"),
    PHONE_IS_EXIST(1011,"手机号码已存在"),
    PHONE_LENGTH_ERROR(1012,"手机号码为空"),
    PHONE_STYLE_ERROR(1013,"手机号码格式错误"),
    EMAIL_IS_NULL(1014,"邮箱为空"),
    EMAIL_IS_EXIST(1015,"邮箱已存在"),
    PASSWORD_IS_NULL(1016,"密码为空"),
    MEMBERNAME_IS_NULL(1017,"用户名不存在"),
    MEMBERNAME_IS_ERROR(1017,"密码错误"),
    HEADER_IS_NULL(2001,"头信息为空"),
    HEADER_IS_MISS(2002,"头信息缺失"),
    HEADER_IS_ERROR(2003,"头信息被篡改"),
    LOGIN_TIME_OUT(2004,"登陆超时"),


    PRODUCT_IS_NULL(2101,"商品不存在"),
    PRODUCT_IS_DOWN(2102,"商品目前已下架"),
    CART_IS_NULL(2103,"购物车为空"),
    TOKEN_IS_NULL(2106,"token信息不存在"),
    REQUEST_IS_REPET(2107,"商品不存在"),
    PRODUCT_STOCK_IS_SHORT(2109,"商品库存不足"),

    //订单
    ORDER_IS_NULL(4000,"订单不存在"),
    PAY_IS_SUCCESS(4001,"支付成功"),
    PAY_IS_TIME_OUT(4002,"支付超时"),
    ;




    private int code;

    private String msg;




    private ResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;

    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
