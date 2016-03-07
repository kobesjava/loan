package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;
import com.qtt.jinrong.bean.account.FinancingDemandsResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class AccountReqsAction {

    public static void requestFinancingDemands(Context context,FinancingDemandsRequest request,MCListenerObj.IObjResListener<FinancingDemandsResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_FINANCING_DEMANDS,request.getParams(),listener,FinancingDemandsResponse.class);
    }

}
