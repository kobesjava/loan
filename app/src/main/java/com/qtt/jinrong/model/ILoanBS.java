package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoanBS extends IBS {

    /**
     * 请求贷款产品列表
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestList(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 请求贷款产品详情
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestLoanProductDetail(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 申请贷款产品
     * @param context
     * @param iRequest
     * @param listener
     */
    void apply(Context context,IRequest iRequest,MCListenerObj.IObjResListener listener);

    /**
     * 请求贷款产品申请列表
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestApplyList(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 请求贷款审核要填写的个人信息
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestVerify(Context context, IRequest request, MCListenerObj.IObjResListener listenerObj);

}
