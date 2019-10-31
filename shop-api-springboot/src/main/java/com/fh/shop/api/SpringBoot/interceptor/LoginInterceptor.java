package com.hou.springboot.api.brand.hou.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hou.springboot.api.brand.hou.common.Check;
import com.hou.springboot.api.brand.hou.common.ResponseEnum;
import com.hou.springboot.api.brand.hou.common.SystemConst;
import com.hou.springboot.api.brand.hou.member.vo.MemberVo;
import com.hou.springboot.api.brand.hou.utils.GloablException;
import com.hou.springboot.api.brand.hou.utils.KeyUtil;
import com.hou.springboot.api.brand.hou.utils.Md5Util;
import com.hou.springboot.api.brand.hou.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Base64;
@SpringBootApplication
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //设置头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"http://localhost:8082");
        //指定访问的域名和端口号
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-token,token,content-type");
        //自定义头信息
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"GET,PUT,DELETE,POST");
        //访问的跨域请求的类型（可以不写）
        String requestMethod = request.getMethod();
        //清除option请求
        if(requestMethod.equalsIgnoreCase("options")){

            return false;
        }

        //判断放行
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(!method.isAnnotationPresent(Check.class)){
            return true;
        }

        String header = request.getHeader("x-token");
        //判断头信息是否为空
        if(StringUtils.isEmpty(header)){
           throw new GloablException(ResponseEnum.HEADER_IS_NULL);
        }
        //拆分
        String[] split = header.split("\\.");
        //判断长度
        if(split.length!=2){
            throw new GloablException(ResponseEnum.HEADER_IS_MISS);
        }
        //验签
        //base64编码
        String memberJson = split[0];
        String signBase64 = split[1];
        //新签名
        String newSign = Md5Util.sign(memberJson, SystemConst.SECRETKEY);
        //base64解码
        String  oldSign = new String (Base64.getDecoder().decode(signBase64.getBytes()));
        //判断头信息
        if(!newSign.equals(oldSign)){
            throw new GloablException(ResponseEnum.HEADER_IS_ERROR);
        }

        //判断超时
        String memJson = new String(Base64.getDecoder().decode(memberJson.getBytes()));
        //json转java
        MemberVo memberVo = JSONObject.parseObject(memJson, MemberVo.class);
        Long mid = memberVo.getId();
        String uid = memberVo.getUid();
        String memberName = memberVo.getMemberName();

        boolean exist = RedisUtil.Exist(KeyUtil.buildMember(mid,memberName, uid));
        //判断超时
        if(!exist){
             throw new GloablException(ResponseEnum.LOGIN_TIME_OUT);
        }
        //续命
        RedisUtil.setEx(KeyUtil.buildMember(mid,memberName, uid),SystemConst.EXPIRE_TIME,"2");
        //放入作用域
        request.setAttribute("member",memberVo);
        return true;

    }
}
