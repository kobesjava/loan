package com.android.finance.ui.activity.user;

import com.android.finance.R;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.OilgaugeProgress;
import com.android.finance.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_financing_needs)
public class FinancingNeedsActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.progress)
    OilgaugeProgress mOilgaugeProgress;

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.financing_need_title));
        mTitleBar.setActivity(this);

        mOilgaugeProgress.setProgress(73);
    }

    @Click(R.id.btnrefresh)
    void clickRefresh() {
    }

}
