package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.account.BaseInfoResponse;
import com.qtt.jinrong.bean.account.CarPropertyResponse;
import com.qtt.jinrong.bean.account.CreditPropertyResponse;
import com.qtt.jinrong.bean.account.DemandsResponse;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;
import com.qtt.jinrong.bean.account.HousePropertyResponse;
import com.qtt.jinrong.bean.account.IdentityResponse;
import com.qtt.jinrong.bean.account.OtherPropertyRequest;
import com.qtt.jinrong.bean.account.OtherPropertyResponse;
import com.qtt.jinrong.bean.account.OtherPropertySaveRequest;
import com.qtt.jinrong.http.Api;

/**
 * Created by yanxin on 16/3/8.
 */
public interface IAccountBS extends IBS {

    /**
     * 获取我的融资需求书状态
     * @param context
     * @param request
     * @param listener
     */
    void requestFinancingDemands(Context context, IRequest request,MCListenerObj.IObjResListener listener);

    /**
     * 获取基本信息
     * @param context
     * @param request
     * @param listener
     */
    void requestBaseInfo(Context context,IRequest request,MCListenerObj.IObjResListener<BaseInfoResponse> listener);

    /**
     * 保存基本信息
     * @param context
     * @param request
     * @param listener
     */
    void saveBaseInfo(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取其他资产信息
     * @param context
     * @param request
     * @param listener
     */
    void requestOtherProperty(Context context,IRequest request,MCListenerObj.IObjResListener<OtherPropertyResponse> listener);

    /**
     * 保存其他资产信息
     * @param context
     * @param request
     * @param listener
     */
    void saveOtherProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取房产信息
     * @param context
     * @param request
     * @param listener
     */
    void requestHouseProperty(Context context,IRequest request,MCListenerObj.IObjResListener<HousePropertyResponse> listener);

    /**
     * 保存房产信息
     * @param context
     * @param request
     * @param listener
     */
    void saveHouseProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取车产信息
     * @param context
     * @param request
     * @param listener
     */
    void requestCarProperty(Context context,IRequest request,MCListenerObj.IObjResListener<CarPropertyResponse> listener);

    /**
     * 保存车产信息
     * @param context
     * @param request
     * @param listener
     */
    void saveCarProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取信用信息
     * @param context
     * @param request
     * @param listener
     */
    void requestCreditProperty(Context context,IRequest request,MCListenerObj.IObjResListener<CreditPropertyResponse> listener);

    /**
     * 保存信用信息
     * @param context
     * @param request
     * @param listener
     */
    void saveCreditProperty(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取需求信息
     * @param context
     * @param request
     * @param listener
     */
    void requestDemands(Context context,IRequest request,MCListenerObj.IObjResListener<DemandsResponse> listener);

    /**
     * 保存需求信息
     * @param context
     * @param request
     * @param listener
     */
    void saveDemands(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);

    /**
     * 获取身份信息
     * @param context
     * @param request
     * @param listener
     */
    void requestIdentity(Context context,IRequest request,MCListenerObj.IObjResListener<IdentityResponse> listener);

    /**
     * 保存身份信息
     * @param context
     * @param request
     * @param listener
     */
    void saveIdentity(Context context,IRequest request,MCListenerObj.IObjResListener<Response> listener);


}
