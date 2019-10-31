package com.hou.springboot.api.interceptor;
import com.hou.springboot.api.common.IdemPoment;
import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.utils.GloablException;
import com.hou.springboot.api.utils.RedisUtil;
import com.hou.springboot.api.common.IdemPoment;
import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.utils.GloablException;
import com.hou.springboot.api.utils.RedisUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class IdempotInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method =  handlerMethod.getMethod();
            if(!method.isAnnotationPresent(IdemPoment.class)){
                return  true;
            }

        //判断头信息是否为空
        //接口支持幂等性
        String token= request.getHeader("x-token");
            if(org.apache.commons.lang3.StringUtils.isEmpty(token)){
                throw new GloablException(ResponseEnum.HEADER_IS_NULL);

            }
            //验证
        boolean exist = RedisUtil.Exist(token);
            if(!exist){
                throw new GloablException(ResponseEnum.TOKEN_IS_NULL);
            }
            //核心
        Long delCount = RedisUtil.delete(token);

        if(delCount<=0){
               throw new GloablException(ResponseEnum.REQUEST_IS_REPET);
        }

        return true;
    }
}
