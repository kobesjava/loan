package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.CreditPropertyModel;
import com.qtt.jinrong.bean.account.CreditPropertySaveRequest;
import com.qtt.jinrong.enums.CreditDebtSituationEnum;
import com.qtt.jinrong.enums.CreditOverdueEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.CreditTotalLimitEnum;
import com.qtt.jinrong.enums.CreditUsedLimitEnum;
import com.qtt.jinrong.enums.MonthAverageRepayEnum;
import com.qtt.jinrong.presenter.ICreditPropertyPresenter;
import com.qtt.jinrong.presenter.impl.CreditPropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.ICreditPropertyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 信用信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_credit)
public class CreditPropertyActivity extends BaseSelectActivity implements ICreditPropertyView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.creditSituation)
    TextView creditSituationText;
    @ViewById(R.id.creditSitutationMore)
    View creditSitutationMore;
    @ViewById(R.id.creditOverdueSituation)
    TextView creditOverdueSituationText;

    @ViewById(R.id.creditCardTotalLimit)
    TextView creditCardTotalLimitText;
    @ViewById(R.id.creditCardMore)
    View creditCardMore;
    @ViewById(R.id.creditCardCount)
    InputEditText creditCardCountEdit;
    @ViewById(R.id.creditCardbank)
    InputEditText creditCardbankEdit;
    @ViewById(R.id.creditCardHasUsed)
    TextView creditCardHasUsedText;

    @ViewById(R.id.creditDebtSituation)
    TextView creditDebtSituationText;
    @ViewById(R.id.creditDebtMore)
    View creditDebtMore;
    @ViewById(R.id.creditDebtInstitutionName)
    InputEditText creditDebtInstitutionNameEdit;
    @ViewById(R.id.creditDebtBalance)
    InputEditText creditDebtBalanceEdit;
    @ViewById(R.id.creditMonthAverageRepayment)
    TextView creditMonthAverageRepaymentText;

    CreditPropertySaveRequest request;
    ICreditPropertyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request = new CreditPropertySaveRequest();
        request.setUserId(getUserId());
        mPresenter = new CreditPropertyPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_credit_title));
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE, getString(R.string.save));
        mTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                mPresenter.save();
            }
        });

        mPresenter.request();
    }

    //信用情况
    @Click(R.id.creditSituation)
    void clickcreditSituation() {
        mSelectView.setData(CreditSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditSituationEnum mEnum = CreditSituationEnum.values()[position];
                request.setCreInfo(mEnum.getCode());
                creditSituationText.setText(val);
                if (mEnum.equals(CreditSituationEnum.有逾期))
                    creditSitutationMore.setVisibility(View.VISIBLE);
                else creditSitutationMore.setVisibility(View.GONE);
            }
        });
        show();
    }
    //逾期情况
    @Click(R.id.creditOverdueSituation)
    void clickcreditOverdueSituation() {
        mSelectView.setData(CreditOverdueEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditOverdueEnum mEnum = CreditOverdueEnum.values()[position];
                request.setOverdue(mEnum.getCode());
                creditOverdueSituationText.setText(val);
            }
        });
        show();
    }

    //信用卡总额度
    @Click(R.id.creditCardTotalLimit)
    void clickcreditCardTotalLimit() {
        mSelectView.setData(CreditTotalLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditTotalLimitEnum mEnum = CreditTotalLimitEnum.values()[position];
                request.setCreMoney(mEnum.getCode());
                creditCardTotalLimitText.setText(val);
                if(mEnum.equals(CreditTotalLimitEnum.无信用卡)) creditCardMore.setVisibility(View.GONE);
                else creditCardMore.setVisibility(View.VISIBLE);
            }
        });
        show();
    }
    //已使用额度
    @Click(R.id.creditCardHasUsed)
    void clickcreditCardHasUsed() {
        mSelectView.setData(CreditUsedLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditUsedLimitEnum mEnum = CreditUsedLimitEnum.values()[position];
                request.setCreUsed(mEnum.getCode());
                creditCardHasUsedText.setText(val);
            }
        });
        show();
    }

    //欠款情况
    @Click(R.id.creditDebtSituation)
    void clickDebt() {
        mSelectView.setData(CreditDebtSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditDebtSituationEnum mEnum = CreditDebtSituationEnum.values()[position];
                request.setCreDebt(mEnum.getCode());
                creditDebtSituationText.setText(val);
                if(mEnum.equals(CreditDebtSituationEnum.无欠款)) creditDebtMore.setVisibility(View.GONE);
                else creditDebtMore.setVisibility(View.VISIBLE);
            }
        });
        show();
    }
    //月均还款额
    @Click(R.id.creditMonthAverageRepayment)
    void clickcreditMonthAverageRepayment() {
        mSelectView.setData(MonthAverageRepayEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                MonthAverageRepayEnum mEnum = MonthAverageRepayEnum.values()[position];
                request.setCreUsed(mEnum.getCode());
                creditMonthAverageRepaymentText.setText(val);
            }
        });
        show();
    }


    /***  ICreditPropertyView  ***/
    @Override
    public void onRequest(CreditPropertyModel model) {
        CreditSituationEnum csEnum = CreditSituationEnum.find(model.getCreInfo());
        if(csEnum != null) {
            creditSituationText.setText(csEnum.getTitle());
            if(csEnum.equals(CreditSituationEnum.有逾期)) {
                creditSitutationMore.setVisibility(View.VISIBLE);
            }
            CreditOverdueEnum coEnum = CreditOverdueEnum.find(model.getOverdue());
            if(coEnum != null) creditOverdueSituationText.setText(coEnum.name());
        }

        CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(model.getCreMoney());
        if(ctlEnum != null) {
            creditCardTotalLimitText.setText(ctlEnum.getTitle());
            if(!ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
                creditCardMore.setVisibility(View.VISIBLE);
            }
            if(model.getCreNum() != null) creditCardCountEdit.setText(String.valueOf(model.getCreNum()));
            if(!TextUtils.isEmpty(model.getCreBank())) creditCardbankEdit.setText(model.getCreBank());
            CreditUsedLimitEnum culEnum = CreditUsedLimitEnum.find(model.getCreUsed());
            if(culEnum != null) creditCardHasUsedText.setText(culEnum.getTitle());
        }

        CreditDebtSituationEnum cdsEnum = CreditDebtSituationEnum.find(model.getCreDebt());
        if(cdsEnum != null) {
            creditDebtSituationText.setText(cdsEnum.name());
            if(!cdsEnum.equals(CreditDebtSituationEnum.无欠款)) {
                creditDebtMore.setVisibility(View.VISIBLE);
            }
            if(!TextUtils.isEmpty(model.getCreDebtName())) creditDebtInstitutionNameEdit.setText(model.getCreDebtName());
            if(model.getCreDebtAmt() != null) creditDebtBalanceEdit.setText(String.valueOf(model.getCreDebtAmt()));
            MonthAverageRepayEnum marEnum = MonthAverageRepayEnum.find(model.getCreMonthRepay());
            if(marEnum != null) creditMonthAverageRepaymentText.setText(marEnum.getTitle());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public CreditPropertySaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(creditCardCountEdit.getString())) request.setCreNum(Integer.valueOf(creditCardCountEdit.getString()));
        if(!TextUtils.isEmpty(creditCardbankEdit.getString())) request.setCreBank(creditCardbankEdit.getString());
        if(!TextUtils.isEmpty(creditDebtInstitutionNameEdit.getString())) request.setCreDebtName(creditDebtInstitutionNameEdit.getString());
        if(!TextUtils.isEmpty(creditDebtBalanceEdit.getString())) request.setCreDebtAmt(Integer.valueOf(creditDebtBalanceEdit.getString()));
        return request;
    }
    /***  ICreditPropertyView  ***/
}
