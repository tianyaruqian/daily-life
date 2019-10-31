package com.fh.shop.api.order.biz;

import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.param.OrderParam;

public interface IOrderService {
    ServerResult addOrder(OrderParam orderParam, Long memberId);
}
