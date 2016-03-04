package com.qtt.framework.util;

import android.util.Log;

import com.qtt.framework.config.AppConfig;


/**
 * Handle Log Print
 * <p/>
 * Created by dench on 2014/11/11.
 * Modify by w_xiong on 2015/01/06 新增兼容爱屋的判断条件
 */
public final class LogUtil {
    LogUtil() {/* Disable Constructor */ }

    public static int v(String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.v(tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.d(tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") )
        {
            return -1;
        }
        return Log.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.d(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.i(tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") ){
            return -1;
        }
        return Log.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ){
            return -1;
        }
        return Log.w(tag, msg, tr);
    }

    public static int w(String tag, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.w(tag, tr);
    }

    public static int e(String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.e(tag, msg, tr);
    }

    public static int println(int priority, String tag, String msg) {
        if (AppConfig.get_host_env().equals("prod") ) {
            return -1;
        }
        return Log.println(priority, tag, msg);
    }
}
