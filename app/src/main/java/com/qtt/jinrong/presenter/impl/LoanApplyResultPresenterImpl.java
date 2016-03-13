package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanApplyResultPresenter;
import com.qtt.jinrong.view.ILoanApplyResultView;

/**
 * Created by yanxin on 16/3/14.
 */
public class LoanApplyResultPresenterImpl implements ILoanApplyResultPresenter {

    ILoanApplyResultView mView;
    ILoanBS mBs;

    public LoanApplyResultPresenterImpl(ILoanApplyResultView mView) {
        this.mView = mView;
        this.mBs = new LoanBSImpl();
    }

    @Override
    public void requestRecommend() {
        mBs.requestList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<LoanListResponse>() {
            @Override
            public void onSuccess(LoanListResponse response, String url) {
                if(response.isSuccess()) mView.onRequestRecommend(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }
}
