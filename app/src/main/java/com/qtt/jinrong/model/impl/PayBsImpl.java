package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.http.action.PayReqsAction;
import com.qtt.jinrong.model.IPayBS;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayBsImpl implements IPayBS {

    @Override
    public void requestSign(Context context, IRequest request, MCListenerObj.IObjResListener listener) {
        PayReqsAction.requestSign(context, request, listener);
    }

    @Override
    public void requestResult(Context context, IRequest request, MCListenerObj.IObjResListener listener) {
        PayReqsAction.requestResult(context,request,listener);
    }
}
