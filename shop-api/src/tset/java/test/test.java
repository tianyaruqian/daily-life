package test;


import com.fh.shop.api.pay.config.MyConfig;
import com.github.wxpay.sdk.WXPay;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class test {







    @Test
    public void aaa()  {
     MyConfig config = new MyConfig();
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> data = new HashMap<String, String>();
     data.put("body", "丹佛充值");
     data.put("out_trade_no", "20160909105959000028082");
     //data.put("device_info", "");
     data.put("fee_type", "CNY");
     data.put("total_fee", "1");
     data.put("spbill_create_ip", "123.12.12.123");
     data.put("notify_url", "http://www.example.com/wxpay/notify");
     data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
     data.put("product_id", "122");

     try {
         Map<String, String> resp = wxpay.unifiedOrder(data);
         System.out.println(resp);
     } catch (Exception e) {
         e.printStackTrace();
     }



 }


    @Test
    public void Test(){

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no","20160909105959000028082");
        try {
            int count =0;
            while (true){
                //查询订单状态
                Map<String, String> resp = wxpay.orderQuery(data);

                //几个参数判断
                String returnCode = resp.get("return_code");
                String returnMsg = resp.get("return_msg");
                if(!returnCode.equalsIgnoreCase("SUCCESS")){
                    System.out.println(returnMsg);
                    return ;

                }
                //returnCode是对的
                String resultCode = resp.get("result_code");
                String errCodeMsg = resp.get("err_code_msg");
                if(!resultCode.equalsIgnoreCase("SUCCESS")){
                    System.out.println(errCodeMsg);
                    return ;
                }
                //resultCode也是对的
                String tradeState = resp.get("trade_state");

                if(tradeState.equalsIgnoreCase("SUCCESS")){
                    System.out.println("*****支付成功");
                    break;
                }
                count++;
                Thread.sleep(5000);
                if(count>=100){
                    System.out.println("支付成功=====");
                         break;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }









}
