package com.hou.springboot.api.pay.biz;
import com.alibaba.fastjson.JSONObject;
import com.hou.springboot.api.common.ResponseEnum;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.common.SystemConst;
import com.hou.springboot.api.order.mapper.IOrderMapper;
import com.hou.springboot.api.order.po.Order;
import com.hou.springboot.api.pay.config.MyConfig;
import com.hou.springboot.api.payLog.mapper.IPaylogMapper;
import com.hou.springboot.api.payLog.po.PayLog;
import com.hou.springboot.api.utils.BigDecimalUtil;
import com.hou.springboot.api.utils.DateUtil;
import com.hou.springboot.api.utils.KeyUtil;
import com.hou.springboot.api.utils.RedisUtil;
import com.github.wxpay.sdk.WXPay;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("payService")
public class IPayServiceImpl implements IPayService {
    @Resource
    private IPaylogMapper paylogMapper;
    @Autowired
    private IOrderMapper orderMapper;
    @Override
    public ServerResult createNative(Long memberVoId) {
        String jsonMember = RedisUtil.get(KeyUtil.buildOrderKey(memberVoId));
        if(StringUtils.isEmpty(jsonMember)){
            //订单不存在
            return ServerResult.error(ResponseEnum.ORDER_IS_NULL);

        }
        //取出支付日志
        PayLog payLog = JSONObject.parseObject(jsonMember, PayLog.class);
        //支付订单号
        String outTradeNo = payLog.getOutTradeNo();
        String price = payLog.getPayMoney().toString();
        //支付金额
        int payMoney = BigDecimalUtil.mul(price, "100").intValue();
        //生成支付二维码
        MyConfig config = new MyConfig();
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "在线支付充值");
        data.put("out_trade_no", outTradeNo);
        //data.put("device_info", "");
        //data.put("fee_type", "CNY");
            Date prepayId = DateUtils.addMinutes(new Date(), 2);

        data.put("prepay_id",DateUtil.datetoStr(prepayId,DateUtil.YYYYMMDDHHMMSS));
        data.put("total_fee", payMoney+"");
        //data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://127.0.0.1:8082/index.html");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
       /* data.put("product_id", "122");*/
             //统一下单
            Map<String, String> resp = wxpay.unifiedOrder(data);
            //几个参数判断
            String returnCode = resp.get("return_code");
            String returnMsg = resp.get("return_msg");
            if(!returnCode.equalsIgnoreCase("SUCCESS")){
                System.out.println(returnMsg);
                return ServerResult.error(9999,"微信操作平台"+returnMsg);

            }
            //returnCode是对的
            String resultCode = resp.get("result_code");
            String errCodeMsg = resp.get("err_code_msg");
            if(!resultCode.equalsIgnoreCase("SUCCESS")){
                System.out.println(errCodeMsg);
                return ServerResult.error(9999,"微信操作平台"+errCodeMsg);
            }

            String codeUrl = resp.get("code_url");
            Map<String,Object> map = new HashMap<>();
            map.put("codeUrl",codeUrl);
            map.put("payMoney",payMoney);
            map.put("outTradeNo",outTradeNo);
            return ServerResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.error();
        }

    }
    //插叙微信支付状态
    @Override
    public ServerResult queryOrder(Long mid) {

        String jsonMember = RedisUtil.get(KeyUtil.buildOrderKey(mid));
        if(StringUtils.isEmpty(jsonMember)){
            //订单不存在
            return ServerResult.error(ResponseEnum.ORDER_IS_NULL);

        }

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
            //取出支付日志
            PayLog payLog = JSONObject.parseObject(jsonMember, PayLog.class);
            //支付订单号
            String outTradeNo = payLog.getOutTradeNo();
            String orderId = payLog.getOrderId();
            int count =0;
            while (true) {
                try {
                    data.put("out_trade_no", outTradeNo);
                    //查询订单状态
                    Map<String, String> resp = wxpay.orderQuery(data);

                    //几个参数判断
                    String returnCode = resp.get("return_code");
                    String returnMsg = resp.get("return_msg");
                    if (!returnCode.equalsIgnoreCase("SUCCESS")) {
                        return ServerResult.error(9999, "微信操作平台" + returnMsg);

                    }
                    //returnCode是对的
                    String resultCode = resp.get("result_code");
                    String errCodeMsg = resp.get("err_code_msg");
                    if (!resultCode.equalsIgnoreCase("SUCCESS")) {
                        return ServerResult.error(9999, "微信操作平台" + errCodeMsg);
                    }
                    //resultCode也是对的
                    String tradeState = resp.get("trade_state");
                    if (tradeState.equalsIgnoreCase("SUCCESS")) {
                        System.out.println("*****支付成功");
                        return ServerResult.success(ResponseEnum.PAY_IS_SUCCESS);
                    }
                    count++;


                    Thread.sleep(3000);

                    if (count >= 45) {
                        System.out.println("支付超时=====");
                        return ServerResult.success(ResponseEnum.PAY_IS_TIME_OUT);
                    }
                    //跟新微信支付日志
                    String transactionId = resp.get("transaction_id");
                    payLog.setPayStatus(SystemConst.PAY_STATUS_IS_SUCCESS);
                    Date now = new Date();
                    payLog.setPayTime(now);
                    //微信流水单号
                    payLog.setTransaction(transactionId);
                    paylogMapper.updateById(payLog);

                    //更新订单支付状态
                    Order order = new Order();
                    order.setId(orderId);
                    order.setPayDate(now);
                    order.setOrderStatus(SystemConst.ORDER_STATUS_IS_PAY);
                    orderMapper.updateById(order);
                    //删除redis中的支付日志
                    RedisUtil.delete(KeyUtil.buildOrderKey(mid));

                } catch (Exception e) {
                    e.printStackTrace();

                    return ServerResult.error();
                }

            }
    }
}
