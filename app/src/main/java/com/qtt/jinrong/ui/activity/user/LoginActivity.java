package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.event.LoginEvent;
import com.qtt.jinrong.bean.user.UserInfo;
import com.qtt.jinrong.common.wrap.WrapCountDownTimer;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.presenter.ILoginRegistPresenter;
import com.qtt.jinrong.presenter.impl.LoginRegistPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.adapter.LoginPagerAdapter;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectBox;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.framework.util.CheckDoubleClick;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.util.UserInfoUtil;
import com.qtt.jinrong.view.ILoginView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ILoginView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.selectBox)
    SelectBox mSelectBox;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    LoginPagerAdapter mLoginPagerAdapter;

    InputEditText mPhone1Edit,mPhone2Edit;
    InputEditText mPwdEdit,mCodeEdit;
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
        mCommonTitleBar.setActivity(this);
        mCommonTitleBar.setRightViewVisible(View.VISIBLE, getString(R.string.login_bar_regist));
        mCommonTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                Intent intent = new Intent(LoginActivity.this, GeneratedClassUtils.get(RegistActivity.class));
                startActivity(intent);
            }
        });

        initPager();

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) mSelectBox.checkLeft();
                else mSelectBox.checkRight();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSelectBox.setSelectBoxListner(new SelectBox.SelectBoxListener() {
            @Override
            public void selectLeft() {
                mViewPager.setCurrentItem(0);
            }

            @Override
            public void selectRight() {
                mViewPager.setCurrentItem(1);
            }
        });

        mSelectBox.checkLeft();
    }

    private void initPager() {
        View loginPwdView = LayoutInflater.from(this).inflate(R.layout.login_pwd,null);
        mPhone1Edit = (InputEditText) loginPwdView.findViewById(R.id.phoneEdit);
        mPwdEdit = (InputEditText) loginPwdView.findViewById(R.id.pwdEdit);
        loginPwdView.findViewById(R.id.btnForgetPwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickForgetBtn();
            }
        });
        loginPwdView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPwd();
            }
        });

        View loginCodeView = LayoutInflater.from(this).inflate(R.layout.login_code,null);
        mPhone2Edit = (InputEditText) loginCodeView.findViewById(R.id.phoneEdit);
        mCodeEdit = (InputEditText) loginCodeView.findViewById(R.id.codeEdit);
        mBtnRequestCode = (Button) loginCodeView.findViewById(R.id.btnRequestCode);
        mBtnRequestCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode();
            }
        });
        loginCodeView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCode();
            }
        });


        List<View> mList = new ArrayList<>(5);
        mList.add(loginPwdView);
        mList.add(loginCodeView);

        mLoginPagerAdapter = new LoginPagerAdapter();
        mLoginPagerAdapter.bind(mList);
        mViewPager.setAdapter(mLoginPagerAdapter);
    }

    void loginCode() {
        if(TextUtils.isEmpty(mPhone2Edit.getString())) {
            ToastUtil.showShortToast("请填写手机号码");
            return;
        }
        if(TextUtils.isEmpty(mCodeEdit.getString())) {
            ToastUtil.showShortToast("请填写验证码");
            return;
        }

        mPresenter.loginCode();
    }

    void loginPwd() {

        if(TextUtils.isEmpty(mPhone1Edit.getString())) {
            ToastUtil.showShortToast("请填写手机号码");
            return;
        }
        if(TextUtils.isEmpty(mPwdEdit.getString())) {
            ToastUtil.showShortToast("请填写密码");
            return;
        }

        if(mPwdEdit.getString().length() < 6 || mPwdEdit.getString().length() > 20) {
            ToastUtil.showShortToast("密码长度在6-20");
            return;
        }

        mPresenter.loginPwd();
    }

    /**
     * 忘记密码
     */
    private void clickForgetBtn() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(ForgetPwdActivity.class));
        startActivity(intent);
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

    static class MyCountDownTimer extends WrapCountDownTimer<LoginActivity> {

        public MyCountDownTimer(long millisInFuture, long countDownInterval,LoginActivity t) {
            super(millisInFuture,countDownInterval,t);
        }

        @Override
        protected void onFinish(LoginActivity loginActivity) {
            loginActivity.resetCodeBtn();
        }

        @Override
        protected void onTick(LoginActivity loginActivity, long millisUntilFinished) {
            loginActivity.mBtnRequestCode.setText("("+(millisUntilFinished/1000)+"秒后)重新获取");
        }
    }



    /**  ILoginView **/
    @Override
    public String getPhone() {
        if(mSelectBox.isLeftChecked()) return mPhone1Edit.getString();
        else return mPhone2Edit.getString();
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
    public void onLogin(UserInfo userInfo) {
        UserInfoUtil.saveUserInfo(this, userInfo);
        finish();
        EventBus.getDefault().post(new LoginEvent());
    }

    @Override
    public void onRequestCode(boolean success) {
        if(!success) {
            resetCodeBtn();
        }
    }
    /**  ILoginView  **/
}
