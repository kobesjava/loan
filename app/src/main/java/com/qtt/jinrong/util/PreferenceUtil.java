package com.qtt.jinrong.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.qtt.jinrong.app.MyApplication;

/**
 * @author yanxin
 */
public class PreferenceUtil {

    private final String PREFERENCE_NAME = "MY_PREFERENCE_NAME";

    private final String USER_INFO = "USER_INFO";

    private static PreferenceUtil instence;

    private PreferenceUtil() {

    }

    public static PreferenceUtil build() {
        if(instence == null) instence = new PreferenceUtil();
        return instence;
    }

    public void putBoolean(String key,boolean val) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,val);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public String getString(String key) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public void putString(String key,String val) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,val);
        editor.apply();
    }

}
