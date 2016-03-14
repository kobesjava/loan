package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ICreditBS extends IBS {

    /**
     * 请求信用卡产品列表
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestList(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 请求信用卡产品详情
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestCreditProductDetail(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 申请信用卡产品
     * @param context
     * @param iRequest
     * @param listener
     */
    void apply(Context context, IRequest iRequest, MCListenerObj.IObjResListener listener);

}
