package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditApplyPresenter;
import com.qtt.jinrong.view.ICreditApplyView;

/**
 * Created by yanxin on 16/3/14.
 */
public class CreditApplyPresenterImpl implements ICreditApplyPresenter {

    ICreditApplyView mView;
    ICreditBS mBs;

    public CreditApplyPresenterImpl(ICreditApplyView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void apply() {
        mBs.apply(mView.getContext(), null, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                mView.onApply(response);
            }

            @Override
            public void onFail(Exception exception, String url) {
                mView.hideLoading();
            }
        });
    }

}
