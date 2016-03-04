package com.qtt.framework.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DeviceUtil {
    /**
     * 获取屏幕分辨率
     */
    public static int[] getScreenSize(Resources resources) {
        int width = resources.getDisplayMetrics().widthPixels;
        int height = resources.getDisplayMetrics().heightPixels;
        int[] result = new int[2];
        result[0] = width;
        result[1] = height;
        return result;
    }

    public static int getPixelFromDip(Context context, float dip) {
        return getPixelFromDip(context.getResources().getDisplayMetrics(), dip);
    }

    public static double calculateScreenSize(DisplayMetrics outMetrics) {
        double x = Math.pow(outMetrics.widthPixels / outMetrics.xdpi, 2);
        double y = Math.pow(outMetrics.heightPixels / outMetrics.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }

    public static double calculateScreenSize(Context context) {
        DisplayMetrics outMetrics = context.getResources().getDisplayMetrics();
        return calculateScreenSize(outMetrics);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }


    /**
     *
     */
    public static int getPixelFromDip(DisplayMetrics dm, float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm) + 0.5f);
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        String model = Build.MODEL;

        if (model == null) {
            return "";
        } else {
            return model;
        }
    }

    /**
     */
    @SuppressWarnings("deprecation")
    public static String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    /**
     */
    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * @return
     */
    public static boolean isSdCardExist() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * @return IMEI
     */
    public static String getIMEI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getDeviceId();
    }

    /**
     * @return IMSI
     */
    public static String getIMSI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getSubscriberId();
    }

    /**
     * wifi mac + imei + cpu serial
     *
     * @return
     */
    public static String getMobileUUID(Context context) {
        String uuid = "";
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiMgr != null) {
            WifiInfo info = wifiMgr.getConnectionInfo();
            if (info != null && info.getMacAddress() != null) {
                uuid = info.getMacAddress().replace(":", "");
            }
        }

        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = teleMgr.getDeviceId();
        uuid += imei;

        if (uuid != null && uuid.length() > 64) {
            uuid = uuid.substring(0, 64);
        }
        return uuid;
    }

    /**
     * 获取手机品牌 + 型号
     *
     * @return
     */
    public static String getBrandModel() {
        return Build.BRAND + "," + Build.MODEL;
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    /**
     * 获取SIM卡运营商名字
     *
     * @param context
     * @return
     */
    public static String getSimOperatorName(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getSimOperatorName();
    }

    /**
     * 获取手机号码
     * @param context
     * @return
     */
    public static String getPhoneNum(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getLine1Number();
    }

}