package com.qtt.jinrong.config;

import android.os.Environment;

import java.io.File;

/**
 * 常量数据
 */
public class Constants {

    // common
    public static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SEPARATOR = File.separator;
    /** SDK 根目录*/
    public static final String FILE_ROOT_DIR = "qttloan";
    public static final String ROOT_PATH = SD_CARD_PATH + SEPARATOR + FILE_ROOT_DIR + SEPARATOR;
    public static final String APK_DIR = "apk";

    /** 登录失效码*/
    public static final int LOGIN_EXPIRED = 110001;

    /** 开启热修复*/
    public static final boolean CHECK_HOTFIX = false;

    public static final String INTENTFILTER_ACTION_INSTALL          = "INTENTFILTER_ACTION_INSTALL";            //安装
    public static final String INTENTFILTER_ACTION_CANCEL_DOWNLOAD  =  "INTENTFILTER_ACTION_CANCEL_DOWNLOAD";   //取消下载
    public static final String INTENTFILTER_ACTION_UPDATE           = "INTENTFILTER_ACTION_UPDATE";             //升级
    public static final String INTENTFILTER_ACTION_SHOW_UPDATE      = "INTENTFILTER_ACTION_SHOW_UPDATE";        //提示升级

    /** 客服电话*/
    public static final String CUSTOMER_SERVICE_PHONE = "400-685-9966";

    public static final int EXIT_INTERVAL = 2000;

    public static final int PAGE_SIZE = 10;

    /** 短信验证码发送间隔时间*/
    public static final int REQUEST_CODE_TIME = 60000;
}
