package com.hou.springboot.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SendUtil {
    private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY="9bc4e2a1b6fb4208ac7134a7b0d904ce";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="63ea8636cecf";
    private static final String CODELEN="6";
    public static String sendMsg(String phone){
        Map<String,String> param = new HashMap<>();
        param.put("mobile",phone);
        param.put("codeLen", CODELEN);
        Map<String,String> header= new HashMap<>();
        header.put("AppKey", APP_KEY);
        String NONCE = UUID.randomUUID().toString();
        header.put("Nonce", NONCE);
        String curTime = System.currentTimeMillis() + "";
        header.put("CurTime", curTime);
        header.put("CheckSum", CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime));
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String result = HttpclientUtil.HttpClient(SERVER_URL, header, param);

        return result;
    }


}
