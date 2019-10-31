package com.hou.springboot.api.common;

public class PaymentConfig {

    /*******微信支付参数*********/
    //公众账号ID
    public static final String appid="wxa1e44e130a9a8eee";
    //密钥
    public static final String appKey="feihujiaoyu12345678yuxiaoyang123 ";
    //商户号
    public static final String mch_id="1507758211";
    //接口地址
    public static final String pay_url="https://api.mch.pay.qq.com/pay/unifiedorder";
    //支付返回地址
    public static final String wxRetrun="http://localhost:8082/index.html";
    //交易场景信息
    public static final String scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"侯博文在线支付\"}} ";

    public static final int ENROLL_PRICE = 200;
}
