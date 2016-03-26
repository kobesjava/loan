package com.qtt.jinrong.presenter.impl;

import com.qtt.jinrong.presenter.IWelcomePresenter;
import com.qtt.jinrong.util.PreferenceUtil;
import com.qtt.jinrong.view.IWelcomeView;

import java.lang.ref.WeakReference;

/**
 * Created by yanxin on 2015/12/16.
 */
public class WelcomePresenterImpl implements IWelcomePresenter {

    private WeakReference<IWelcomeView> welcomeViewWeakReference;

    //private IWelcomeService iWelcomeService;

    public WelcomePresenterImpl(IWelcomeView iWelcomeView) {
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
