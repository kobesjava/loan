package com.android.finance.ui.activity.loan;

import android.os.Bundle;

import com.android.finance.R;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_detail)
public class LoanApplyDetailActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle(getString(R.string.loan_apply_detail_title));
        mCommonTitleBar.setActivity(this);
    }

}
