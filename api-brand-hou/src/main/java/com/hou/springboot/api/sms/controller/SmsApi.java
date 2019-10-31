package com.hou.springboot.api.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.common.SystemConst;
import com.hou.springboot.api.sms.Smsresult;
import com.hou.springboot.api.sms.biz.ISmsService;
import com.hou.springboot.api.utils.RedisUtil;
import com.hou.springboot.api.utils.SendUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/codes")
@CrossOrigin("*")
public class SmsApi {
    @Resource(name="smsService")
    private ISmsService smsService;

    @GetMapping
    public ServerResult sendCode(String phoneNumber){
        //手机号 正则验证
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (StringUtils.isEmpty(phoneNumber)) {
            //手机号应为11位数
            return ServerResult.error(ResponseEnum.PHONE_LENGTH_ERROR);
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        boolean isMatch = m.matches();
        if (!isMatch) {
            //您的手机号是错误格式
            return ServerResult.error(ResponseEnum.PHONE_STYLE_ERROR);
        }
        String codesJson = SendUtil.sendMsg(phoneNumber);
        Smsresult result = JSONObject.parseObject(codesJson, Smsresult.class);
        //判断验证码是否发送成功
        if(result.getCode()!=200){
            //发送失败
            return ServerResult.error(ResponseEnum.CODE_SEND_FAILED);
        }
        //存入redis
        RedisUtil.setEx(phoneNumber,SystemConst.CODE_TIME_OUT,result.getObj());

        return ServerResult.success(ResponseEnum.CODE_SEND_SUCEEESS);

    }


}
