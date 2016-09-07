package com.qtt.framework.util;

import android.util.Log;

import com.qtt.framework.config.AppConfig;

/**
 * @author yanxin
 */
public final class LogUtil {
    LogUtil() {/* Disable Constructor */ }

    public static int v(String tag, String msg) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.v(tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.d(tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        if (AppConfig.host_env.equals("prod") )
        {
            //return -1;
        }
        return Log.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.d(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.i(tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        if (AppConfig.host_env.equals("prod") ){
            return -1;
        }
        return Log.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ){
            return -1;
        }
        return Log.w(tag, msg, tr);
    }

    public static int w(String tag, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.w(tag, tr);
    }

    public static int e(String tag, String msg) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.e(tag, msg, tr);
    }

    public static int println(int priority, String tag, String msg) {
        if (AppConfig.host_env.equals("prod") ) {
            return -1;
        }
        return Log.println(priority, tag, msg);
    }
}
