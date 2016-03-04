package com.android.finance.presenter.impl;

import com.android.finance.bean.recommend.AdModel;
import com.android.finance.bean.recommend.AdResponse;
import com.android.finance.model.IRecommendBS;
import com.android.finance.model.impl.RecommendBSImpl;
import com.android.finance.presenter.IRecommendPresenter;
import com.android.finance.util.ToastUtil;
import com.android.finance.view.IRecommendView;
import com.finance.framework.http.MCListenerObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/2/23.
 */
public class RecommendPresenterImpl implements IRecommendPresenter {

    private IRecommendView mIRecommendView;
    private IRecommendBS mIRecommendBS;

    public RecommendPresenterImpl(IRecommendView iRecommendView) {
        this.mIRecommendView = iRecommendView;
        this.mIRecommendBS = new RecommendBSImpl();
    }

    @Override
    public void requestAd() {

        List<AdModel> adModels = new ArrayList<AdModel>();

        AdModel adModel = new AdModel();

        adModel.setUrl("http://4006391555.com/leshifu/images/index_banner/banner_20160224.jpg");
        adModels.add(adModel);

        adModel = new AdModel();
        adModel.setUrl("http://4006391555.com/leshifu/images/index_banner.jpg");
        adModels.add(adModel);

        adModel = new AdModel();
        adModel.setUrl("http://4006391555.com/leshifu/images/index_banner/banner_20160224.jpg");
        adModels.add(adModel);

        adModel = new AdModel();
        adModel.setUrl("http://4006391555.com/leshifu/images/index_banner.jpg");
        adModels.add(adModel);

        mIRecommendView.onRequestAd(adModels);

        mIRecommendBS.requestAd(mIRecommendView.getContext(), new MCListenerObj.IObjResListener<AdResponse>() {
            @Override
            public void onSuccess(AdResponse response, String url) {
                if(!response.isSuccess()) {
                    ToastUtil.showShortToast(response.getMessage());
                    return;
                }
                mIRecommendView.onRequestAd(response.getData());
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }
}
