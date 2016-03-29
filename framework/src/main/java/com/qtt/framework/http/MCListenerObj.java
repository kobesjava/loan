package com.qtt.framework.http;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.qtt.framework.util.NetworkUtil;

/**
 * Created by shejian on 2015/4/14.
 */
public class MCListenerObj<T> implements Response.ErrorListener, Response.Listener<T> {

    IObjResListener<T> mIObjResListener;

    private Context context;

    public String urlStr;

    public MCListenerObj(IObjResListener<T> mIObjResListener) {
        this.mIObjResListener = mIObjResListener;
    }

    public MCListenerObj(IObjResListener<T> mIObjResListener, String url) {
        this.mIObjResListener = mIObjResListener;
        this.urlStr = url;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private MCListenerObj() {
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        String error = "";
        if (volleyError instanceof TimeoutError) {
            error = "请求超时";
        } else if (volleyError instanceof ServerError) {
            error = "服务器异常,请稍后再试";
        } else if (volleyError instanceof NoConnectionError) {
            if (!NetworkUtil.isOpenNetwork(context)) {
                error = "网络断开,请联网再试";
            } else {
                error = "服务器异常,请稍后再试";
            }
        } else if (volleyError instanceof NetworkError) {
            error = "网络不好,请稍后再试";
        } else {
            error = "请求异常";
        }
        try{
            if (context != null && context.getResources() != null)
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            e.printStackTrace();
        }
        mIObjResListener.onFail(volleyError, urlStr);
    }

    @Override
    public void onResponse(T s) {
        mIObjResListener.onSuccess(s, urlStr);
    }

    public static interface IObjResListener<T> {

        public void onSuccess(T t, String url);

        public void onFail(Exception exception, String url);

    }

}
