package com.hou.springboot.api.brand.hou.token.biz;

import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.utils.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("tokenService")
public class ITokenServiceImpl implements ITokenService {


    public ServerResult createToken() {
        String token = UUID.randomUUID().toString();
        RedisUtil.set(token,"0");
        return ServerResult.success(token);
    }
}
