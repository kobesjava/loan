package com.qtt.jinrong.http.action;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.BaseInfoResponse;
import com.qtt.jinrong.bean.account.CarPropertyResponse;
import com.qtt.jinrong.bean.account.CreditPropertyResponse;
import com.qtt.jinrong.bean.account.DemandsResponse;
import com.qtt.jinrong.bean.account.FinancingDemandsResponse;
import com.qtt.jinrong.bean.account.HousePropertyResponse;
import com.qtt.jinrong.bean.account.OtherPropertyResponse;
import com.qtt.jinrong.http.Api;

import static com.qtt.jinrong.http.action.IWBaseReqsAction.getPostReq7HeardInfo;

/**
 * Created by yanxin on 16/3/7.
 */
public class AccountReqsAction {

    /**
     * 获取融资需求书各项的完成状态
     * @param context
     * @param request
     * @param listener
     */
    public static void requestFinancingDemands(Context context,IRequest request,MCListenerObj.IObjResListener<FinancingDemandsResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_FINANCING_DEMANDS,request.getParams(),listener,FinancingDemandsResponse.class);
    }

    /**
     * 获取基本信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestBase(Context context,IRequest request,MCListenerObj.IObjResListener<BaseInfoResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_BASE,request.getParams(),listener,BaseInfoResponse.class);
    }

    /**
     * 保存基本信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveBase(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_BASE,request.getParams(),listener,Response.class);
    }

    /**
     * 获取其他资产
     * @param context
     * @param request
     * @param listener
     */
    public static void requestOtherProperty(Context context,IRequest request,MCListenerObj.IObjResListener<OtherPropertyResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_OTHER_PROPERTY,request.getParams(),listener,OtherPropertyResponse.class);
    }

    /**
     * 保存其他资产信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveOtherProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_OTHER_PROPERTY,request.getParams(),listener,Response.class);
    }

    /**
     * 获取房产信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestHouseProperty(Context context,IRequest request,MCListenerObj.IObjResListener<HousePropertyResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_HOUSE_PROPERTY,request.getParams(),listener,HousePropertyResponse.class);
    }

    /**
     * 保存房产信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveHouseProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_HOUSE_PROPERTY,request.getParams(),listener,Response.class);
    }

    /**
     * 获取车产信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestCarProperty(Context context,IRequest request,MCListenerObj.IObjResListener<CarPropertyResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_CAR_PROPERTY,request.getParams(),listener,CarPropertyResponse.class);
    }

    /**
     * 保存车产信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveCarProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_CAR_PROPERTY,request.getParams(),listener,Response.class);
    }

    /**
     * 获取信用信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestCreditProperty(Context context,IRequest request,MCListenerObj.IObjResListener<CreditPropertyResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_CREDIT,request.getParams(),listener,CreditPropertyResponse.class);
    }

    /**
     * 保存信用信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveCreditProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_CREDIT,request.getParams(),listener,Response.class);
    }

    /**
     * 获取需求信息
     * @param context
     * @param request
     * @param listener
     */
    public static void requestDemands(Context context,IRequest request,MCListenerObj.IObjResListener<DemandsResponse> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_REQUEST_DEMANDS,request.getParams(),listener,DemandsResponse.class);
    }

    /**
     * 保存需求信息
     * @param context
     * @param request
     * @param listener
     */
    public static void saveDemands(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener) {
        getPostReq7HeardInfo(context, Api.ACCOUNT_SAVE_DEMANDS,request.getParams(),listener,Response.class);
    }

}
