package com.android.finance.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * 系统功能调用工具类
 *
 * @author yanxin
 */
public final class SystemUtil {

    /**
     * 调用系统拨号功能，跳到拨号界面
     *
     * @param context
     * @param phoneNumber 电话号码
     */
    public static void callPhone(Context context, String phoneNumber) {
        if(TextUtils.isEmpty(phoneNumber) || context == null) return;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 显示或隐藏软键盘， 若软键盘处于显示状态，则执行隐藏； 若软键盘处于隐藏状态,则执行显示
     *
     * @param activity
     */
    public static void showOrHideSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @param @param activity
     * @return void
     * @throws
     * @Title: HideSoftInput
     * @Description:关闭输入法
     */
    public static void HideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 获取软键盘的打开状态
     *
     * @param activity
     * @return true, 打开状态；false,关闭状态
     */
    public static boolean isSoftInputActive(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();// isOpen若返回true，则表示输入法打开
    }

    /**
     *判断当前应用程序处于前台还是后台
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if(tasks == null || tasks.isEmpty()) return false;
        ComponentName topActivity = tasks.get(0).topActivity;
        if (topActivity != null && topActivity.getPackageName().equals(context.getPackageName()))  return true;
        return false;
    }

    /**
     * 启动蓝牙
     * @param activity
     * @param REQUEST_ENABLE_BT
     */
    public static void enableBluetooth(Activity activity,int REQUEST_ENABLE_BT) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    /**
     * 启动蓝牙
     * @param fragment
     * @param REQUEST_ENABLE_BT
     */
    public static void enableBluetooth(Fragment fragment,int REQUEST_ENABLE_BT) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        fragment.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    /**
     * 检查APP 是否获取了相应的权限
     * @param context
     * @param permisson
     * @return
     */
    public static final boolean isEnablePermission(Context context,String permisson) {
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permisson, context.getPackageName()));
        return permission;
    }

    /**
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断GPS是否打开
     * @return
     */
    public static boolean checkGpsIsOpen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 设置GPS
     * @param context
     */
    public static void setUpGps(Context context) {
        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

}
