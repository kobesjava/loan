package com.android.finance.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;


import com.android.finance.R;
import com.android.finance.presenter.IWelcomePresenter;
import com.android.finance.presenter.impl.IWelcomePresenterImpl;
import com.android.finance.view.IWelcomeView;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity implements IWelcomeView {

    private IWelcomePresenter iWelcomePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iWelcomePresenter = new IWelcomePresenterImpl(this);

        iWelcomePresenter.initSystem();

        // 通知
        //if (mIntent.getStringExtra(PushConstants.TAG) != null) {
        //    iWelcomePresenter.doPush();
        //}

        toMain();
    }

    private void toMain() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(MainActivity.class));
        startActivity(intent);
        finish();
    }

}
