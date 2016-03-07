package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;
import com.qtt.jinrong.common.wrap.WrapCountDownTimer;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.presenter.ILoginRegistPresenter;
import com.qtt.jinrong.presenter.impl.LoginRegistPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.ui.widget.text.InputPwdEdit;
import com.qtt.framework.util.CheckDoubleClick;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.view.IRegistView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_regist1)
public class Regist1Activity extends BaseActivity implements IRegistView {

    public static final String INTENT_PHONE = "INTENT_PHONE";
    public static final String INTENT_NICKNAME = "INTENT_NICKNAME";
    public static final String INTENT_GENDER = "INTENT_GENDER";

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.phone)
    TextView mPhone;

    @ViewById(R.id.codeEdit)
    InputEditText mCodeEdit;

    @ViewById(R.id.pwd)
    InputPwdEdit mPwdEdit;

    @ViewById(R.id.btnRequestCode)
    Button mBtnRequestCode;

    @ViewById(R.id.btnSubmit)
    ToggleButton mBtnSubmit;

    MyCountDownTimer mCountDownTimer;

    String mPhoneNum;
    String mNickname;
    int mGender;

    ILoginRegistPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginRegistPresenterImpl(this);
        mPhoneNum = mIntent.getStringExtra(INTENT_PHONE);
        mNickname = mIntent.getStringExtra(INTENT_NICKNAME);
        mGender = mIntent.getIntExtra(INTENT_GENDER,1);
    }

    @Override
    protected void onDestroy() {
        if(mCountDownTimer != null) {
            mCountDownTimer.stop();
        }
        super.onDestroy();
    }

    @AfterViews
    public void initView() {
        mCommonTitleBar.setTitle(getString(R.string.regist_title));
        mCommonTitleBar.setActivity(this);
        mCommonTitleBar.setRightViewVisible(View.VISIBLE, "登录");
        mCommonTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                Intent intent = new Intent(Regist1Activity.this, GeneratedClassUtils.get(LoginActivity.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mPhone.setText(mPhoneNum);

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));
        mCountDownTimer = new MyCountDownTimer(Constants.REQUEST_CODE_TIME,1000,this);
        mCountDownTimer.start();
    }

    /**
     * 重新设置发送验证码按钮
     */
    void resetCodeBtn() {
        mBtnRequestCode.setEnabled(true);
        mBtnRequestCode.setText(getString(R.string.login_click_request_again));
        mBtnRequestCode.setBackgroundResource(R.drawable.bg_orange_corner_selecter);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.white));
    }

    @Click(R.id.btnRequestCode)
    void clickBtnRequestCode() {
        if(CheckDoubleClick.isFastDoubleClick()) return;

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));

        mCountDownTimer = new MyCountDownTimer(Constants.REQUEST_CODE_TIME,1000,this);
        mCountDownTimer.start();

    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {
        mPresenter.regist();
    }

    static class MyCountDownTimer extends WrapCountDownTimer<Regist1Activity> {

        public MyCountDownTimer(long millisInFuture, long countDownInterval,Regist1Activity t) {
            super(millisInFuture,countDownInterval,t);
        }

        @Override
        protected void onFinish(Regist1Activity activity) {
            activity.resetCodeBtn();
        }

        @Override
        protected void onTick(Regist1Activity activity, long millisUntilFinished) {
            activity.mBtnRequestCode.setText("("+(millisUntilFinished/1000)+"秒后)重新获取");
        }
    }

    /***  IRegistView  ***/
    @Override
    public void onRequestCode(boolean success) {
        if(!success) resetCodeBtn();
    }

    @Override
    public String getPhone() {
        return mPhoneNum;
    }

    @Override
    public String getCode() {
        return mCodeEdit.getString();
    }

    @Override
    public String getPwd() {
        return mPwdEdit.getString();
    }

    @Override
    public int getGender() {
        return mGender;
    }

    @Override
    public String getNickname() {
        return mNickname;
    }

    @Override
    public void onRegist() {
        finish();
    }
    /***  IRegistView  ***/
}
