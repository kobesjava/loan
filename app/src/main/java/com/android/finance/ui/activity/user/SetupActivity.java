package com.android.finance.ui.activity.user;

import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.bean.event.LoginExpired;
import com.android.finance.bean.event.LoginOutEvent;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.finance.framework.config.AppConfig;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_setup)
public class SetupActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @AfterViews
    public void initView() {
        mCommonTitleBar.setTitle(getString(R.string.setup_title));
        mCommonTitleBar.setActivity(this);
    }

    @Click(R.id.btnLoginout)
    void clickBtnLoginout() {
        EventBus.getDefault().post(new LoginExpired());
        finish();
    }

}
