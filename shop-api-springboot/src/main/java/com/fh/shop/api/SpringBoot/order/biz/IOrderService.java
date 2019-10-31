package com.hou.springboot.api.brand.hou.order.biz;

import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.param.OrderParam;

public interface IOrderService {
    ServerResult addOrder(OrderParam orderParam, Long memberId);
}
