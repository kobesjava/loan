package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.qtt.framework.util.CheckDoubleClick;
import com.qtt.jinrong.R;
import com.qtt.jinrong.common.wrap.WrapCountDownTimer;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.presenter.ILoginRegistPresenter;
import com.qtt.jinrong.presenter.impl.LoginRegistPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.ui.widget.text.InputPwdEdit;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IForgetPwdView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_forget_pwd)
public class ForgetPwdActivity extends BaseActivity implements IForgetPwdView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.phoneEdit)
    InputEditText mPhoneEdit;

    @ViewById(R.id.codeEdit)
    InputEditText mCodeEdit;

    @ViewById(R.id.pwd)
    InputPwdEdit mPwdEdit;

    @ViewById(R.id.btnRequestCode)
    Button mBtnRequestCode;

    MyCountDownTimer mCountDownTimer;
    ILoginRegistPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginRegistPresenterImpl(this);
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
        mCommonTitleBar.setTitle(getString(R.string.forget_title));
        mCommonTitleBar.setActivity(this);
    }

    @Click(R.id.btnRequestCode)
    void clickRequestCode() {
        if(CheckDoubleClick.isFastDoubleClick()) return;

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));

        mCountDownTimer = new MyCountDownTimer(Constants.REQUEST_CODE_TIME,1000,this);
        mCountDownTimer.start();

        mPresenter.requestCode();
    }

    @Click(R.id.btnSubmit)
    void clickSubmit() {
        if(TextUtils.isEmpty(mPhoneEdit.getString())) {
            ToastUtil.showShortToast("请填写手机号码");
            return;
        }

        if(TextUtils.isEmpty(mCodeEdit.getString())) {
            ToastUtil.showShortToast("请填写验证码");
            return;
        }

        if(TextUtils.isEmpty(mPwdEdit.getString())) {
            ToastUtil.showShortToast("请填写密码");
            return;
        }
        mPresenter.forgetPwd();
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

    /**
     * 请求验证码
     */
    private void requestCode() {
        if(CheckDoubleClick.isFastDoubleClick()) return;

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));

        mCountDownTimer = new MyCountDownTimer(Constants.REQUEST_CODE_TIME,1000,this);
        mCountDownTimer.start();

        mPresenter.requestCode();
    }

    static class MyCountDownTimer extends WrapCountDownTimer<ForgetPwdActivity> {

        public MyCountDownTimer(long millisInFuture, long countDownInterval,ForgetPwdActivity t) {
            super(millisInFuture,countDownInterval,t);
        }

        @Override
        protected void onFinish(ForgetPwdActivity loginActivity) {
            loginActivity.resetCodeBtn();
        }

        @Override
        protected void onTick(ForgetPwdActivity loginActivity, long millisUntilFinished) {
            loginActivity.mBtnRequestCode.setText("("+(millisUntilFinished/1000)+"秒后)重新获取");
        }
    }

    /***  IForgetView ***/
    @Override
    public String getPhone() {
        return mPhoneEdit.getString();
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
    public void onRequestCode(boolean success) {
        if(!success) resetCodeBtn();
    }

    @Override
    public void onResetPwdSucess() {
        finish();
    }
    /***  IForgetView ***/
}
