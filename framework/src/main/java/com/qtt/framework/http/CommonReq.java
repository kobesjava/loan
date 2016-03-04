package com.qtt.framework.http;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.qtt.framework.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanxin
 */
public class CommonReq extends Request<String> {

    private static final String TAG = "HTTP";

    private final Response.Listener<String> mListener;
    protected Map<String, Object> paramsMap = new HashMap<>();
    private HashMap<String, String> headerInfo;

    // 如何传参数
    public CommonReq(int method, String urlStr, HashMap<String, Object> paramsMap, MCListener mcListener) {
        super(method, urlStr, mcListener);
        mListener = mcListener;
        this.paramsMap = paramsMap;
    }

    // 如何传参数
    public CommonReq(int method, String urlStr, Map<String, Object> paramsMap, MCListenerObj<String> mcListener,HashMap<String, String> headerInfo) {
        super(method, urlStr, mcListener);
        mListener = mcListener;
        this.headerInfo = headerInfo;
        this.paramsMap = paramsMap;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if(headerInfo == null) return super.getHeaders();
        return headerInfo;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String str = JSON.toJSONString(paramsMap);
        LogUtil.d(TAG, "request url:" + getUrl() + "  params:" + str);
        return str.getBytes();
    }

    @Override
    protected void deliverResponse(String response) {
        if (response != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String jsonString = new String(response.data);
        LogUtil.d(TAG,"request url:"+getUrl() + "  response:"+jsonString);
        return Response.success(jsonString, getCacheEntry());
    }

}
