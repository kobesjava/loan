package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.pay.PayResultResponse;
import com.qtt.jinrong.bean.pay.PaySignResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayReqsAction {

    public static void requestSign(Context context,IRequest request,MCListenerObj.IObjResListener<PaySignResponse> listener) {
        getPostReq7HeardInfo(context, Api.PAY_SIGN,request.getParams(),listener,PaySignResponse.class);
    }

    public static void requestResult(Context context,IRequest request,MCListenerObj.IObjResListener<PayResultResponse> listener) {
        getPostReq7HeardInfo(context, Api.PAY_RESULT,request.getParams(),listener,PayResultResponse.class);
    }

}
