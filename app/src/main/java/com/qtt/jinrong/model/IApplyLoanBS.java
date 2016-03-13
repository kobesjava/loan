package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;

/**
 * Created by yanxin on 16/3/14.
 */
public interface IApplyLoanBS extends IBS {

    /**
     * 申请贷款产品
     * @param context
     * @param iRequest
     * @param listener
     */
    void apply(Context context,IRequest iRequest,MCListenerObj.IObjResListener listener);

}
