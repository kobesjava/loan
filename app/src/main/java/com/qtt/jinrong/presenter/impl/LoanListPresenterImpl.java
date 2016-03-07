package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListResponse;
import com.qtt.jinrong.http.action.LoanReqsAction;
import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanListPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanListView;

/**
 * Created by yanxin on 16/3/7.
 */
public class LoanListPresenterImpl implements ILoanListPresenter {

    private ILoanListView mView;
    private ILoanBS mLoanBs;

    public LoanListPresenterImpl(ILoanListView mView) {
        this.mView = mView;
        this.mLoanBs = new LoanBSImpl();
    }

    @Override
    public void request() {
        mLoanBs.requestList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<LoanListResponse>() {
            @Override
            public void onSuccess(LoanListResponse response, String url) {
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                }
                mView.onRequest(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.onRequest(null);
            }
        });
    }

}
