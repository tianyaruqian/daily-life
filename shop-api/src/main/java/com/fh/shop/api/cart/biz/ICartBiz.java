package com.fh.shop.api.cart.biz;

import com.fh.shop.api.common.ServerResult;

public interface ICartBiz {
    ServerResult addCart(Long pid, Long count, Long userVipVoId);

    ServerResult findCart(Long id);

    ServerResult deleteCart(Long userVipVoId, Long productId);
}
