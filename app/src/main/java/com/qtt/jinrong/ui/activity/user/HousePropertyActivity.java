package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.HousePropertyModel;
import com.qtt.jinrong.bean.account.HousePropertySaveRequest;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.presenter.IHousePropertyPresenter;
import com.qtt.jinrong.presenter.impl.HousePropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.view.IHousePropertyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_house_property)
public class HousePropertyActivity extends BaseSelectActivity implements IHousePropertyView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.property)
    TextView mHousePropertyText;

    HousePropertySaveRequest request;
    IHousePropertyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        request = new HousePropertySaveRequest();
        request.setUserId(getUserId());
        mPresenter = new HousePropertyPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_house_title));
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE,getString(R.string.save));
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

    @Click(R.id.property)
    void clickProperty() {
        mSelectView.setData(HousePropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setHouse(HousePropertyEnum.values()[position].getCode());
                mHousePropertyText.setText(val);
            }
        });
        show();
    }

    /***  IHousePropertyView  ***/
    @Override
    public void onRequest(HousePropertyModel model) {
        if(model.getHouse() != null) {
            HousePropertyEnum mEnum = HousePropertyEnum.find(model.getHouse());
            mHousePropertyText.setText(mEnum.getTitle());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public HousePropertySaveRequest getSaveRequest() {
        return request;
    }
    /***  IHousePropertyView  ***/
}
