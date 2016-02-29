package com.android.finance.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.finance.R;
import com.android.finance.bean.event.CreditLevelEvent;
import com.android.finance.bean.event.LoanTypeEvent;
import com.android.finance.bean.event.TabEvent;
import com.android.finance.bean.recommend.AdModel;
import com.android.finance.enums.CreditLevelEnum;
import com.android.finance.enums.LoanTypeEnum;
import com.android.finance.presenter.IRecommendPresenter;
import com.android.finance.presenter.impl.RecommendPresenterImpl;
import com.android.finance.ui.adapter.RecommendAdAdapter;
import com.android.finance.ui.fragment.common.BaseFragment;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.IndicatorView;
import com.android.finance.view.IRecommendView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_recommend)
public class RecommendFragment extends BaseFragment implements IRecommendView {

    View mView;

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitltBar;

    @ViewById(R.id.indicator)
    IndicatorView mIndictor;

    RecommendAdAdapter mRecommendAdAdapter;
    IRecommendPresenter mIRecommendPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIRecommendPresenter = new RecommendPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    protected void initView() {
        if(isInit) return;
        super.initView();

        mTitltBar.setTitle(getString(R.string.recommend_title));
        mTitltBar.hideLeft();
        mIRecommendPresenter.requestAd();
    }

    @Click(R.id.btnLoanEnterprise)
    void clickBtnLoanEnterprise() {
        goToLoanTab(LoanTypeEnum.企业贷款);
    }

    @Click(R.id.btnLoanWorker)
    void clickBtnLoanWorker() {
        goToLoanTab(LoanTypeEnum.上班族贷款);
    }

    @Click(R.id.btnLoanCredit)
    void clickBtnLoanCredit() {
        goToLoanTab(LoanTypeEnum.信用贷款);
    }

    @Click(R.id.btnMoreLoan)
    void clickBtnMoreLoan() {
        goToLoanTab(null);
    }

    @Click(R.id.btnCreditCommon)
    void clickBtnCreditCommon() {
        goToCreditTab(CreditLevelEnum.普卡);
    }

    @Click(R.id.btnCreditSilver)
    void clickBtnCreditSilver() {
        goToCreditTab(CreditLevelEnum.银卡);
    }

    @Click(R.id.btnCreditGold)
    void clickBtnCreditGold() {
        goToCreditTab(CreditLevelEnum.金卡);
    }

    @Click(R.id.btnMoreCredit)
    void clickBtnMoreCredit() {
        goToCreditTab(null);
    }

    /**
     * 切换到贷款tab
     * @param loanTypeEnum
     */
    private void goToLoanTab(LoanTypeEnum loanTypeEnum) {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(loanTypeEnum);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(1);
        EventBus.getDefault().post(tabEvent);
    }

    /**
     * 切换到信用卡tab
     * @param levelEnum
     */
    private void goToCreditTab(CreditLevelEnum levelEnum) {
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(2);
        EventBus.getDefault().post(tabEvent);
        CreditLevelEvent event = new CreditLevelEvent();
        event.setLevel(levelEnum);
        EventBus.getDefault().post(event);
    }


    /******  IRecommendView  *******/
    @Override
    public void onRequestAd(List<AdModel> adModels) {
        if(mRecommendAdAdapter == null) {
            mRecommendAdAdapter = new RecommendAdAdapter(getActivity(),adModels);
            mIndictor.setAdapter(mRecommendAdAdapter);
        }
        else mRecommendAdAdapter.update(adModels);

        mIndictor.setIndicator(adModels.size());
    }
    /******  IRecommendView  *******/

}
