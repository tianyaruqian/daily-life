package com.hou.springboot.api.pay.biz;


import com.hou.springboot.api.common.ServerResult;

public interface IPayService {
    ServerResult createNative(Long memberVoId);

    ServerResult queryOrder(Long mid);

}
