package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.bean.loan.LoanProductDetailResponse;
import com.qtt.jinrong.http.Api;

import java.util.HashMap;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoanReqsAction {

    /**
     * 获取贷款产品列表
     * @param context
     * @param request
     * @param listener
     */
    public static void requestList(Context context,IRequest request,MCListenerObj.IObjResListener<LoanListResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_LIST,request.getParams(),listener,LoanListResponse.class);
    }

    /**
     * 获取贷款产品详情
     * @param context
     * @param request
     * @param listener
     */
    public static void requestLoanProductDetil(Context context,IRequest request,MCListenerObj.IObjResListener<LoanProductDetailResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_DETAIL,request.getParams(),listener,LoanProductDetailResponse.class);
    }

    /**
     * 申请贷款产品
     * @param context
     * @param iRequest
     * @param listener
     */
    public static void requestLoanProductApply(Context context,IRequest iRequest,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY,new HashMap<String, Object>(),listener,Response.class);
    }

    /**
     * 获取贷款产品申请列表
     * @param context
     * @param request
     * @param listener
     */
    public static void requestApplyList(Context context,IRequest request,MCListenerObj.IObjResListener<LoanListResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY_LIST,request.getParams(),listener,LoanListResponse.class);
    }

}
