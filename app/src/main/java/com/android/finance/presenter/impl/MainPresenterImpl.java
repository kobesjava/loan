package com.android.finance.presenter.impl;

import com.android.finance.presenter.IMainPresenter;
import com.android.finance.view.IMainView;

/**
 * Created by yanxin on 16/2/23.
 */
public class MainPresenterImpl implements IMainPresenter {

    private IMainView iMainView;

    public MainPresenterImpl(IMainView iMainView) {

        this.iMainView = iMainView;

    }

}
