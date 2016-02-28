package com.finance.framework.http;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.finance.framework.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shejian on 2015/4/14.
 */
public class CommonObjReq<T> extends Request<T> {

    private static final String TAG = "HTTP";

    private final Response.Listener<T> mListener;
    private Class<T> targetClass;
    private Map<String, String> paramsMap = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();

    public CommonObjReq(int method, String urlStr, Map<String, String> paramsMap, MCListenerObj<T> mcListener, Class<T> t) {
        super(method, urlStr, mcListener);
        targetClass = t;
        mListener = mcListener;
        this.paramsMap = paramsMap;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String str = JSON.toJSONString(paramsMap);
        LogUtil.d(TAG, "request url:" + getUrl() + "  " + str);
        return str.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    protected void deliverResponse(T response) {
        if (response != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String jsonString = new String(response.data);
        LogUtil.d(TAG, "response url:"+getUrl()+ "  " + jsonString);
        T flickrResponse = JSON.parseObject(jsonString, targetClass);
        return Response.success(flickrResponse, getCacheEntry());
    }

}
