package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.FinancingDemandsModel;
import com.qtt.jinrong.presenter.IFinancingDemandPresenter;
import com.qtt.jinrong.presenter.impl.FinancingDemandPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.OilgaugeProgress;
import com.qtt.jinrong.view.IFinancingDemandsView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 我的融资需求书
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_financing_needs)
public class FinancingNeedsActivity extends BaseActivity implements IFinancingDemandsView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;
    @ViewById(R.id.progress)
    OilgaugeProgress mOilgaugeProgress;
    @ViewById(R.id.baseStatus)
    TextView baseStatus;
    @ViewById(R.id.needsStatus)
    TextView needsStatus;
    @ViewById(R.id.identiStatus)
    TextView identiStatus;
    @ViewById(R.id.creditStatus)
    TextView creditStatus;
    @ViewById(R.id.houseStatus)
    TextView houseStatus;
    @ViewById(R.id.carStatus)
    TextView carStatus;
    @ViewById(R.id.otherStatus)
    TextView otherStatus;

    IFinancingDemandPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FinancingDemandPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.financing_need_title));
        mTitleBar.setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.request();
    }

    @Click(R.id.btnrefresh)
    void clickRefresh() {
        mPresenter.request();
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
        Intent intent = new Intent(this, GeneratedClassUtils.get(CreditPropertyActivity.class));
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

    /***  IVIEW  ***/
    @Override
    public void onRequest(FinancingDemandsModel financingDemandsModel) {

        int finished = 0;

        if(financingDemandsModel.isBaseStatus()) {
            finished++;
            baseStatus.setText(getString(R.string.finished));
        }
        else baseStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isDemandStatus()) {
            finished++;
            needsStatus.setText(getString(R.string.finished));
        }
        else needsStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isIdentityStatus()) {
            finished++;
            identiStatus.setText(getString(R.string.finished));
        }
        else identiStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isCreditStatus()) {
            finished++;
            creditStatus.setText(getString(R.string.finished));
        }
        else creditStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isCarStatus()) {
            finished++;
            carStatus.setText(getString(R.string.finished));
        }
        else carStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isHouseStatus()) {
            finished++;
            houseStatus.setText(getString(R.string.finished));
        }
        else houseStatus.setText(getString(R.string.beperfect));

        if(financingDemandsModel.isAssetStatus()) {
            finished++;
            otherStatus.setText(getString(R.string.finished));
        }
        else otherStatus.setText(getString(R.string.beperfect));

        mOilgaugeProgress.setProgress(finished*100/7);
    }
    /***  IVIEW  ***/
}
