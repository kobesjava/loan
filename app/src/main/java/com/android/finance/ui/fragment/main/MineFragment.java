package com.android.finance.ui.fragment.main;

import android.content.Intent;

import com.android.finance.R;
import com.android.finance.config.Constants;
import com.android.finance.ui.activity.user.LoginActivity;
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

    @AfterViews
    public void initView() {

    }

    @Click(R.id.btnLogin)
    void clickLogin() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoginActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanApply)
    void clickLoanApply() {

    }

    @Click(R.id.btnLoanMaterial)
    void clickLoanMaterial() {

    }

    @Click(R.id.btnAboutUs)
    void clickAboutUs() {

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
