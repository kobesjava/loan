package com.qtt.jinrong.ui.activity.credit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.credit.CreditDetailModel;
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.presenter.ICreditApplyPresenter;
import com.qtt.jinrong.presenter.impl.CreditApplyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.loan.LoanApplyResultActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ICreditDetailView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 信用卡详情
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_credit_detail)
public class CreditDetailActivity extends BaseActivity implements ICreditDetailView{

    public static final String INTENT_MODEL = "INTENT_MODEL";

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;
    @ViewById(R.id.img)
    SimpleDraweeView mIcon;
    @ViewById(R.id.name)
    TextView mCreditName;
    @ViewById(R.id.free)
    TextView mFreeDays;
    @ViewById(R.id.desc)
    TextView mDescText;
    @ViewById(R.id.cashPerscent)
    TextView mCashPerscent;
    @ViewById(R.id.currency)
    TextView mCurrency;
    @ViewById(R.id.annual)
    TextView annual;
    @ViewById(R.id.freeAnnual)
    TextView freeAnnual;

    @ViewById(R.id.dayInteretst)
    TextView dayInteretst;
    @ViewById(R.id.repayMin)
    TextView repayMin;
    @ViewById(R.id.bill)
    TextView bill;

    @ViewById(R.id.fenqiApply)
    TextView fenqiApply;
    @ViewById(R.id.fenqiType)
    TextView fenqiType;
    @ViewById(R.id.fenqiRate)
    TextView fenqiRate;

    CreditModel creditModel;
    ICreditApplyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CreditApplyPresenterImpl(this);
        creditModel = mIntent.getParcelableExtra(INTENT_MODEL);
    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.credit_detail_title));
        mTitleBar.setActivity(this);

        mPresenter.request();
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
    public void onRequestDetail(CreditDetailModel model) {
        if(model == null) return;

        try {
            Uri uri = Uri.parse(model.getCreImg());
            mIcon.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片", "URL=" + model.getCreImg() + " Exception=" + e.getMessage());
        }
        mCreditName.setText(creditModel.getCreTitle());
        mFreeDays.setText("免息期: "+model.getCreFree());
        mDescText.setText("银行: "+model.getCreBank()+"   卡等级: "+model.getCreClass());
        mCashPerscent.setText("预借现金额度: "+model.getCreBor());
        mCurrency.setText("卡币种: "+model.getCreCurr());
        freeAnnual.setText("免年费政策: "+model.getCrePolicy());
        annual.setText("年费: "+model.getCreAnn());

        dayInteretst.setText("循环信用利息(日息): "+model.getCreDay());
        repayMin.setText("最低还款: "+model.getCreLowest());
        bill.setText("账单日: "+model.getCreBill());

        fenqiApply.setText("分期申请: "+model.getCreApply());
        fenqiType.setText("分期类型: "+model.getCreApptype());
        fenqiRate.setText("分期期数费率: "+model.getCreRate());
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
