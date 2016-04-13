package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.bean.pay.PaySignRequest;

/**
 * Created by yanxin on 16/4/13.
 */
public interface IPayBS extends IBS {

    /**
     * 获取签名
     */
    void requestSign(Context context, IRequest request,MCListenerObj.IObjResListener listener);

    /**
     * 获取支付结果
     */
    void requestResult(Context context, IRequest request,MCListenerObj.IObjResListener listener);

}
