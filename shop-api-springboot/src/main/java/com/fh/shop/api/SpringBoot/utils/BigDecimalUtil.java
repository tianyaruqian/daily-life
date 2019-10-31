package com.hou.springboot.api.brand.hou.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {


    //求积，保留2位精度
    public static BigDecimal mul(String s1,String s2){
        BigDecimal bdc1 = new BigDecimal(s1);
        BigDecimal bdc2 = new BigDecimal(s2);
        return bdc1.multiply(bdc2).setScale(2);
    }
    //求和，保留2位精度
    public static BigDecimal add(String s1,String s2){
        BigDecimal bdc1 = new BigDecimal(s1);
        BigDecimal bdc2 = new BigDecimal(s2);
        return bdc1.add(bdc2).setScale(2);
    }


}
