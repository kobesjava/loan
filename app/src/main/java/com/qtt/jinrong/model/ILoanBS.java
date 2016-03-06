package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListRequest;

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
    void requestList(Context context, LoanListRequest request, MCListenerObj listenerObj);

}
