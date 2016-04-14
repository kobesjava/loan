package com.qtt.framework.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.qtt.framework.pay.IwPayResultListener;
import com.qtt.framework.util.LogUtil;

import java.io.Serializable;

/**
 * Created by Jer on 2015/9/18.
 */
public class AliPayPresenter {
    AliPayConstants aliPayConstants;
    Activity payActivity;
    IwPayResultListener iwPayResultListener;

    public AliPayPresenter(Activity payActivity, IwPayResultListener iwPayResultListener) {
        aliPayConstants = new AliPayConstants();
        this.payActivity = payActivity;
        this.iwPayResultListener = iwPayResultListener;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(final String aliPayInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(payActivity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(aliPayInfo,false);

                LogUtil.d("PAY","result--->"+result);

                Message msg = new Message();
                msg.what = aliPayConstants.SDK_PAY_FLAG;
                msg.obj = result;
                mPayEndHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mPayEndHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AliPayConstants.SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        if (iwPayResultListener != null) {
                            iwPayResultListener.onPayComplete(payResult);
                        }
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            if (iwPayResultListener != null) {
                                iwPayResultListener.onPayStateDelay("支付结果确认中", payResult);
                            }
                        } else {
                            if (iwPayResultListener != null) {
                                iwPayResultListener.onPayFailInfo("支付失败", resultStatus,null);
                            }
                        }
                    }
                    break;
                }
                case AliPayConstants.SDK_CHECK_FLAG: {
                    Toast.makeText(payActivity, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    public static class AliPayInfo implements Serializable {
        private String subject;
        private String body;
        private String price; //阿里的支付单位是元
        private String outTradeNo;
        private String notifyUrlStr;
        private String billId;    //账单的唯一标识，用于从服务端获取支付流水号


        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getBody() {
            return body;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getNotifyUrlStr() {
            return notifyUrlStr;
        }


        public void setNotifyUrlStr(String notifyUrlStr) {
            this.notifyUrlStr = notifyUrlStr;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSubject() {
            return subject;
        }

        @Override
        public String toString() {
            return "AliPayInfo{" +
                    "subject='" + subject + '\'' +
                    ", body='" + body + '\'' +
                    ", price='" + price + '\'' +
                    ", outTradeNo='" + outTradeNo + '\'' +
                    ", notifyUrlStr='" + notifyUrlStr + '\'' +
                    ", billId='" + billId + '\'' +
                    '}';
        }
    }

    public static class AliPayConstants {
        private static final int SDK_PAY_FLAG = 1;
        private static final int SDK_CHECK_FLAG = 2;
    }


}
