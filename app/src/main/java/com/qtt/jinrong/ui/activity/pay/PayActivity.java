package com.qtt.jinrong.ui.activity.pay;

import android.os.Bundle;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.pay.PayModel;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/4/12.
 */
@EActivity(R.layout.activity_pay)
public class PayActivity extends BaseActivity {

    public static final String INTENT_PAY_MODEL = "INTENT_PAY_MODEL";

    @ViewById(R.id.titleBar)
    CommonTitleBar titleBar;
    @ViewById(R.id.desc)
    TextView descTxt;
    @ViewById(R.id.amount)
    TextView amountTxt;

    private PayModel payModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payModel = mIntent.getParcelableExtra(INTENT_PAY_MODEL);
    }

    @AfterViews
    void initView() {
        titleBar.setTitle("在线支付");
        titleBar.setActivity(this);

        descTxt.setText(payModel.getTitle());
        amountTxt.setText("￥"+payModel.getAmount());
    }

    @Click(R.id.btnPay)
    void clickBtnPay() {

    }

}
