package com.qtt.framework.config;

import android.app.Application;

public class AppConfig {

    /**
     * 应用初始化必须赋值
     */
    public static Application application = null;
    public static int versionCode;
    public static String versionName = "";

    public static String UID = "-1"; // 已登录用户的Uid，默认为-1
    public static String u_ticket = ""; // 已登录用户的cookie，默认""

    public static String channelNo = "qtt";

    public static String host_name = "";
    public static int host_port;
    public static String host_protocol = "http";
    public static String host_path = "";

    public static String host_env = "";

    public static String getRootUrl() {
        return host_protocol + "://" + host_name + ":" + host_port + host_path;
    }

    public static String getDomain() {
        return host_protocol + "://" + host_name + ":" + host_port;
    }

}
