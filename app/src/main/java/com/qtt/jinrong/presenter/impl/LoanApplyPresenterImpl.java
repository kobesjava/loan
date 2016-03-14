package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanApplyPresenter;
import com.qtt.jinrong.view.ILoanApplyView;

/**
 * Created by yanxin on 16/3/14.
 */
public class LoanApplyPresenterImpl implements ILoanApplyPresenter {

    ILoanApplyView mView;
    ILoanBS mBs;

    public LoanApplyPresenterImpl(ILoanApplyView mView) {
        this.mView = mView;
        this.mBs = new LoanBSImpl();
    }

    @Override
    public void apply() {
        mView.showLoading();
        mBs.apply(mView.getContext(), null, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.hideLoading();
                mView.onApply(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

}
