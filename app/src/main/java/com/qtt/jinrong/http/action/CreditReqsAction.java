package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.credit.CreditApplyDetailResponse;
import com.qtt.jinrong.bean.credit.CreditApplyListResponse;
import com.qtt.jinrong.bean.credit.CreditListResponse;
import com.qtt.jinrong.bean.credit.CreditProductDetailResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class CreditReqsAction {

    /**
     * 获取信用卡产品列表
     * @param context
     * @param request
     * @param listener
     */
    public static void requestList(Context context,IRequest request,MCListenerObj.IObjResListener<CreditListResponse> listener) {
        getPostReq7HeardInfo(context, Api.CREDIT_PRODUCT_LIST,request.getParams(),listener,CreditListResponse.class);
    }

    /**
     * 获取信用卡产品详情
     * @param context
     * @param request
     * @param listener
     */
    public static void requestCreditProductDetil(Context context,IRequest request,MCListenerObj.IObjResListener<CreditProductDetailResponse> listener) {
        getPostReq7HeardInfo(context, Api.CREDIT_PRODUCT_DETAIL,request.getParams(),listener,CreditProductDetailResponse.class);
    }

    /**
     * 申请信用卡产品
     * @param context
     * @param iRequest
     * @param listener
     */
    public static void requestCreditProductApply(Context context,IRequest iRequest,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.CREDIT_PRODUCT_APPLY,iRequest.getParams(),listener,Response.class);
    }

    /**
     * 信用卡申请列表
     * @param context
     * @param iRequest
     * @param listener
     */
    public static void requestApplyList(Context context,IRequest iRequest,MCListenerObj.IObjResListener<CreditApplyListResponse> listener) {
        getPostReq7HeardInfo(context, Api.CREDIT_PRODUCT_APPLY_LIST,iRequest.getParams(),listener,CreditApplyListResponse.class);
    }

    /**
     * 信用卡申请详情
     * @param context
     * @param iRequest
     * @param listener
     */
    public static void requestApplyDetail(Context context,IRequest iRequest,MCListenerObj.IObjResListener<CreditApplyDetailResponse> listener) {
        getPostReq7HeardInfo(context, Api.CREDIT_PRODUCT_APPLY_DETAIL,iRequest.getParams(),listener,CreditApplyDetailResponse.class);
    }

}
