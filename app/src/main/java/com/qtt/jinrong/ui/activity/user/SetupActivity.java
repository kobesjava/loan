package com.qtt.jinrong.ui.activity.user;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.event.LoginExpired;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

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
