package com.qtt.jinrong.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.qtt.jinrong.app.MyApplication;
import com.qtt.jinrong.bean.user.UserInfo;

/**
 * 用户登录信息存取类
 */
public class UserInfoUtil {

    private static final String USER_INFO = "USER_INFO";

    /**
     * 保存登录用户信息
     *
     * @param context
     * @param res
     */
    public static void saveUserInfo(Context context, UserInfo res) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("id", res.getUserId());
        editor.putString("name", res.getUsername());
        editor.putString("mobile", res.getCell());
        editor.putInt("gender", res.getGender());
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

        if (TextUtils.isEmpty(id)) return null;

        UserInfo res = new UserInfo();
        res.setUserId(id);
        res.setUsername(sharedPreferences.getString("name",""));
        res.setCell(sharedPreferences.getString("mobile",""));
        res.setGender(sharedPreferences.getInt("gender",1));
        return res;
    }

}
