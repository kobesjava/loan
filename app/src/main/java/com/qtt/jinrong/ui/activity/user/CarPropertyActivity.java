package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.CarPropertyModel;
import com.qtt.jinrong.bean.account.CarPropertySaveRequest;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.presenter.ICarPropertyPresenter;
import com.qtt.jinrong.presenter.impl.CarPropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.view.ICarPropertyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_car_property)
public class CarPropertyActivity extends BaseSelectActivity implements ICarPropertyView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.property)
    TextView mCarPropertyText;

    CarPropertySaveRequest request;
    ICarPropertyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request = new CarPropertySaveRequest();
        request.setUserId(getUserId());
        mPresenter = new CarPropertyPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_car_title));
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
        mSelectView.setData(CarPropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setCar(CarPropertyEnum.values()[position].getCode());
                mCarPropertyText.setText(val);
            }
        });
        show();
    }


    /***  ICarPropertyView  ***/
    @Override
    public void onRequest(CarPropertyModel model) {
        if(model.getCar() != null) {
            CarPropertyEnum mEnum = CarPropertyEnum.find(model.getCar());
            if(mEnum != null) mCarPropertyText.setText(mEnum.name());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public CarPropertySaveRequest getSaveRequest() {
        return request;
    }
    /***  ICarPropertyView  ***/
}
