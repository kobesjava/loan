package com.qtt.jinrong.presenter.impl;

import com.qtt.jinrong.model.ILoanBS;
import com.qtt.jinrong.model.impl.LoanBSImpl;
import com.qtt.jinrong.presenter.ILoanListPresenter;
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

    }

}
