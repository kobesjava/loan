package com.android.finance.presenter.impl;

import com.android.finance.presenter.IWelcomePresenter;
import com.android.finance.util.PreferenceUtil;
import com.android.finance.view.IWelcomeView;

import java.lang.ref.WeakReference;

/**
 * Created by yanxin on 2015/12/16.
 */
public class IWelcomePresenterImpl implements IWelcomePresenter {

    private WeakReference<IWelcomeView> welcomeViewWeakReference;

    //private IWelcomeService iWelcomeService;

    public IWelcomePresenterImpl(IWelcomeView iWelcomeView) {
        this.welcomeViewWeakReference = new WeakReference<IWelcomeView>(iWelcomeView);
        //this.iWelcomeService = new IWelcomeServiceImpl();
    }

    private IWelcomeView getIWelcomeView() {
        return welcomeViewWeakReference.get();
    }

    @Override
    public void initSystem() {
        PreferenceUtil.build().putBoolean("LoginExpired", false);
    }

    @Override
    public void doPush() {
        //iWelcomeService.doPush(getIWelcomeView().getPushBean());
    }
}
