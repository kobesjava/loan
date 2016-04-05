package com.qtt.jinrong.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.config.ConfigManager;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.config.NetworkManger;
import com.qtt.jinrong.task.TaskManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.qtt.framework.app.BaseUnCatchExceptionHandler;
import com.qtt.framework.config.AppConfig;
import com.qtt.framework.umeng.CommonAnalysis;

import java.io.File;

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

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryPath(new File(Constants.CACHE_DIR))
                        .build())
                /*.setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
                    @Override
                    public MemoryCacheParams get() {
                        MemoryCacheParams params = new MemoryCacheParams(20*1024*1024*8);
                        return params;
                    }
                })*/
                .build();
        Fresco.initialize(this,config);

        MemoryCacheParams params = config.getBitmapMemoryCacheParamsSupplier().get();
        LogUtil.e("FRESCO","maxCacheSize="+params.maxCacheSize);
        LogUtil.e("FRESCO","maxCacheEntrySize="+params.maxCacheEntrySize);
        LogUtil.e("FRESCO","maxCacheEntries="+params.maxCacheEntries);
        LogUtil.e("FRESCO","maxEvictionQueueEntries="+params.maxEvictionQueueEntries);
        LogUtil.e("FRESCO","maxEvictionQueueSize="+params.maxEvictionQueueSize);

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
