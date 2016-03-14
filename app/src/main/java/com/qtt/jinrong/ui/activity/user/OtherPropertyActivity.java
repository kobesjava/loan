package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.OtherPropertyModel;
import com.qtt.jinrong.bean.account.OtherPropertySaveRequest;
import com.qtt.jinrong.presenter.IOtherPropertyPresenter;
import com.qtt.jinrong.presenter.impl.OtherPropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.IOtherInfoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 其他资产(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_other_property)
public class OtherPropertyActivity extends BaseActivity implements IOtherInfoView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.name)
    InputEditText mName;
    @ViewById(R.id.years)
    InputEditText mYears;
    @ViewById(R.id.detail)
    InputEditText mDetail;
    @ViewById(R.id.worth)
    InputEditText mWorth;

    OtherPropertySaveRequest request;
    IOtherPropertyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request = new OtherPropertySaveRequest();
        request.setUserId(getUserId());
        mPresenter = new OtherPropertyPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_other_title));
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE, getString(R.string.save));
        mTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                mPresenter.save();
            }
        });

        mPresenter.request();
    }


    /***  IOtherInfoView  ***/
    @Override
    public void onRequest(OtherPropertyModel model) {
        if(!TextUtils.isEmpty(model.getAssetName())) mName.setText(model.getAssetName());
        if(model.getAssetAge() != null && model.getAssetAge() > 0) mYears.setText(String.valueOf(model.getAssetAge()));
        if(!TextUtils.isEmpty(model.getAssetDetail())) mDetail.setText(model.getAssetDetail());
        if(model.getAssetPrice() != null && model.getAssetPrice() > 0) mWorth.setText(String.valueOf(model.getAssetPrice()));
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public OtherPropertySaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(mName.getString())) request.setAssetName(mName.getString());
        if(!TextUtils.isEmpty(mYears.getString())) request.setAssetAge(Integer.valueOf(mYears.getString()));
        if(!TextUtils.isEmpty(mDetail.getString())) request.setAssetDetail(mDetail.getString());
        if(!TextUtils.isEmpty(mWorth.getString())) request.setAssetPrice(Integer.valueOf(mWorth.getString()));
        return request;
    }
    /***  IOtherInfoView  ***/
}
