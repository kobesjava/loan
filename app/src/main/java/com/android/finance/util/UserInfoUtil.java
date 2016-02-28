package com.android.finance.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.android.finance.app.MyApplication;
import com.android.finance.bean.user.UserInfo;

/**
 * 用户登录信息存取类
 */
public class UserInfoUtil {

    private static final String USER_INFO = "USER_INFO";
    private static final String USER_CALL_INFO = "USER_CALL_INFO";

    /**
     * 保存登录用户信息
     *
     * @param context
     * @param res
     */
    public static void saveUserInfo(Context context, UserInfo res) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("id", res.getId());
        editor.putString("name", res.getName());
        editor.putString("mobile", res.getMobile());
        editor.apply();
    }

    /**
     * 清除登录用户信息
     *
     * @param context
     */
    public static void cleanUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static UserInfo getUserInfo() {
        UserInfo agentLoginResponse = getLocalLoginResponse(MyApplication.getInstance());

        if (agentLoginResponse == null) {
            agentLoginResponse = new UserInfo();
        }
        return agentLoginResponse;
    }

    /**
     * 获取登录用户ID和名称
     *
     * @param context
     * @return
     */
    public static UserInfo getLocalLoginResponse(Context context) {
        if (context == null) context = MyApplication.getInstance();
        if (context == null) return null;

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String name = sharedPreferences.getString("name", "");
        String mobile = sharedPreferences.getString("mobile", "");

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(mobile)) return null;

        UserInfo res = new UserInfo();
        res.setId(id);
        res.setName(name);
        res.setMobile(mobile);
        return res;
    }

    public static boolean isHandPhone(Context context) {
        return context.getSharedPreferences(USER_CALL_INFO, Context.MODE_PRIVATE).getBoolean("isHandPhone", false);
    }

    public static void saveUserHandlePhone(Context context, boolean isHandlePhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CALL_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean("isHandPhone", isHandlePhone);
        editor.apply();
    }

    public static void saveUserRingingPhone(Context context, boolean isRingingPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CALL_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean("isRingingPhone", isRingingPhone);
        editor.apply();
    }

    public static boolean isRingingPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CALL_INFO, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isRingingPhone", false)) {
            return true;
        }
        return false;
    }

    public static boolean isHandOrRingingPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CALL_INFO, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isHandPhone", false) || sharedPreferences.getBoolean("isRingingPhone", false)) {
            return true;
        }
        return false;
    }
}
