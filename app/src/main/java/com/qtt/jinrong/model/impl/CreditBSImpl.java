package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.http.action.CreditReqsAction;
import com.qtt.jinrong.model.ICreditBS;

/**
 * Created by yanxin on 16/3/7.
 */
public class CreditBSImpl implements ICreditBS {

    @Override
    public void requestList(Context context,IRequest request, MCListenerObj.IObjResListener listenerObj) {
        CreditReqsAction.requestList(context, request, listenerObj);
    }

    @Override
    public void requestCreditProductDetail(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj) {
        CreditReqsAction.requestCreditProductDetil(context, request, listenerObj);
    }

    @Override
    public void apply(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        CreditReqsAction.requestCreditProductApply(context, iRequest, listener);
    }
}
