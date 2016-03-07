package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.account.FinancingDemandsRequest;

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
    void requestFinancingDemands(Context context, FinancingDemandsRequest request,MCListenerObj.IObjResListener listener);

}
