package com.hou.springboot.api.brand.hou.cart.biz;

import com.hou.springboot.api.brand.hou.common.ServerResult;

public interface ICartBiz {
    ServerResult addCart(Long pid, Long count, Long userVipVoId);

    ServerResult findCart(Long id);

    ServerResult deleteCart(Long userVipVoId, Long productId);
}
