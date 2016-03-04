
package com.qtt.framework.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * 发起网络请求的基本类
 * public static Request<?> getAllTab(MCListener.IStrResListener iStrResListener) {
 * HashMap<String,String> paramsMap=new HashMap<>();
 * return createCommonReq(AppConstants.BASE_URL, paramsMap, iStrResListener);
 * }
 * @author yanxin
 */
public class BaseReqAction {

    /**
     * Get获取Str请求函数
     *
     * @param mContext
     * @param url
     * @param paramsMap
     * @param iStrResListener
     * @return
     */
    public static Request<?> createGetReqToQu(Context mContext, String url, HashMap<String, Object> paramsMap, MCListener.IStrResListener iStrResListener, final HashMap<String, String> headerInfo) {
        return createReqToQue(mContext, getCommStrReq(Request.Method.GET, url, paramsMap, iStrResListener, headerInfo));
    }

    /**
     * Post获取Str请求函数
     *
     * @param mContext
     * @param url
     * @param paramsMap
     * @param iStrResListener
     * @return
     */
    public static Request<?> createPostReqToQu(Context mContext, String url, HashMap<String, Object> paramsMap, MCListener.IStrResListener iStrResListener, final HashMap<String, String> headerInfo) {
        return createReqToQue(mContext, getCommStrReq(Request.Method.POST, url, paramsMap, iStrResListener, headerInfo));
    }

    public static CommonReq getCommStrReq(int method, String urlStr, HashMap<String, Object> paramsMap, MCListener.IStrResListener iStrResListener, final HashMap<String, String> headerInfo) {
        return new CommonReq(method, urlStr, paramsMap, new MCListener(iStrResListener, urlStr)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headerInfo;
            }
        };
    }

    /**
     * Get请求获取对象的函数
     *
     * @param mContext
     * @param url
     * @param paramsMap
     * @param iStrResListener
     * @param responseClass
     * @param <T>
     * @return
     */
    public static <T> Request<?> createGetReqToQu(Context mContext, String url, HashMap<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass, final HashMap<String, String> headerInfo) {
        return createReqToQue(mContext, getCommObjReq(Request.Method.GET, url, paramsMap, iStrResListener, responseClass, headerInfo));
    }

    /**
     * Post请求获取对象的接口
     *
     * @param mContext
     * @param url
     * @param paramsMap
     * @param iStrResListener
     * @param responseClass
     * @param <T>
     * @return
     */
    public static <T> Request<?> createPostReqToQu(Context mContext, String url, HashMap<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass, final HashMap<String, String> headerInfo) {
        return createReqToQue(mContext, getCommObjReq(Request.Method.POST, url, paramsMap, iStrResListener, responseClass, headerInfo));
    }

    public static <T> CommonObjReq getCommObjReq(int method, String urlStr, HashMap<String, Object> paramsMap, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass, final HashMap<String, String> headerInfo) {

        return new CommonObjReq(method, urlStr, paramsMap, new MCListenerObj(iStrResListener, urlStr), responseClass) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headerInfo;
            }
        };
    }

    public static Request<?> createReqToQue(Context mContext, Request commonReq) {
        if(mContext != null) {
            commonReq.setTag(mContext.getClass().getSimpleName());
        }
        return getQueue(mContext).add(commonReq);
    }

    public static RequestQueue getQueue(Context mContext) {
        return RequestManger.getInstence(mContext).getRequestQueue();
    }
}
