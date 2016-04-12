package com.qtt.jinrong.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.event.LoginEvent;
import com.qtt.jinrong.bean.event.LoginExpired;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.ui.activity.credit.CreditApplyListActivity;
import com.qtt.jinrong.ui.activity.loan.LoanApplyListActivity;
import com.qtt.jinrong.ui.activity.user.AboutUsActivity;
import com.qtt.jinrong.ui.activity.user.FinancingNeedsActivity;
import com.qtt.jinrong.ui.activity.user.LoginActivity;
import com.qtt.jinrong.ui.activity.user.SetupActivity;
import com.qtt.jinrong.ui.activity.user.VipActivity;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.util.SystemUtil;
import com.qtt.jinrong.util.UserInfoUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {

    View mView;

    @ViewById(R.id.notLogin)
    View notLoginText;
    @ViewById(R.id.nickname)
    TextView nicknameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_mine, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @AfterViews
    public void initView() {
        if(isInit) return;
        super.initView();
        setUpView();
    }

    private void setUpView() {
        if(mUserInfo != null) {
            notLoginText.setVisibility(View.GONE);
            nicknameText.setVisibility(View.VISIBLE);
            nicknameText.setText(mUserInfo.getUsername());
        } else {
            notLoginText.setVisibility(View.VISIBLE);
            nicknameText.setVisibility(View.GONE);
        }
    }

    @Click(R.id.btnLogin)
    void clickLogin() {
        if(mUserInfo != null) {
            Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
            startActivity(intent);
        } else {
            login();
        }
    }

    @Click(R.id.btnSetUp)
    void clickSetUp() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(SetupActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanApply)
    void clickMyLoanApply() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnCreditApply)
    void clickMyCreditApply() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(CreditApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanMaterial)
    void clickLoanMaterial() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnMember)
    void clickbtnMember() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(VipActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnAboutUs)
    void clickAboutUs() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(AboutUsActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnCall)
    void clickCall() {
        AlertDialogUtils.showdDoublelickPrompt(getActivity(), Constants.CUSTOMER_SERVICE_PHONE, "呼叫", "取消", new AlertDialogUtils.ImplAlertDialog() {
            @Override
            public void Confirm(Object object) {
                SystemUtil.callPhone(getActivity(), Constants.CUSTOMER_SERVICE_PHONE.replaceAll("-", ""));
            }
        });
    }

    public void onEventMainThread(LoginEvent event) {
        mUserInfo = UserInfoUtil.getUserInfo();
        setUpView();
    }

    public void onEventMainThread(LoginExpired event) {
        mUserInfo = null;
        setUpView();
    }

}
