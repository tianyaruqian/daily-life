package com.fh.shop.api.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpclientUtil {

    public static String HttpClient(String url, Map<String ,String> headers,Map<String ,String> param){
        //
        String result = "";
        CloseableHttpClient client= null;
        HttpPost httpPost = null;
        CloseableHttpResponse reg = null;
        try {
        client = HttpClientBuilder.create().build();
        //头信
        httpPost = new HttpPost(url);
        //
        if(headers!=null & headers.size()>0){
            Iterator<Map.Entry<String, String>> headermap = headers.entrySet().iterator();
            while(headermap.hasNext()){
                Map.Entry<String, String> next = headermap.next();
                String key = next.getKey();
                String value = next.getValue();
                httpPost.addHeader(key,value);
                }
            }

        if(null!=param & param.size()>0){
            //设置参数
            List<BasicNameValuePair> pairList= new ArrayList<>();
            Iterator<Map.Entry<String, String>> params = param.entrySet().iterator();
            while(params.hasNext()){
                Map.Entry<String, String> next = params.next();
                String key = next.getKey();
                String value = next.getValue();
                BasicNameValuePair pairs= new BasicNameValuePair(key,value);
                pairList.add(pairs);
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList,"utf-8");
            httpPost.setEntity(urlEncodedFormEntity);

        }
             reg = client.execute(httpPost);
            HttpEntity entity = reg.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if(null!= reg){
                    reg.close();
                }
                if(null!=httpPost){
                    httpPost.releaseConnection();
                }
                if(null!=client){
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;

    }


}
