package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_regist)
public class RegistActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.phoneEdit)
    InputEditText mPhoneEdit;

    @ViewById(R.id.nickEdit)
    InputEditText mNickEdit;

    @ViewById(R.id.btnSex)
    ToggleButton mSexBtn;

    @AfterViews
    public void initView() {
        mCommonTitleBar.setTitle(getString(R.string.regist_title));
        mCommonTitleBar.setActivity(this);
    }

    @Click(R.id.btnNext)
    void clickBtnNext() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(Regist1Activity.class));
        intent.putExtra(Regist1Activity.INTENT_PHONE,mPhoneEdit.getString());
        startActivity(intent);
        finish();
    }

}
