package com.hou.springboot.api.brand.hou.pay.biz;

import com.hou.springboot.api.brand.hou.common.ServerResult;

public interface IPayService {
    ServerResult createNative(Long memberVoId);

    ServerResult queryOrder(Long mid);

}
