package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.jinrong.http.request.CommonRequest;
import com.qtt.jinrong.http.request.CommonStrReq;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.qtt.framework.config.AppConfig;
import com.qtt.framework.http.BaseReqAction;
import com.qtt.framework.http.CommonObjReq;
import com.qtt.framework.http.CommonReq;
import com.qtt.framework.http.MCListenerObj;
import com.qtt.framework.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanxin
 */
public class CommonReqsAction extends BaseReqAction {

    public static String ROOT_URL = AppConfig.getDomain();

    public static <T> void getGetReq7HeardInfo(Context mContext, String url, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        CommonObjReq commonObjReq = getCommObjReq(mContext,Request.Method.GET, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        commonObjReq.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 1f));
        createReqToQue(mContext, commonObjReq);
    }

    public static <T> void getPostReq7HeardInfo(Context mContext, String url, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        CommonObjReq commonObjReq = getCommObjReq(mContext,Request.Method.POST, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        commonObjReq.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 1f));
        createReqToQue(mContext, commonObjReq);
    }

    public static <T> void getPostReqUploadfileHeardInfo(Context mContext, String url, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        CommonObjReq commonObjReq = getCommObjReq(mContext,Request.Method.POST, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        commonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 1f));
        createReqToQue(mContext, commonObjReq);
    }

    public static void getPostReqString(Context mContext, String url, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<String> iStrResListener) {
        CommonReq commonReq = getCommReq(mContext, Request.Method.POST, url, paramsMap, iStrResListener, getHeardInfo(paramsMap));
        commonReq.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 1f));
        createReqToQue(mContext, commonReq);
    }

    private static HashMap<String, String> getHeardInfo(Map<String, Object> paramsMap) {
        Map<String, Object> map = paramsMap;
        List<Map.Entry<String, Object>> params = new ArrayList<>(map.entrySet());//
        Collections.sort(params, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                if (o1.getKey() == null || o2.getKey() == null)
                    return 0;

                return o1.getKey().compareTo(o2.getKey());
            }
        });

        String secret = "";
        for (Map.Entry<String, Object> param : params) {
            String key = param.getKey();
            Object value = param.getValue();
            secret = secret + key + "=" + value + "&";
        }
        if (secret.endsWith("&"))
            secret = secret.substring(0, secret.length() - 1);

        LogUtil.d("HTTP", secret);

        HashMap<String, String> headers = new HashMap<>();
        /*String timestamp = String.valueOf((DateUtil.getCurrentTime().getTimeInMillis() / 100000));
        String md5 = Encrypt.decryptKey(secret, timestamp, MyApplication.getInstance().getPackageName());
        headers.put(appKeyLabel, AppConfig.get_appkey());
        headers.put(appSecretLabel, md5);
        headers.put(appTime, DateUtil.getCurrentTime().getTimeInMillis() + "");
        headers.put(appVersion, AppConfig.versionName);
        headers.put(appOS, "android");
        headers.put(appIMEI, DeviceUtil.getIMEI(AppConfig.application));
        headers.put(appModel, DeviceUtil.getBrandModel());
        headers.put(userId, AppConfig.UID);
        AgentLoginResponse agentLoginResponse = UserInfoUtil.getLocalLoginResponse();
        if (null != agentLoginResponse) {
            headers.put(u_ticket, agentLoginResponse.loginTicket);
            headers.put(userId, agentLoginResponse.agentId+"");
        }
        headers.put(distance, AppConfig.distance);
        headers.put(cityId, AppConfig.cityId);*/
        headers.put("Connection", "Closed");

        return headers;
    }

    /**
     * 报文头需要的字段
     */
    private final static String appTime = "App_Time";// 当前时间
    private final static String appVersion = "ver"; // 手机端版本号
    private final static String appOS = "os"; // 手机来源 (android/ios)
    private final static String appIMEI = "imei"; // 手机唯一标志
    private final static String appModel = "model"; // 手机型号
    private final static String userId = "user_id"; // 已登录用户ID
    private final static String u_ticket = "u_ticket";// 已登录用户的Cookie
    private final static String appKeyLabel = "App-Key"; // key
    private final static String appSecretLabel = "App-Secret"; // secret
    private final static String distance = "distance";
    private final static String cityId = "cityId";

    public static <T> CommonObjReq getCommObjReq(Context mContext,int method, String urlStr, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass, final HashMap<String, String> headerInfo) {

        MCListenerObj mcListenerObj = new MCListenerObj(iStrResListener, urlStr);

        mcListenerObj.setContext(mContext.getApplicationContext());

        CommonObjReq commonObjReq = new CommonRequest(method, urlStr, paramsMap,mcListenerObj , responseClass,headerInfo);

        return commonObjReq;

    }

    public static CommonReq getCommReq(Context mContext,int method, String urlStr, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<String> iStrResListener, final HashMap<String, String> headerInfo) {

        MCListenerObj mcListenerObj = new MCListenerObj(iStrResListener, urlStr);

        mcListenerObj.setContext(mContext.getApplicationContext());

        CommonReq commonObjReq = new CommonStrReq(method, urlStr, paramsMap,mcListenerObj,headerInfo);

        return commonObjReq;

    }

}