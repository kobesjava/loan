package com.android.finance.view;

import android.content.Context;

/**
 * @author yanxin
 */
public interface IView {

    Context getContext();

    String getUserId();

    void showLoading();

    void hideLoading();

}
