package com.qtt.framework.umeng;

import android.content.Context;

import com.qtt.framework.config.AppConfig;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by shuaijiman on 2015/5/5.
 */
public class CommonAnalysis {
    /**
     * 默认是false。但是从xml里读取。请不要去修改这个值
     */
    public static boolean sEnable;

    /**
     * 请使用AppContext
     */
    public static void init(Context context) {
        if("prod".equals(AppConfig.get_host_env())) sEnable = true;
        else sEnable = false;
        if (!sEnable) return;
        MobclickAgent.setDebugMode(false);
        //SDK在统计Fragment时，需要关闭Activity自带的页面统计
        //然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCatchUncaughtExceptions(false);
        MobclickAgent.updateOnlineConfig(context);
    }

    /**
     * 请在自己Application的onCreate函数里，调用完父类onCreate之后，调用该函数.
     *
     * @param key
     */
    public static void setAppKey(String key) {
        if (!sEnable) {
            return;
        }
        AnalyticsConfig.setAppkey(key);
    }

    public static void onEvent(Context context, String eventId) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.onEvent(context, eventId);
    }

    public static void onPageStart(String page) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.onPageStart(page);
    }

    public static void onPageEnd(String page) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.onPageEnd(page);
    }

    public static void onResume(Context context) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.onPause(context);
    }

    public static void reportError(Context context, Throwable e) {
        if (!sEnable) {
            return;
        }
        MobclickAgent.reportError(context, e);
        MobclickAgent.onKillProcess(context);
    }
}
