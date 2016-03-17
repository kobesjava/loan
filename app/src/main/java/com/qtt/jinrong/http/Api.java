package com.qtt.jinrong.http;

import com.qtt.framework.config.AppConfig;

/**
 * 接口地址
 * Created by yanxin on 16/2/23.
 */
public class Api {

    private static String ROOT_URL = AppConfig.getRootUrl();

    private static final String MOUDLE_APP_PATH = "/app";
    private static final String MOUDLE_AD_PATH = "/advert";
    private static final String MOUDLE_LOAN_PATH = "/sloan";
    private static final String MOUDLE_CREDIT_PATH = "/credit";
    private static final String MOUDLE_USER_PATH = "/user";
    private static final String MOUDLE_ACCOUNT_PATH = "/account";

    /** 检查更新 **/
    public final static String URL_APP_UPGRADE = ROOT_URL + MOUDLE_APP_PATH + "/upgrade.do";


    /**  滚动栏  **/
    public final static String URL_AD = ROOT_URL + MOUDLE_AD_PATH + "/queryAdvertList.do";


    /**  loan模块  **/
    //贷款产品列表
    public final static String LOAN_PRODUCT_LIST = ROOT_URL + MOUDLE_LOAN_PATH + "/querySloanList.do";
    //贷款产品详情
    public final static String LOAN_PRODUCT_DETAIL  = ROOT_URL + MOUDLE_LOAN_PATH + "/querySloanDetail.do";
    //贷款产品申请
    public final static String LOAN_PRODUCT_APPLY  = ROOT_URL + MOUDLE_LOAN_PATH + "/nowApply.do";
    //贷款产品申请列表
    public final static String LOAN_PRODUCT_APPLY_LIST = ROOT_URL + MOUDLE_LOAN_PATH + "/queryApplyList.do";


    /**  credit模块  **/
    //信用卡产品列表
    public final static String CREDIT_PRODUCT_LIST = ROOT_URL + MOUDLE_CREDIT_PATH + "/queryCreditList.do";
    //信用卡产品详情
    public final static String CREDIT_PRODUCT_DETAIL  = ROOT_URL + MOUDLE_CREDIT_PATH + "/queryCreditDetail.do";
    //信用卡产品申请
    public final static String CREDIT_PRODUCT_APPLY  = ROOT_URL + MOUDLE_CREDIT_PATH + "/nowApplyCredit.do";


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


    /**  account模块  **/
    //融资需求书状态
    public final static String ACCOUNT_FINANCING_DEMANDS = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryFinancingDemand.do";
    //获取基本信息
    public final static String ACCOUNT_REQUEST_BASE = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryBaseInfo.do";
    //保存基本信息
    public final static String ACCOUNT_SAVE_BASE = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveBaseInfo.do";
    //获取其他资产
    public final static String ACCOUNT_REQUEST_OTHER_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryAsset.do";
    //保存其他资产
    public final static String ACCOUNT_SAVE_OTHER_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveAsset.do";
    //获取房产
    public final static String ACCOUNT_REQUEST_HOUSE_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryHouse.do";
    //保存房产
    public final static String ACCOUNT_SAVE_HOUSE_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveHouse.do";
    //获取车产
    public final static String ACCOUNT_REQUEST_CAR_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryCarProd.do";
    //保存车产
    public final static String ACCOUNT_SAVE_CAR_PROPERTY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveCarProd.do";
    //获取信用信息
    public final static String ACCOUNT_REQUEST_CREDIT = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryCredit.do";
    //保存信用信息
    public final static String ACCOUNT_SAVE_CREDIT = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveCredit.do";
    //获取需求信息
    public final static String ACCOUNT_REQUEST_DEMANDS = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryDemand.do";
    //保存需求信息
    public final static String ACCOUNT_SAVE_DEMANDS = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveDemand.do";
    //获取身份信息
    public final static String ACCOUNT_REQUEST_IDENTITY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/queryIdentity.do";
    //保存身份信息
    public final static String ACCOUNT_SAVE_IDENTITY = ROOT_URL + MOUDLE_ACCOUNT_PATH + "/saveIdentity.do";

}
