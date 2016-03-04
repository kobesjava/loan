package com.android.finance.ui.activity.user;

import android.content.Intent;

import com.android.finance.R;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.OilgaugeProgress;
import com.finance.framework.util.GeneratedClassUtils;

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

        mOilgaugeProgress.setProgress(75);
    }

    @Click(R.id.btnrefresh)
    void clickRefresh() {
    }

    @Click(R.id.baseView)
    void clickBaseView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(BaseInfoActivity.class));
        startActivity(intent);
    }

    @Click(R.id.needsView)
    void clickNeedsView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(DemandsActivity.class));
        startActivity(intent);
    }

    @Click(R.id.idsView)
    void clickIdsView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(IdentityActivity.class));
        startActivity(intent);
    }

    @Click(R.id.creditView)
    void clickCreditView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(CreditActivity.class));
        startActivity(intent);
    }

    @Click(R.id.houseView)
    void clickHouseView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(HousePropertyActivity.class));
        startActivity(intent);
    }

    @Click(R.id.carView)
    void clickCarView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(CarPropertyActivity.class));
        startActivity(intent);
    }

    @Click(R.id.otherView)
    void clickOtherView() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(OtherPropertyActivity.class));
        startActivity(intent);
    }

}
