package com.hou.springboot.api.order.biz;

import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.param.OrderParam;

public interface IOrderService {
    ServerResult addOrder(OrderParam orderParam, Long memberId);
}
