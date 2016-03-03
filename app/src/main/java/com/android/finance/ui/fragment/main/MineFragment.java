package com.android.finance.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.finance.R;
import com.android.finance.bean.loan.LoanApplyModel;
import com.android.finance.config.Constants;
import com.android.finance.ui.activity.loan.LoanApplyListActivity;
import com.android.finance.ui.activity.user.AboutUsActivity;
import com.android.finance.ui.activity.user.FinancingNeedsActivity;
import com.android.finance.ui.activity.user.LoginActivity;
import com.android.finance.ui.activity.user.SetupActivity;
import com.android.finance.ui.fragment.common.BaseFragment;
import com.android.finance.ui.widget.dialog.AlertDialogUtils;
import com.android.finance.util.SystemUtil;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {

    View mView;

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
    }

    @Click(R.id.btnLogin)
    void clickLogin() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoginActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnSetUp)
    void clickSetUp() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(SetupActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanApply)
    void clickMyLoanApply() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanMaterial)
    void clickLoanMaterial() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
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
                SystemUtil.callPhone(getActivity(),Constants.CUSTOMER_SERVICE_PHONE.replaceAll("-",""));
            }
        });
    }

}
