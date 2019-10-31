package com.fh.shop.api.utils;

public class KeyUtil {

    public static String buildCode(String data){

        return "code"+data;
    }

   public static String buildMember(Long mid,String name,String id){

        return "member"+":"+mid+":"+name+":"+ id;
   }

    public static String buildCartKey(Long mid){

        return "member"+":"+mid;
    }


    public static String buildOrderKey(Long mid){

        return "member"+":"+mid;
    }
}
