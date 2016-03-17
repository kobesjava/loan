package com.qtt.jinrong.presenter.impl;

import com.qtt.framework.config.AppConfig;
import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.app.CheckUpgradeRequest;
import com.qtt.jinrong.bean.app.CheckUpgradeResponse;
import com.qtt.jinrong.bean.app.UpgradeModel;
import com.qtt.jinrong.model.IAppBs;
import com.qtt.jinrong.model.impl.AppBSImpl;
import com.qtt.jinrong.presenter.IMainPresenter;
import com.qtt.jinrong.view.IMainView;

/**
 * Created by yanxin on 16/2/23.
 */
public class MainPresenterImpl implements IMainPresenter {

    private IMainView mView;
    private IAppBs mBs;

    public MainPresenterImpl(IMainView mView) {
        this.mView = mView;
        this.mBs = new AppBSImpl();
    }

    @Override
    public void checkUpgrade() {
        CheckUpgradeRequest request = new CheckUpgradeRequest();
        request.setVersion(AppConfig.versionName);
        mBs.checkUpgrade(mView.getContext(), request, new MCListenerObj.IObjResListener<CheckUpgradeResponse>() {
            @Override
            public void onSuccess(CheckUpgradeResponse response, String url) {
                if(response.isSuccess() && response.getData() != null
                        && response.getData().needUpgrade()) {
                    mView.upgrade(response.getData());
                }
            }

            @Override
            public void onFail(Exception exception, String url) {
                UpgradeModel model = new UpgradeModel();
                model.setVersion("1.1");
                model.setIfForced(false);
                model.setUpgrade(true);
                model.setVersionInfo("1.那你反复带你飞\n2.佛非农佛奥反扒\n3.弄点饭放年假佛啊");
                model.setUrl("http://house-test-water.oss.aliyuncs.com/apk/rentagent/SaleAgent-release.apk");
                mView.upgrade(model);
            }
        });
    }
}
