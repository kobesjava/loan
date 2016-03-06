package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoanReqsAction {

    public static void requestList(Context context,IRequest request,MCListenerObj.IObjResListener<LoanListResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_LIST,request.getParams(),listener,LoanListResponse.class);
    }

}
