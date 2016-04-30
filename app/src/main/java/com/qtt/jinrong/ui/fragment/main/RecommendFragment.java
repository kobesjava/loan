package com.qtt.jinrong.ui.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.event.CreditLevelEvent;
import com.qtt.jinrong.bean.event.LoanTypeEvent;
import com.qtt.jinrong.bean.event.TabEvent;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.recommend.AdModel;
import com.qtt.jinrong.enums.CreditLevelEnum;
import com.qtt.jinrong.enums.LoanTypeEnum;
import com.qtt.jinrong.presenter.IRecommendPresenter;
import com.qtt.jinrong.presenter.impl.RecommendPresenterImpl;
import com.qtt.jinrong.ui.activity.loan.LoanApplyListActivity;
import com.qtt.jinrong.ui.activity.loan.LoanProductDetailActivity;
import com.qtt.jinrong.ui.activity.user.FinancingNeedsActivity;
import com.qtt.jinrong.ui.adapter.RecommendAdAdapter;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.help.UiUtil;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.IndicatorView;
import com.qtt.jinrong.ui.widget.LineView;
import com.qtt.jinrong.view.IRecommendView;

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
    @ViewById(R.id.refreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewById(R.id.loanListView)
    LinearLayout loanListView;


    RecommendAdAdapter mRecommendAdAdapter;
    IRecommendPresenter mIRecommendPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIRecommendPresenter = new RecommendPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    protected void initView() {
        if (isInit) return;
        super.initView();

        mTitltBar.setTitle(getString(R.string.recommend_title));
        mTitltBar.hideLeft();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIRecommendPresenter.requestAd();
                mIRecommendPresenter.requestRecommendLoan();
            }
        });

        mIRecommendPresenter.requestAd();
        mIRecommendPresenter.requestRecommendLoan();
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
        goToCreditTab(CreditLevelEnum.不限);
    }

    @Click(R.id.moreLoan)
    void clickMoreLoan() {
        goToLoanTab(LoanTypeEnum.上班族贷款);
    }

    @Click(R.id.loanApplyList)
    void clickloanApplyList() {
        if (mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.laonMaterial)
    void clickLoanMaterial() {
        if (mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
        startActivity(intent);
    }

    /**
     * 切换到贷款tab
     *
     * @param loanTypeEnum
     */
    private void goToLoanTab(LoanTypeEnum loanTypeEnum) {
        LoanTypeEvent event = new LoanTypeEvent();
        event.setType(loanTypeEnum);
        EventBus.getDefault().post(event);
        TabEvent tabEvent = new TabEvent();
        tabEvent.setTabIndex(1);
        tabEvent.setLoanTypeEnum(loanTypeEnum);
        EventBus.getDefault().post(tabEvent);
    }

    /**
     * 切换到信用卡tab
     *
     * @param levelEnum
     */
    private void goToCreditTab(CreditLevelEnum levelEnum) {
        TabEvent tabEvent = new TabEvent();
        tabEvent.setCreditLevelEnum(levelEnum);
        tabEvent.setTabIndex(2);
        EventBus.getDefault().post(tabEvent);
        CreditLevelEvent event = new CreditLevelEvent();
        event.setLevel(levelEnum);
        EventBus.getDefault().post(event);
    }


    /******
     * IRecommendView
     *******/
    @Override
    public void onRequestAd(List<AdModel> adModels) {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if (adModels == null) return;
        if (mRecommendAdAdapter == null) {
            mRecommendAdAdapter = new RecommendAdAdapter(getActivity(), adModels);
            mIndictor.setAdapter(mRecommendAdAdapter);
        } else mRecommendAdAdapter.update(adModels);

        mIndictor.setIndicator(adModels.size());
    }

    @Override
    public void onRequestLoan(List<LoanModel> loanModels) {
        if (loanModels == null || loanModels.size() == 0) return;

        loanListView.removeAllViews();

        View view;
        SimpleDraweeView img;
        TextView title;
        TextView company;
        RatingBar rb;
        TextView interestTotal;
        TextView monthPay;
        LoanModel mLoanModel;

        for (int i = 0; i < loanModels.size(); i++) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.loan_item, null);
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            company = (TextView) view.findViewById(R.id.company);
            rb = (RatingBar) view.findViewById(R.id.rb);
            interestTotal = (TextView) view.findViewById(R.id.interest);
            monthPay = (TextView) view.findViewById(R.id.monthPay);

            mLoanModel = loanModels.get(i);
            view.setTag(mLoanModel);

            float monthRate = UiUtil.getMonthRate(mLoanModel.monthRate);
            int totalRate = UiUtil.calculateRate(mLoanModel.compound, monthRate, 12, 100000);
            totalRate += mLoanModel.monthManageFee*12+mLoanModel.onceManageFee;
            mLoanModel.setRate(totalRate + "元");
            mLoanModel.setMoney((totalRate +  100000 )/ 12 + "元");

            title.setText(mLoanModel.getTitle());
            company.setText(mLoanModel.getOwnedCompany());
            interestTotal.setText("总利息 ：" + mLoanModel.getRate());
            monthPay.setText("月供 ：" + mLoanModel.getMoney());
            rb.setRating(mLoanModel.getScore());

            try {
                Uri uri = Uri.parse(mLoanModel.getThumpImg());
                img.setImageURI(uri);
            } catch (Exception e) {
                LogUtil.d("加载图片", "URL=" + mLoanModel.getThumpImg() + " Exception=" + e.getMessage());
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanProductDetailActivity.class));
                    intent.putExtra(LoanProductDetailActivity.INTENT_LOAN, (LoanModel) v.getTag());
                    startActivity(intent);
                }
            });

            loanListView.addView(view);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getResources().getDimensionPixelOffset(R.dimen.margin_step_1));
            loanListView.addView(new LineView(getActivity()), params);
        }

    }
    /******  IRecommendView  *******/

}
