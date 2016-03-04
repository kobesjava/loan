package com.qtt.jinrong.presenter.impl;

import com.qtt.jinrong.presenter.IMainPresenter;
import com.qtt.jinrong.view.IMainView;

/**
 * Created by yanxin on 16/2/23.
 */
public class MainPresenterImpl implements IMainPresenter {

    private IMainView iMainView;

    public MainPresenterImpl(IMainView iMainView) {

        this.iMainView = iMainView;

    }

}
