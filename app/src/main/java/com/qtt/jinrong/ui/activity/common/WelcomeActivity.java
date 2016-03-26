package com.qtt.jinrong.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;


import com.qtt.jinrong.R;
import com.qtt.jinrong.presenter.IWelcomePresenter;
import com.qtt.jinrong.presenter.impl.WelcomePresenterImpl;
import com.qtt.jinrong.view.IWelcomeView;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity implements IWelcomeView {

    private IWelcomePresenter iWelcomePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iWelcomePresenter = new WelcomePresenterImpl(this);

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
