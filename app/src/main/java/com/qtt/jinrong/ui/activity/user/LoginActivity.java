package com.qtt.jinrong.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.qtt.jinrong.R;
import com.qtt.jinrong.common.wrap.WrapCountDownTimer;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.adapter.LoginPagerAdapter;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectBox;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.framework.util.CheckDoubleClick;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        View loginCodeView = LayoutInflater.from(this).inflate(R.layout.login_code,null);
        mPhone2Edit = (InputEditText) loginPwdView.findViewById(R.id.phoneEdit);
        mCodeEdit = (InputEditText) loginPwdView.findViewById(R.id.codeEdit);
        mBtnRequestCode = (Button) loginCodeView.findViewById(R.id.btnRequestCode);
        mBtnRequestCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode();
            }
        });


        List<View> mList = new ArrayList<>(5);
        mList.add(loginPwdView);
        mList.add(loginCodeView);

        mLoginPagerAdapter = new LoginPagerAdapter();
        mLoginPagerAdapter.bind(mList);
        mViewPager.setAdapter(mLoginPagerAdapter);
    }

    /**
     * 忘记密码
     */
    private void clickForgetBtn() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(ForgetPwdActivity.class));
        startActivity(intent);
    }

    /**
     * 请求验证码
     */
    private void requestCode() {
        if(CheckDoubleClick.isFastDoubleClick()) return;

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));

        mCountDownTimer = new MyCountDownTimer(5000,1000,this);
        mCountDownTimer.start();
    }

    static class MyCountDownTimer extends WrapCountDownTimer<LoginActivity> {

        public MyCountDownTimer(long millisInFuture, long countDownInterval,LoginActivity t) {
            super(millisInFuture,countDownInterval,t);
        }

        @Override
        protected void onFinish(LoginActivity loginActivity) {
            loginActivity.mBtnRequestCode.setEnabled(true);
            loginActivity.mBtnRequestCode.setText(loginActivity.getString(R.string.login_click_request_again));
            loginActivity.mBtnRequestCode.setBackgroundResource(R.drawable.bg_orange_corner_selecter);
            loginActivity.mBtnRequestCode.setTextColor(loginActivity.getResources().getColor(R.color.white));
        }

        @Override
        protected void onTick(LoginActivity loginActivity, long millisUntilFinished) {
            loginActivity.mBtnRequestCode.setText("("+(millisUntilFinished/1000)+"秒后)重新获取");
        }
    }

}
