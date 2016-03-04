package com.qtt.framework.app;

import android.content.Context;

import com.qtt.framework.config.AppConfig;
import com.qtt.framework.umeng.CommonAnalysis;
import com.qtt.framework.util.LogUtil;


/**
 * Created by yanxin on 2015/7/16.
 */
public class BaseUnCatchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final String TAG = "BaseUnCatchExceptionHandler";

    // CrashHandler 实例
    private static BaseUnCatchExceptionHandler INSTANCE = new BaseUnCatchExceptionHandler();

    // 程序的 Context 对象
    private Context mContext;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /** 保证只有一个 CrashHandler 实例 */
    private BaseUnCatchExceptionHandler() {
    }

    /** 获取 CrashHandler 实例 ,单例模式 */
    public static BaseUnCatchExceptionHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {

        if(!"prod".equals(AppConfig.get_host_env())) return;

        mContext = context;

        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtil.e(TAG, "异常:" + ex);
        CommonAnalysis.reportError(mContext, ex);
    }
}
