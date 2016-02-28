package com.android.finance.ui.fragment.main;

import android.os.Bundle;

import com.android.finance.R;
import com.android.finance.bean.event.LoanTypeEvent;
import com.android.finance.bean.event.TabEvent;
import com.android.finance.bean.recommend.AdModel;
import com.android.finance.enums.LoanType;
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
 * 推荐
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_recommend)
public class RecommendFragment extends BaseFragment implements IRecommendView {

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

    @AfterViews
    public void initView() {
        mTitltBar.setTitle(getString(R.string.recommend_title));
        mTitltBar.hideLeft();

        mIRecommendPresenter.requestAd();
    }

    @Click(R.id.btnLoanEnterprise)
    void clickBtnLoanEnterprise() {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(LoanType.企业贷款);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(1);
        EventBus.getDefault().post(tabEvent);
    }

    @Click(R.id.btnLoanWorker)
    void clickBtnLoanWorker() {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(LoanType.上班族贷款);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(1);
        EventBus.getDefault().post(tabEvent);
    }

    @Click(R.id.btnLoanCredit)
    void clickBtnLoanCredit() {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(LoanType.信用贷款);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(2);
        EventBus.getDefault().post(tabEvent);
    }

    @Click(R.id.btnMore)
    void clickBtnMore() {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(null);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(1);
        EventBus.getDefault().post(tabEvent);
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
