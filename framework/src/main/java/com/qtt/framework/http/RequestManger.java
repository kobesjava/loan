package com.qtt.framework.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.qtt.framework.util.LogUtil;

/**
 * Created by yanxin on 2015/11/27.
 */
public class RequestManger {

    private final String TAG = "HTTP";

    private final Context mContext;

    private RequestQueue mRequestQueue;

    private static RequestManger mRequestManager;

    private RequestManger(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(this.mContext);
    }

    public static RequestManger getInstence(Context mContext) {
        if(mRequestManager == null) {
            mRequestManager = new RequestManger(mContext);
        }
        return mRequestManager;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * 取消网络请求
     * @param tag
     */
    public void cancel(final String tag) {
        BaseReqAction.getQueue(mContext).cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                if(request.getTag() != null) {
                    String rTag = request.getTag().toString();
                    if(rTag.equals(tag)) {
                        LogUtil.d(TAG, "取消网络请求" + request.getUrl());
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 取消所有的网络请求
     */
    public void cancelAll() {
        BaseReqAction.getQueue(mContext).cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                LogUtil.d(TAG, "取消网络请求" + request.getUrl());
                request.setTag(null);
                return true;
            }
        });
    }

}
