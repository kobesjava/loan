package com.qtt.jinrong.ui.activity.credit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.presenter.ICreditApplyPresenter;
import com.qtt.jinrong.presenter.impl.CreditApplyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.loan.LoanApplyResultActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ICreditApplyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_credit_detail)
public class CreditDetailActivity extends BaseActivity implements ICreditApplyView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.img)
    ImageView mIcon;

    @ViewById(R.id.name)
    TextView mCreditName;

    @ViewById(R.id.free)
    TextView mFreeDays;

    @ViewById(R.id.level)
    TextView mLevelText;

    @ViewById(R.id.cashPerscent)
    TextView mCashPerscent;

    @ViewById(R.id.currency)
    TextView mCurrency;

    @ViewById(R.id.info)
    TextView mInfo;

    CreditModel creditModel;
    ICreditApplyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CreditApplyPresenterImpl(this);
    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.credit_detail_title));
        mTitleBar.setActivity(this);
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {
        showLoading();
        mPresenter.apply();
    }

    /*** ICreditApplyView ***/
    @Override
    public String getCreditId() {
        return creditModel.getId();
    }

    @Override
    public void onApply(Response response) {
        hideLoading();
        Intent intent = new Intent(this, GeneratedClassUtils.get(CreditApplyResultActivity.class));
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_SUCCESS,response.isSuccess());
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_MESSAGE,response.getMessage());
        startActivity(intent);
        finish();
    }
    /*** ICreditApplyView ***/
}
