package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.CreditPropertyModel;
import com.qtt.jinrong.bean.account.CreditPropertySaveRequest;
import com.qtt.jinrong.enums.CreditDebtSituationEnum;
import com.qtt.jinrong.enums.CreditLimitEnum;
import com.qtt.jinrong.enums.CreditUseStationEnum;
import com.qtt.jinrong.enums.CreditUsedLimitEnum;
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
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_credit)
public class CreditPropertyActivity extends BaseSelectActivity implements ICreditPropertyView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.useStation)
    TextView mUseStationText;
    @ViewById(R.id.totalLimit)
    TextView mTotalLimitText;
    @ViewById(R.id.count)
    InputEditText mCountText;
    @ViewById(R.id.bank)
    InputEditText mBankText;
    @ViewById(R.id.usedLimit)
    TextView mUsedLimitText;
    @ViewById(R.id.debt)
    TextView mDebtText;

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

    @Click(R.id.useStation)
    void clickUseStation() {
        mSelectView.setData(CreditUseStationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setCreInfo(CreditUseStationEnum.values()[position].getCode());
                mUseStationText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.totalLimit)
    void clickTotalLimit() {
        mSelectView.setData(CreditLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setCreMoney(CreditLimitEnum.values()[position].getCode());
                mTotalLimitText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.usedLimit)
    void clickUsedLimit() {
        mSelectView.setData(CreditUsedLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setCreUsed(CreditUsedLimitEnum.values()[position].getCode());
                mUsedLimitText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.debt)
    void clickDebt() {
        mSelectView.setData(CreditDebtSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setCreDebt(CreditDebtSituationEnum.values()[position].getCode());
                mDebtText.setText(val);
            }
        });
        show();
    }

    /***  ICreditPropertyView  ***/
    @Override
    public void onRequest(CreditPropertyModel model) {
        if(model.getCreInfo() != null) {
            CreditUseStationEnum mEnum = CreditUseStationEnum.find(model.getCreInfo());
            if(mEnum != null) mUseStationText.setText(mEnum.getTitle());
        }
        if(model.getCreMoney() != null) {
            CreditLimitEnum mEnum = CreditLimitEnum.find(model.getCreMoney());
            if(mEnum != null) mTotalLimitText.setText(mEnum.getTitle());
        }
        if(model.getCreNum() != null) mCountText.setText(String.valueOf(model.getCreNum()));
        if(!TextUtils.isEmpty(model.getCreBank())) mBankText.setText(model.getCreBank());
        if(model.getCreUsed() != null) {
            CreditUsedLimitEnum mEnum = CreditUsedLimitEnum.find(model.getCreUsed());
            if(mEnum != null) mUsedLimitText.setText(mEnum.getTitle());
        }
        if(model.getCreDebt() != null) {
            CreditDebtSituationEnum mEnum = CreditDebtSituationEnum.find(model.getCreDebt());
            if(mEnum != null) mDebtText.setText(mEnum.name());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public CreditPropertySaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(mBankText.getString())) {
            request.setCreBank(mBankText.getString());
        }
        if(!TextUtils.isEmpty(mCountText.getString())) {
            request.setCreNum(Integer.parseInt(mCountText.getString()));
        }
        return request;
    }
    /***  ICreditPropertyView  ***/
}
