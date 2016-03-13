package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.model.IApplyLoanBS;
import com.qtt.jinrong.model.impl.ApplyLoanBS;
import com.qtt.jinrong.presenter.IApplyLoanPresenter;
import com.qtt.jinrong.view.ILoanApplyLoanView;

/**
 * Created by yanxin on 16/3/14.
 */
public class ApplyLoanPresenterImpl implements IApplyLoanPresenter {

    ILoanApplyLoanView mView;
    IApplyLoanBS mBs;

    public ApplyLoanPresenterImpl(ILoanApplyLoanView mView) {
        this.mView = mView;
        this.mBs = new ApplyLoanBS();
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
