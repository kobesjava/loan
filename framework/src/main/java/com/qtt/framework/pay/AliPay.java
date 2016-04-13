package com.qtt.framework.pay;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Base64;

import com.qtt.framework.pay.alipay.AliPayPresenter;
import com.qtt.framework.pay.request.PayBillRequest;
import com.qtt.framework.util.LogUtil;

/**
 * Created by Jer on 2015/11/9.
 */
public class AliPay extends BasePay {

    public AliPay(Activity mActivity, PayBillRequest payBillRequest, PayPresenter.ICompletePayListener iCompletePayListener) {
        super(mActivity, payBillRequest, iCompletePayListener);
    }

    @Override
    void toPayForInfo(PayBillRequest payBillResponse) {
        String payInfo = payBillResponse.getPayInfo();
        LogUtil.d("PAY", "alipay info:" + payInfo.toString());
        AliPayPresenter aliPayPresenter = new AliPayPresenter(mActivity, this);
        String decoded = new String(Base64.decode(payInfo, Base64.DEFAULT));
        aliPayPresenter.pay(decoded);
    }
}
