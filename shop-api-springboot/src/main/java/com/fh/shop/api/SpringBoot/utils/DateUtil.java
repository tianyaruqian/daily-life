package com.hou.springboot.api.brand.hou.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String FULL_YEAR = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    //date转string
    public static String datetoStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String result = simpleDateFormat.format(date);
        return result;
    }

    //string转date
    public static Date StrtoDate(String str,String pattern){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        SimpleDateFormat sim = new SimpleDateFormat(pattern);

        Date current = null;
        try {
            current = sim.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return current;
    }


}
