package com.finance.framework.http;


import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by shejian on 2015/4/14.
 */
public class MCListener implements Response.ErrorListener, Response.Listener<String> {

    IStrResListener mIStrResListener;

    public MCListener(IStrResListener mIStrResListener) {
        this.mIStrResListener = mIStrResListener;
    }

    public String urlStr;

    public MCListener(IStrResListener mIStrResListener, String url) {
        this.mIStrResListener = mIStrResListener;
        this.urlStr = url;
    }

    private MCListener() {
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        mIStrResListener.onFail(volleyError, urlStr);
    }

    @Override
    public void onResponse(String s) {
        mIStrResListener.onSuccess(s, urlStr);
    }

    public static interface IStrResListener {

        public void onSuccess(String mStr, String url);

        public void onFail(Exception exception, String url);

    }

}
