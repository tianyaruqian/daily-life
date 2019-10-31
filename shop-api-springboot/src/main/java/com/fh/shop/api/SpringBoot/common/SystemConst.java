package com.hou.springboot.api.brand.hou.common;

public final class SystemConst {

    public static final String CUREENT_USER ="user" ;
    public static final String CURRENT_LSIT="menueList";
    public static final String ALL_MENUE="allmenuelist";
    public static final String MENUE_BUTTON="buttonlist";
    public static final int LOG_SUCCESS_STATUS=1;
    public static final int LOG_ERROR_STATUS=2;
    public static final int ERROR_COUNT=3;
    public static final String PASSWORD_RESET = "1";
    public static final String IMAGE_PATH="/images/";

    public static final String COOKIE_NAME="h_id";
    public static final String DOMAIN="admin.shop.com";
    public static final int CODE_TIME_OUT=10*60;
    public static final int SESSION_TIME=10*60;
    public static final int EXPIRE_TIME=60*60;
    public static final String SECRETKEY="slfzj+3124176.";

    public static final int PRODUCT_VALID=1;
    public static final String HASH_REDIS="itemMap";
    //订单
    public static final int ORDER_STATUS_IS_PAY=20;
    //微信支付订单
    public static final int PAY_STATUS_IS_WAIT_PAY=1;
    public static final int PAY_STATUS_IS_SUCCESS=2;


}
