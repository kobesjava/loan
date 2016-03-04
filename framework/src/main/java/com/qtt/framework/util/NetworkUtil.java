package com.qtt.framework.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

public abstract class NetworkUtil {

    /**
     * 对网络连接状态进行判断
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null && connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断是否连通网络(WIFI and MOBILE)
     *
     * @return
     */

    public static boolean isConnectNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo Mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        NetworkInfo Wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (Mobile.getState().equals(State.DISCONNECTED) && Wifi.getState().equals(State.DISCONNECTED)) {
            return false;
        }
        return true;
    }

    public static final int NetType_NONE = -1;
    public static final int NetType_2G = 0;
    public static final int NetType_3G = 1;
    public static final int NetType_4G = 2;
    public static final int NetType_WIFI = 3;

    public static int getNetWorkType(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info != null) {
            int typeInt = info.getType();
            switch (typeInt) {
                case ConnectivityManager.TYPE_WIFI:
                    return NetType_WIFI;
                case ConnectivityManager.TYPE_MOBILE:
                    int subType = info.getSubtype();
                    switch (subType) {
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                            return NetType_2G;
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                            return NetType_3G;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return NetType_4G;
                        default:
                            return NetType_NONE;
                    }
                default:
                    return NetType_NONE;
            }
        } else {
            return NetType_NONE;
        }
    }

    public static String getNetWorkTypeStr(Context context) {
        int typeInt = getNetWorkType(context);
        switch (typeInt) {
            case NetType_NONE:
                return null;
            case NetType_2G:
                return "2G";
            case NetType_3G:
                return "3G";
            case NetType_4G:
                return "4G";
            case NetType_WIFI:
                return "wifi";
            default:
                return null;
        }
    }
}
