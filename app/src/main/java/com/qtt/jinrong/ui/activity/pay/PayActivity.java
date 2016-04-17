package com.qtt.jinrong.ui.activity.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qtt.framework.pay.request.PayBillRequest;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.app.MyApplication;
import com.qtt.jinrong.bean.pay.PayIntent;
import com.qtt.jinrong.bean.pay.PayResultRequest;
import com.qtt.jinrong.bean.pay.PayResultResponse;
import com.qtt.jinrong.bean.pay.PaySignRequest;
import com.qtt.jinrong.bean.pay.PaySignResponse;
import com.qtt.jinrong.presenter.IPayPresenter;
import com.qtt.jinrong.presenter.impl.PayPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.dialog.MyDialog;
import com.qtt.jinrong.view.IPayView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/4/12.
 */
@EActivity(R.layout.activity_pay)
public class PayActivity extends BaseActivity implements IPayView{

    public static final String INTENT_PAY_MODEL = "INTENT_PAY_MODEL";

    @ViewById(R.id.titleBar)
    CommonTitleBar titleBar;
    @ViewById(R.id.desc)
    TextView descTxt;
    @ViewById(R.id.amount)
    TextView amountTxt;

    private PayIntent payIntent;
    private PaySignRequest signRequest;
    private PayResultRequest resultRequest;

    private IPayPresenter payPresenter;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payIntent = mIntent.getParcelableExtra(INTENT_PAY_MODEL);
        payPresenter = new PayPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        titleBar.setTitle("在线支付");
        titleBar.setActivity(this);

        descTxt.setText(payIntent.getTitle());
        amountTxt.setText("￥"+ payIntent.getAmount());
    }

    @Click(R.id.btnPay)
    void clickBtnPay() {
        signRequest = new PaySignRequest();
        signRequest.setUserId(getUserId());

        if (myDialog == null) {
            myDialog = new MyDialog.Builder(this).setCancelable(false)
                    .showTitle(false).setShowButton(false,false)
                    .showMessage(true).showProgress(true).create();
            mDialog = myDialog.getDialog();
        }
        myDialog.setMessage("生成订单...");
        myDialog.show();
        payPresenter.requestSign();
    }

    /** IPayView  **/
    @Override
    public PaySignRequest getPaySignRequest() {
        return signRequest;
    }

    @Override
    public void onRequestSign(PaySignResponse response) {
        myDialog.setMessage("跳转支付...");
        payPresenter.pay();
    }

    @Override
    public PayResultRequest getPayResultRequest() {
        return null;
    }

    @Override
    public void onRequestResult(PayResultResponse response) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public PayBillRequest getPayBillRequest() {
        PayBillRequest request = new PayBillRequest();
        request.setPartner("2088101568358171");
        request.setSeller("2088101568358171");
        request.setBody("测试商品详情");
        request.setNotify_url("http://www.qttjinrong.com");
        request.setOrderId("838447347989");
        request.setPrice(0.01f);
        request.setSubject("会员付款");
        return request;
    }

    @Override
    public void onPaySuccess() {
        //获取服务端支付结果
        myDialog.setMessage("支付结果确认中...");
        payPresenter.requestPayResult();
    }

    @Override
    public void onPayFail() {
        hideDialog();
        Intent intent = new Intent(MyApplication.getInstance(), GeneratedClassUtils.get(PayResultActivity.class));
        intent.putExtra(PayResultActivity.INTENT_RESULT,false);
        startActivity(intent);
    }
    /** IPayView  **/

}
