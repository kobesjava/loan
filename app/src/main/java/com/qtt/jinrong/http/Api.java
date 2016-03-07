package com.qtt.jinrong.http;

import com.qtt.framework.config.AppConfig;

/**
 * 接口地址
 * Created by yanxin on 16/2/23.
 */
public class Api {

    private static String ROOT_URL = AppConfig.getRootUrl();

    private static final String MOUDLE_AD_PATH = "/advert";
    private static final String MOUDLE_LOAN_PATH = "/sloan";
    private static final String MOUDLE_USER_PATH = "/user";

    /**  滚动栏  **/
    public final static String URL_AD = ROOT_URL + MOUDLE_AD_PATH + "/queryAdvertList.do";

    /**  loan模块  **/
    //贷款产品列表
    public final static String LOAN_PRODUCT_LIST = ROOT_URL + MOUDLE_LOAN_PATH + "/querySloanList.do";

    /**  user模块  **/
    //密码登录
    public final static String USER_LOGIN_PWD = ROOT_URL + MOUDLE_USER_PATH + "/login.do";
    //验证码登录
    public final static String USER_LOGIN_CODE = ROOT_URL + MOUDLE_USER_PATH + "/loginByDynamic.do";
    //注册
    public final static String USER_REGIST = ROOT_URL + MOUDLE_USER_PATH + "/register.do";
    //验证码
    public final static String USER_CODE = ROOT_URL + MOUDLE_USER_PATH + "/getVerifyCode.do";
    //重新设置密码
    public final static String USER_RESET_PWD = ROOT_URL + MOUDLE_USER_PATH + "/resetPwd.do";
}
