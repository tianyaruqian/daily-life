package com.hou.springboot.api.order.biz;

import com.alibaba.fastjson.JSONObject;
import com.hou.springboot.api.cart.vo.CartItem;
import com.hou.springboot.api.cart.vo.CartVo;
import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.common.SystemConst;
import com.hou.springboot.api.order.mapper.IOrderMapper;
import com.hou.springboot.api.order.mapper.IOrderdetailsMapper;
import com.hou.springboot.api.order.po.Order;
import com.hou.springboot.api.order.po.Orderdetails;
import com.hou.springboot.api.param.OrderParam;
import com.hou.springboot.api.payLog.mapper.IPaylogMapper;
import com.hou.springboot.api.payLog.po.PayLog;
import com.hou.springboot.api.product.mapper.IProductMapper;
import com.hou.springboot.api.product.po.Product;
import com.hou.springboot.api.utils.BigDecimalUtil;
import com.hou.springboot.api.utils.IDUtil;
import com.hou.springboot.api.utils.KeyUtil;
import com.hou.springboot.api.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("orderService")
public class IOrderServiceImpl implements IOrderService {
    @Resource
    private IOrderMapper orderMapper;

    @Resource
    private IOrderdetailsMapper orderdetailsMapper;

    @Resource
     private IProductMapper productMapper;
    @Resource
    private IPaylogMapper paylogMapper;

    @Override
    public ServerResult addOrder(OrderParam orderParam, Long memberId) {
            //从redis中取出数据
        String cartJson = RedisUtil.Hget(SystemConst.HASH_REDIS, KeyUtil.buildCartKey(memberId));
        if(StringUtils.isEmpty(cartJson)){
            return ServerResult.error(ResponseEnum.CART_IS_NULL);
        }
        CartVo itemCart = JSONObject.parseObject(cartJson, CartVo.class);
        //插入订单
        Order order = new Order();
        //雪花算法
        Long timeId = IDUtil.getRandomId();
        order.setId(String.valueOf(timeId));
        order.setTotalPrice(new BigDecimal(itemCart.getTotalPrice()));
        order.setTotalCount(itemCart.getTotalCount());
        order.setMemberId(memberId);
        order.setCreatDate(new Date());
        order.setOrderStatus(10);
        order.setPayType(orderParam.getPayType());
        orderMapper.insert(order);
        //插入订单明细
        List<CartItem> itemProList = itemCart.getList();
        List<CartItem> shotItems = new ArrayList<>();
        //迭代器循环
        for (Iterator<CartItem> iterator = itemProList.iterator();iterator.hasNext();) {
            CartItem itemPro = iterator.next();
            Orderdetails orderdetails = new Orderdetails();
            orderdetails.setMemberId(memberId);
            orderdetails.setCount(itemPro.getCount());
            orderdetails.setPrice(new BigDecimal(itemPro.getProductPrice()));
            orderdetails.setProductName(itemPro.getProductName());
            orderdetails.setPid(itemPro.getId());
            orderdetails.setSumPrice(new BigDecimal(itemPro.getSubtotal()));
            orderdetails.setOrderId(String.valueOf(timeId));
            orderdetails.setImage(itemPro.getImage());
            //获取商品
            Long productId = orderdetails.getPid();
            Product product = productMapper.selectById(productId);
            Long count = orderdetails.getCount();
            //获取商品库存
            Integer stock = product.getStock();
            long realStock = stock - count;
            product.setStock((int) realStock);
            //库存不足
            if(product.getStock()>=count){
                //更新库存
                Long updateCount = productMapper.updatePro(productId,count);
                if(updateCount==0){
                    //存储库不足的商品
                    shotItems.add(itemPro);
                    //移除商品
                    iterator.remove();
                }else {
                    orderdetailsMapper.insert(orderdetails);
                }
            }else{
                //存储库不足的商品
                shotItems.add(itemPro);
                //移除商品
                iterator.remove();
            }

        }
        //库存不足；
        if(itemProList.size()==0){
            return ServerResult.error(ResponseEnum.PRODUCT_STOCK_IS_SHORT);
        }
        //更新购物车
        Long totalCount = 0L;
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem item : itemProList) {
            totalCount  += item.getCount();
            String itemPrice = BigDecimalUtil.mul(item.getProductPrice(), item.getCount().toString()).toString();
            totalPrice = BigDecimalUtil.add(totalPrice.toString(),itemPrice);
        }
        itemCart.setTotalCount(totalCount);
        itemCart.setTotalPrice(totalPrice.toString());

        //插入支付日志
        PayLog payLog = new PayLog();
        //雪花算法
        Long outTrade = IDUtil.getRandomId();
        //主键id
        payLog.setOutTradeNo(outTrade.toString());
        //订单id
        payLog.setOrderId(timeId.toString());
        payLog.setMemberId(memberId);//用户id
        payLog.setCreateDate(new Date());//创建时间
        payLog.setPayType(orderParam.getPayType());

        payLog.setPayMoney(new BigDecimal(itemCart.getTotalPrice()));
        payLog.setPayStatus(SystemConst.PAY_STATUS_IS_WAIT_PAY);
        paylogMapper.insert(payLog);
        String jsonString = JSONObject.toJSONString(payLog);
        //存入redis
        RedisUtil.set(KeyUtil.buildOrderKey(memberId),jsonString);

        //清除购物车
        RedisUtil.hdel(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(memberId));
        return ServerResult.success(shotItems);
    }
}
