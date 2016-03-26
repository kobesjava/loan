package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.credit.CreditListResponse;
import com.qtt.jinrong.model.ICreditBS;
import com.qtt.jinrong.model.impl.CreditBSImpl;
import com.qtt.jinrong.presenter.ICreditListPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ICreditListView;

/**
 * Created by yanxin on 16/3/7.
 */
public class CreditListPresenterImpl implements ICreditListPresenter {

    private ICreditListView mView;
    private ICreditBS mBs;

    public CreditListPresenterImpl(ICreditListView mView) {
        this.mView = mView;
        this.mBs = new CreditBSImpl();
    }

    @Override
    public void request() {
        mBs.requestList(mView.getContext(), mView.getRequest(), new MCListenerObj.IObjResListener<CreditListResponse>() {
            @Override
            public void onSuccess(CreditListResponse response, String url) {
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
