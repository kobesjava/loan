package com.android.finance.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.enums.CreditDebtEnum;
import com.android.finance.enums.CreditLimitEnum;
import com.android.finance.enums.CreditUseStationEnum;
import com.android.finance.enums.CreditUsedLimitEnum;
import com.android.finance.enums.MarriageEnum;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.activity.common.BaseSelectActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.SelectPopView;
import com.android.finance.ui.widget.text.InputEditText;
import com.android.finance.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_credit)
public class CreditActivity extends BaseSelectActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                ToastUtil.showShortToast("保存");
            }
        });

    }

    @Click(R.id.useStation)
    void clickUseStation() {
        mSelectView.setData(CreditUseStationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
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
                mUsedLimitText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.debt)
    void clickDebt() {
        mSelectView.setData(CreditDebtEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mDebtText.setText(val);
            }
        });
        show();
    }

}
