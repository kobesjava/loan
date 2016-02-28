package com.android.finance.ui.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.finance.R;
import com.android.finance.common.wrap.WrapCountDownTimer;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.text.InputEditText;
import com.android.finance.ui.widget.text.InputPwdEdit;
import com.finance.framework.util.CheckDoubleClick;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/24.
 */
@EActivity(R.layout.activity_regist1)
public class Regist1Activity extends BaseActivity {

    public static final String INTENT_PHONE = "INTENT_PHONE";
    public static final String INTENT_TITLE = "INTENT_TITLE";

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

    @ViewById(R.id.btnSex)
    ToggleButton mBtnSubmit;

    MyCountDownTimer mCountDownTimer;

    @Override
    protected void onDestroy() {
        if(mCountDownTimer != null) {
            mCountDownTimer.stop();
        }
        super.onDestroy();
    }

    @AfterViews
    public void initView() {

        String title = mIntent.getStringExtra(INTENT_TITLE);
        mCommonTitleBar.setTitle(TextUtils.isEmpty(title)?getString(R.string.regist_title):title);
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

        mPhone.setText(mIntent.getStringExtra(INTENT_PHONE));

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));
        mCountDownTimer = new MyCountDownTimer(5000,1000,this);
        mCountDownTimer.start();
    }

    @Click(R.id.btnRequestCode)
    void clickBtnRequestCode() {
        if(CheckDoubleClick.isFastDoubleClick()) return;

        mBtnRequestCode.setEnabled(false);
        mBtnRequestCode.setBackgroundResource(R.color.color_9e9e0e);
        mBtnRequestCode.setTextColor(getResources().getColor(R.color.color_eee));

        mCountDownTimer = new MyCountDownTimer(5000,1000,this);
        mCountDownTimer.start();
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {

    }

    static class MyCountDownTimer extends WrapCountDownTimer<Regist1Activity> {

        public MyCountDownTimer(long millisInFuture, long countDownInterval,Regist1Activity t) {
            super(millisInFuture,countDownInterval,t);
        }

        @Override
        protected void onFinish(Regist1Activity activity) {
            activity.mBtnRequestCode.setEnabled(true);
            activity.mBtnRequestCode.setText(activity.getString(R.string.login_click_request_again));
            activity.mBtnRequestCode.setBackgroundResource(R.drawable.bg_orange_corner_selecter);
            activity.mBtnRequestCode.setTextColor(activity.getResources().getColor(R.color.white));
        }

        @Override
        protected void onTick(Regist1Activity activity, long millisUntilFinished) {
            activity.mBtnRequestCode.setText("("+(millisUntilFinished/1000)+"秒后)重新获取");
        }
    }
}
