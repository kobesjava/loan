package com.qtt.jinrong.http;

import com.qtt.framework.config.AppConfig;

/**
 * 接口地址
 * Created by yanxin on 16/2/23.
 */
public class Api {

    private static String ROOT_URL = AppConfig.getRootUrl();

    private static final String AD_MOUDLE_PATH = "/advert";

    private static final String LOAN_MOUDLE_PATH = "/sloan";

    /** 滚动栏*/
    public final static String URL_AD = ROOT_URL + AD_MOUDLE_PATH + "/queryAdvertList.do";


    /**  loan模块  */
    public final static String LOAN_PRODUCT_LIST = ROOT_URL + LOAN_MOUDLE_PATH + "/querySloanList.do";
}
