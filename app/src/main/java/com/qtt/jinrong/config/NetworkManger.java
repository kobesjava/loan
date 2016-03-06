package com.qtt.jinrong.config;


import com.qtt.jinrong.http.action.CommonReqsAction;
import com.qtt.framework.config.AppConfig;

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
        return "dev".equals(AppConfig.host_env);
    }

    public boolean isTest() {
        return "test".equals(AppConfig.host_env);
    }

    public boolean isBeta() {
        return "beta".equals(AppConfig.host_env);
    }

    public boolean isProd() {
        return "prod".equals(AppConfig.host_env);
    }

}
