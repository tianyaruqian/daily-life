package com.fh.shop.api.pay.biz;

import com.fh.shop.api.common.ServerResult;

public interface IPayService {
    ServerResult createNative(Long memberVoId);

    ServerResult queryOrder(Long mid);

}
