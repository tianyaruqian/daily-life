package com.hou.springboot.api.cart.biz;

import com.hou.springboot.api.common.ServerResult;

public interface ICartBiz {
    ServerResult addCart(Long pid, Long count, Long userVipVoId);

    ServerResult findCart(Long id);

    ServerResult deleteCart(Long userVipVoId, Long productId);
}
