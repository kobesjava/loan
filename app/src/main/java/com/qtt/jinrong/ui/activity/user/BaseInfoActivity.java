package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.BaseInfoModel;
import com.qtt.jinrong.bean.account.BaseInfoSaveRequest;
import com.qtt.jinrong.enums.GenderEnum;
import com.qtt.jinrong.enums.MarriageEnum;
import com.qtt.jinrong.enums.ProvinceEnum;
import com.qtt.jinrong.presenter.IBaseInfoPresenter;
import com.qtt.jinrong.presenter.impl.BaseInfoPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.DistrictUtil;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IBaseInfoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 基本信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_base_info)
public class BaseInfoActivity extends BaseSelectActivity implements IBaseInfoView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.name)
    InputEditText nameText;
    @ViewById(R.id.btnSex)
    ToggleButton sexToggleBtn;
    @ViewById(R.id.age)
    InputEditText ageText;
    @ViewById(R.id.ids)
    InputEditText idsText;
    @ViewById(R.id.phone)
    TextView phoneText;
    @ViewById(R.id.province)
    TextView mProvinceText;
    @ViewById(R.id.city)
    TextView mCityText;
    @ViewById(R.id.address)
    InputEditText addressText;
    @ViewById(R.id.marriage)
    TextView mMarriageText;

    IBaseInfoPresenter mPresenter;
    ProvinceEnum provinceEnum;
    BaseInfoSaveRequest request;
    BaseInfoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request = new BaseInfoSaveRequest();
        request.setUserId(getUserId());
        mPresenter = new BaseInfoPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_base_info_title));
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

        if(mUserInfo != null) phoneText.setText(mUserInfo.getCell());

        mPresenter.request();
    }

    @Click(R.id.province)
    void clickProvince() {
        mSelectView.setData(ProvinceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                provinceEnum = ProvinceEnum.values()[position];
                request.setRegisterProvince(provinceEnum.getCode());
                mProvinceText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.city)
    void clickCity() {
        if(provinceEnum == null) {
            ToastUtil.showShortToast("请先选择户籍!");
            return;
        }
        mSelectView.setData(DistrictUtil.getCities(getApplicationContext(), provinceEnum));
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setRegisterCity(position + 1);
                mCityText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.marriage)
    void clickMarriage() {
        mSelectView.setData(MarriageEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                if(position == 0) request.setMarriage(MarriageEnum.已婚.getCode());
                else request.setMarriage(MarriageEnum.未婚离异.getCode());
                mMarriageText.setText(val);
            }
        });
        show();
    }

    /*** IBaseInfoView ***/
    @Override
    public void onRequest(BaseInfoModel model) {
        if(model == null) return;
        this.model = model;

        if(!TextUtils.isEmpty(model.getUsername())) nameText.setText(model.getUsername());
        if(model.getGender() == GenderEnum.男.getCode()) {
            sexToggleBtn.setChecked(true);
        } else if(model.getGender() == GenderEnum.女.getCode()) {
            sexToggleBtn.setChecked(false);
        }
        if(model.getAge() > 0) ageText.setText(String.valueOf(model.getAge()));
        if(!TextUtils.isEmpty(model.getIdNumber())) idsText.setText(model.getIdNumber());
        ProvinceEnum provinceEnum = ProvinceEnum.find(model.getRegisterProvince());
        if(provinceEnum != null) {
            this.provinceEnum = provinceEnum;
            mProvinceText.setText(provinceEnum.name());
        }
        Integer cityId = model.getRegisterCity();
        String city = DistrictUtil.getCity(getApplication(),this.provinceEnum,cityId);
        if(!TextUtils.isEmpty(city)) mCityText.setText(city);
        if(!TextUtils.isEmpty(model.getRegisterAddr())) addressText.setText(model.getRegisterAddr());
        if(model.getMarriage() == MarriageEnum.已婚.getCode()) {
            mMarriageText.setText(MarriageEnum.已婚.name());
        } else if(model.getMarriage() == MarriageEnum.未婚离异.getCode()) {
            mMarriageText.setText(MarriageEnum.未婚离异.name());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public BaseInfoSaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(nameText.getString())) request.setUsername(nameText.getString());
        request.setGender(sexToggleBtn.isChecked()?GenderEnum.男.getCode():GenderEnum.女.getCode());
        if(!TextUtils.isEmpty(ageText.getString())) request.setAge(Integer.valueOf(ageText.getString()));
        if(!TextUtils.isEmpty(idsText.getString())) request.setIdNumber(idsText.getString().replaceAll(" ",""));
        if(!TextUtils.isEmpty(addressText.getString())) request.setRegisterAddr(addressText.getString());
        return request;
    }
    /*** IBaseInfoView ***/
}
