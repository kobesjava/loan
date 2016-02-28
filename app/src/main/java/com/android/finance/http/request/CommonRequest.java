package com.android.finance.http.request;

import com.android.finance.app.MyApplication;
import com.android.finance.bean.Response;
import com.android.finance.bean.event.LoginExpired;
import com.android.finance.config.Constants;
import com.android.finance.util.PreferenceUtil;
import com.android.volley.AuthFailureError;
import com.finance.framework.http.CommonObjReq;
import com.finance.framework.http.MCListenerObj;
import com.finance.framework.http.RequestManger;
import com.finance.framework.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * @author yanxin
 * @param <T>
 */
public class CommonRequest<T extends Response> extends CommonObjReq<T> {

    private final String TAG = "HTTP";

    public Map<String, String> headerInfo;

    public CommonRequest(int method, String urlStr, Map<String, String> paramsMap, MCListenerObj<T> mcListener, Class<T> t,HashMap<String, String> headerInfo) {
        this(method, urlStr, paramsMap,mcListener,t);
        this.headerInfo = headerInfo;
    }

    public CommonRequest(int method, String urlStr, Map<String, String> paramsMap, MCListenerObj<T> mcListener, Class<T> t) {
        super(method, urlStr, paramsMap,mcListener,t);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headerInfo;
    }

    @Override
    public void deliverResponse(T response) {
        if(response != null && response.getErrorCode() == Constants.LOGIN_EXPIRED) {
            if(PreferenceUtil.build().getBoolean("LoginExpired")) return;
            PreferenceUtil.build().putBoolean("LoginExpired", true);
            LogUtil.d(TAG, "登录失效 立即取消所有网络请求");
            RequestManger.getInstence(MyApplication.getInstance()).cancelAll();
            EventBus.getDefault().post(new LoginExpired());
        } else {
            super.deliverResponse(response);
        }
    }

}
