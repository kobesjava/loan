package com.qtt.jinrong.view;

import android.app.Activity;

import com.qtt.framework.pay.request.PayBillRequest;
import com.qtt.jinrong.bean.pay.PayResultRequest;
import com.qtt.jinrong.bean.pay.PayResultResponse;
import com.qtt.jinrong.bean.pay.PaySignRequest;
import com.qtt.jinrong.bean.pay.PaySignResponse;

/**
 * Created by yanxin on 16/4/13.
 */
public interface IPayView extends IView {

    /**
     * 获取签名request
     * @return
     */
    PaySignRequest getPaySignRequest();

    /**
     * 获取签名返回处理
     * @param response
     */
    void onRequestSign(PaySignResponse response);

    /**
     * 获取支付结果request
     * @return
     */
    PayResultRequest getPayResultRequest();

    /**
     * 获取支付结果返回处理
     * @return
     */
     void onRequestResult(PayResultResponse response);

    Activity getActivity();

    PayBillRequest getPayBillRequest();

    void onPaySuccess();

    void onPayFail();

}
