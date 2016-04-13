package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.framework.pay.AliPay;
import com.qtt.framework.pay.PayPresenter;
import com.qtt.framework.pay.request.PayBillRequest;
import com.qtt.jinrong.bean.pay.PayResultRequest;
import com.qtt.jinrong.bean.pay.PayResultResponse;
import com.qtt.jinrong.bean.pay.PaySignRequest;
import com.qtt.jinrong.bean.pay.PaySignResponse;
import com.qtt.jinrong.model.IPayBS;
import com.qtt.jinrong.model.impl.PayBsImpl;
import com.qtt.jinrong.presenter.IPayPresenter;
import com.qtt.jinrong.view.IPayView;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayPresenterImpl implements IPayPresenter {

    private IPayView iView;
    private IPayBS iPayBS;

    public PayPresenterImpl(IPayView iView) {
        this.iView = iView;
        this.iPayBS = new PayBsImpl();
    }

    @Override
    public void requestSign() {
        PaySignRequest request = iView.getPaySignRequest();
        iPayBS.requestSign(iView.getContext(), request, new MCListenerObj.IObjResListener<PaySignResponse>() {
            @Override
            public void onSuccess(PaySignResponse response, String url) {
                iView.onRequestSign(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                iView.onRequestSign(null);
            }
        });
    }

    @Override
    public void pay() {
        PayBillRequest request = iView.getPayBillRequest();
        AliPay aliPay = new AliPay(iView.getActivity(), request, new PayPresenter.ICompletePayListener() {
            @Override
            public void paySuccess() {
                iView.onPaySuccess();
            }

            @Override
            public void payFail() {
                iView.onPayFail();
            }
        });
        aliPay.pay();
    }

    @Override
    public void requestPayResult() {
        PayResultRequest request = iView.getPayResultRequest();
        iPayBS.requestResult(iView.getContext(), request, new MCListenerObj.IObjResListener<PayResultResponse>() {
            @Override
            public void onSuccess(PayResultResponse response, String url) {
                iView.onRequestResult(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                iView.onRequestResult(null);
            }
        });
    }
}
