package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.loan.LoanApplyDetailResponse;
import com.qtt.jinrong.bean.loan.LoanApplyListResponse;
import com.qtt.jinrong.bean.loan.LoanApplyVerifyInfoResponse;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.bean.loan.LoanProductDetailResponse;
import com.qtt.jinrong.http.Api;

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
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY,iRequest.getParams(),listener,Response.class);
    }

    /**
     * 获取贷款产品申请列表
     * @param context
     * @param request
     * @param listener
     */
    public static void requestApplyList(Context context,IRequest request,MCListenerObj.IObjResListener<LoanApplyListResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY_LIST,request.getParams(),listener,LoanApplyListResponse.class);
    }

    /**
     * 请求贷款审核要填写的个人信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestVerify(Context context,IRequest request,MCListenerObj.IObjResListener<LoanApplyVerifyInfoResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY_VERIFY_INFO,request.getParams(),listener,LoanApplyVerifyInfoResponse.class);
    }

    /**
     * 请求贷款申请详情
     * @param context
     * @param request
     * @param listener
     */
    public static void requestApplyDetail(Context context,IRequest request,MCListenerObj.IObjResListener<LoanApplyDetailResponse> listener) {
        getPostReq7HeardInfo(context, Api.LOAN_PRODUCT_APPLY_DETAIL,request.getParams(),listener,LoanApplyDetailResponse.class);
    }

}
