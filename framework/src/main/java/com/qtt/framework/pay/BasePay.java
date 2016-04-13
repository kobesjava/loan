package com.qtt.framework.pay;

import android.app.Activity;

import com.qtt.framework.pay.request.PayBillRequest;

/**
 * Created by Jer on 2015/11/9.
 * 实现支付工具多态类
 */
public abstract class BasePay implements IwPayResultListener {

    Activity mActivity;
    PayBillRequest payBillRequest;
    PayPresenter.ICompletePayListener iCompletePayListener;

    public BasePay(Activity mActivity, PayBillRequest payBillRequest, PayPresenter.ICompletePayListener iCompletePayListener) {
        super();
        this.mActivity = mActivity;
        this.iCompletePayListener = iCompletePayListener;
        this.payBillRequest = payBillRequest;
    }

    public void pay() {
        toPayForInfo(payBillRequest);
    }

    @Override
    public void onPayComplete(Object object) {
        if(iCompletePayListener != null) {
            iCompletePayListener.paySuccess();
        }
    }

    @Override
    public void onPayStateDelay(String msgInfo, Object object) {
        if (mActivity != null) {
            PayPresenter.showResultDialog(mActivity, "" + msgInfo);
        }
    }

    @Override
    public void onPayFailInfo(String msgInfo, String errorCode,Object object) {
        PayPresenter.dismissProgressDialog();
        if(iCompletePayListener != null) {
            iCompletePayListener.payFail();
        }
    }

    abstract void toPayForInfo(PayBillRequest request);

}
