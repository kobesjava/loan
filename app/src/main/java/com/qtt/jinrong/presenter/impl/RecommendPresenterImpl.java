package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListRequest;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.bean.recommend.AdResponse;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.IRecommendBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.model.impl.RecommendBSImpl;
import com.qtt.jinrong.presenter.IRecommendPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IRecommendView;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendPresenterImpl implements IRecommendPresenter {

    private IRecommendView mView;
    private IRecommendBS mIRecommendBS;
    private ILoanBS mLoanBs;

    public RecommendPresenterImpl(IRecommendView mView) {
        this.mView = mView;
        this.mIRecommendBS = new RecommendBSImpl();
        this.mLoanBs = new LoanBSImpl();
    }

    @Override
    public void requestAd() {
        mIRecommendBS.requestAd(mView.getContext(), new MCListenerObj.IObjResListener<AdResponse>() {
            @Override
            public void onSuccess(AdResponse response, String url) {
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                }
                mView.onRequestAd(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.onRequestAd(null);
            }
        });
    }

    @Override
    public void requestRecommendLoan() {
        LoanListRequest mRequest = new LoanListRequest();
        mRequest.setQuota(10);
        mRequest.setLimi(24);
        mRequest.setIdentity(1);
        mRequest.setGuaranteeWay(1);
        mRequest.setRepay(1);
        mRequest.setOrderNo(1);
        mRequest.setPageNo(1);
        mRequest.setPageSize(3);
        mLoanBs.requestList(mView.getContext(), mRequest, new MCListenerObj.IObjResListener<LoanListResponse>() {
            @Override
            public void onSuccess(LoanListResponse response, String url) {
                if (!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                } else {
                    mView.onRequestLoan(response.getData());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }
}
