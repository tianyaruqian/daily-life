package com.fh.shop.api.token.biz;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.utils.RedisUtil;
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
