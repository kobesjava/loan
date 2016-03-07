package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListRequest;
import com.qtt.jinrong.http.action.LoanReqsAction;
import com.qtt.jinrong.model.ILoanBS;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoanBSImpl implements ILoanBS {

    @Override
    public void requestList(Context context,LoanListRequest request, MCListenerObj.IObjResListener listenerObj) {
        LoanReqsAction.requestList(context,request,listenerObj);
    }

}
