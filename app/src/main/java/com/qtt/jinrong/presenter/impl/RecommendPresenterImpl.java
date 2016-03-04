package com.qtt.jinrong.presenter.impl;

import com.qtt.jinrong.bean.recommend.AdModel;
import com.qtt.jinrong.bean.recommend.AdResponse;
import com.qtt.jinrong.model.IRecommendBS;
import com.qtt.jinrong.model.impl.RecommendBSImpl;
import com.qtt.jinrong.presenter.IRecommendPresenter;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IRecommendView;
import com.qtt.framework.http.MCListenerObj;

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
