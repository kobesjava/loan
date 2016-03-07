package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.user.UserRegistRequest;
import com.qtt.jinrong.enums.GenderEnum;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.util.ToastUtil;
import com.umeng.analytics.Gender;

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
        if(TextUtils.isEmpty(mPhoneEdit.getString())) {
            ToastUtil.showShortToast("请填写手机号码");
            return;
        } else if(TextUtils.isEmpty(mNickEdit.getString())) {
            ToastUtil.showShortToast("请填写昵称");
            return;
        }

        Intent intent = new Intent(this, GeneratedClassUtils.get(Regist1Activity.class));
        intent.putExtra(Regist1Activity.INTENT_PHONE,mPhoneEdit.getString());
        intent.putExtra(Regist1Activity.INTENT_NICKNAME,mNickEdit.getString());
        intent.putExtra(Regist1Activity.INTENT_GENDER,mSexBtn.isChecked()? GenderEnum.男.getCode():GenderEnum.女.getCode());
        startActivity(intent);
        finish();
    }

}
