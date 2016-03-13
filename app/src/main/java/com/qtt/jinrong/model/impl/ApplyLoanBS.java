package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.http.action.LoanReqsAction;
import com.qtt.jinrong.model.IApplyLoanBS;

/**
 * Created by yanxin on 16/3/14.
 */
public class ApplyLoanBS implements IApplyLoanBS {

    @Override
    public void apply(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener) {
        LoanReqsAction.requestLoanProductApply(context, iRequest,listener);
    }

}
