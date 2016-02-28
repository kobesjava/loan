package com.android.finance.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.finance.config.ConfigManager;
import com.android.finance.config.NetworkManger;
import com.android.finance.task.TaskManager;
import com.finance.framework.app.BaseUnCatchExceptionHandler;
import com.finance.framework.config.AppConfig;
import com.finance.framework.umeng.CommonAnalysis;

/**
 * @author yanxin
 */
public class MyApplication extends Application {

    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        initAppStatus();
        initConfig();
        startTask();
        CommonAnalysis.init(getApplicationContext());
        BaseUnCatchExceptionHandler crashHandler = BaseUnCatchExceptionHandler.getInstance();
        crashHandler.init(this);
    }

    /**
     * 初始化App配置数据
     */
    private void initAppStatus() {
        // 初始化版本信息
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            AppConfig.versionName = pi.versionName;
            AppConfig.versionCode = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 初始化Application
        AppConfig.application = application;
    }

    /**
     * 加载配置文件
     */
    private void initConfig() {
        ConfigManager.build().init();
        NetworkManger.build().init();
    }

    /**
     * 开启系统任务
     */
    private void startTask() {
        new TaskManager().init();
    }

}
