package com.android.finance.config;


import com.android.finance.http.action.CommonReqsAction;
import com.finance.framework.config.AppConfig;

/**
 * Created by yanxin on 2015/6/8.
 */
public class NetworkManger {

    private static NetworkManger instance;

    private NetworkManger() {
    }

    public static NetworkManger build() {
        if(instance == null) instance = new NetworkManger();
        return instance;
    }

    public void init() {
        CommonReqsAction.ROOT_URL = AppConfig.getDomain();
    }

    public boolean isDubugIP() {
        return isDev() || isTest() || isBeta();
    }

    public boolean isDev() {
        return "dev".equals(AppConfig.get_host_env());
    }

    public boolean isTest() {
        return "test".equals(AppConfig.get_host_env());
    }

    public boolean isBeta() {
        return "beta".equals(AppConfig.get_host_env());
    }

    public boolean isProd() {
        return "prod".equals(AppConfig.get_host_env());
    }

}
