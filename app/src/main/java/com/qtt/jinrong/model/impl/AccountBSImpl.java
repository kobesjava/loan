package com.qtt.jinrong.model.impl;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;
import com.qtt.jinrong.http.action.AccountReqsAction;
import com.qtt.jinrong.model.IAccountBS;

/**
 * Created by yanxin on 16/3/8.
 */
public class AccountBSImpl implements IAccountBS {

    @Override
    public void requestFinancingDemands(Context context, FinancingDemandsRequest request, MCListenerObj.IObjResListener listener) {
        AccountReqsAction.requestFinancingDemands(context,request,listener);
    }

}
