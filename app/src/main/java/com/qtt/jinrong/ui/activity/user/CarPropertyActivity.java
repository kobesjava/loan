package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.CarPropertyModel;
import com.qtt.jinrong.bean.account.CarPropertySaveRequest;
import com.qtt.jinrong.enums.CarAgeEnum;
import com.qtt.jinrong.enums.CarDriveKmEnum;
import com.qtt.jinrong.enums.CarLinscePositionEnum;
import com.qtt.jinrong.enums.CarPropertyAssessedEnum;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.enums.CarMortgageSituationEnum;
import com.qtt.jinrong.presenter.ICarPropertyPresenter;
import com.qtt.jinrong.presenter.impl.CarPropertyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.ICarPropertyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 车产信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_car_property)
public class CarPropertyActivity extends BaseSelectActivity implements ICarPropertyView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.property)
    TextView mCarPropertyText;
    @ViewById(R.id.cpMore)
    View cpMore;
    @ViewById(R.id.cpBrand)
    InputEditText cpBrandEdit;
    @ViewById(R.id.cpLicenseNum)
    InputEditText cpLicenseNumEdit;
    @ViewById(R.id.cpLicensePosition)
    TextView cpLicensePositionText;
    @ViewById(R.id.cpPriceBuy)
    InputEditText cpPriceBuyEdit;
    @ViewById(R.id.cpAssessed)
    TextView cpAssessedText;
    @ViewById(R.id.cpYears)
    TextView cpYearsText;
    @ViewById(R.id.cpTravelKM)
    TextView cpTravelKMText;
    @ViewById(R.id.cpMortgageSituation)
    TextView cpMortgageSituationText;
    @ViewById(R.id.cpMortgageSituationMore)
    View cpMortgageSituationMore;
    @ViewById(R.id.cpMortgageLoanBalance)
    InputEditText cpLoanBalanceEdit;

    ICarPropertyPresenter mPresenter;
    CarPropertySaveRequest request;

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
                CarPropertyEnum mEnum = CarPropertyEnum.values()[position];
                if (mEnum.equals(CarPropertyEnum.无车产)) cpMore.setVisibility(View.GONE);
                else cpMore.setVisibility(View.VISIBLE);
                request.setCar(CarPropertyEnum.values()[position].getCode());
                mCarPropertyText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.cpLicensePosition)
    void clickLicensePosition() {
        mSelectView.setData(CarLinscePositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarLinscePositionEnum mEnum = CarLinscePositionEnum.values()[position];
                request.setCarBelong(mEnum.getCode());
                cpLicensePositionText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.cpAssessed)
    void clickCpAssessed() {
        mSelectView.setData(CarPropertyAssessedEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarPropertyAssessedEnum mEnum = CarPropertyAssessedEnum.values()[position];
                request.setCarValuation(mEnum.getCode());
                cpAssessedText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.cpYears)
    void clickCpYears() {
        mSelectView.setData(CarAgeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarAgeEnum mEnum = CarAgeEnum.values()[position];
                request.setCarAge(mEnum.getCode());
                cpYearsText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.cpTravelKM)
    void clickCpTravelKM() {
        mSelectView.setData(CarDriveKmEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarDriveKmEnum mEnum = CarDriveKmEnum.values()[position];
                request.setCarRange(mEnum.getCode());
                cpTravelKMText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.cpSituation)
    void clickCpSituation() {
        mSelectView.setData(CarMortgageSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarMortgageSituationEnum mEnum = CarMortgageSituationEnum.values()[position];
                request.setCarMortgage(mEnum.getCode());
                cpMortgageSituationText.setText(val);
                if(mEnum.equals(CarMortgageSituationEnum.未被抵押无按揭)) cpMortgageSituationMore.setVisibility(View.GONE);
                else cpMortgageSituationMore.setVisibility(View.VISIBLE);
            }
        });
        show();
    }


    /***  ICarPropertyView  ***/
    @Override
    public void onRequest(CarPropertyModel model) {
        if(model.getCar() != null) {
            CarPropertyEnum cpEnum = CarPropertyEnum.find(model.getCar());
            if(cpEnum == null) return;
            request.setCar(cpEnum.getCode());
            mCarPropertyText.setText(cpEnum.name());
            if(cpEnum.equals(CarPropertyEnum.无车产)) {
                cpMore.setVisibility(View.GONE);
                return;
            }
            cpMore.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(model.getCarBrand())) cpBrandEdit.setText(model.getCarBrand());
            if(!TextUtils.isEmpty(model.getCarNo())) cpLicenseNumEdit.setText(model.getCarNo());
            if(model.getCarBelong() != null) {
                CarLinscePositionEnum mEnum = CarLinscePositionEnum.find(model.getCarBelong());
                if(mEnum != null) cpLicensePositionText.setText(mEnum.name());
            }
            if(model.getCarPrice() != null) cpPriceBuyEdit.setText(String.valueOf(model.getCarPrice()));
            if(model.getCarValuation() != null) {
                CarPropertyAssessedEnum mEnum = CarPropertyAssessedEnum.find(model.getCarBelong());
                if(mEnum != null) cpAssessedText.setText(mEnum.name());
            }
            if(model.getCarAge() != null) {
                CarAgeEnum mEnum = CarAgeEnum.find(model.getCarAge());
                if(mEnum != null) cpYearsText.setText(mEnum.name());
            }
            if(model.getCarRange() != null) {
                CarDriveKmEnum mEnum = CarDriveKmEnum.find(model.getCarRange());
                if(mEnum != null) cpTravelKMText.setText(mEnum.name());
            }
            if(model.getCarMortgage() != null) {
                CarMortgageSituationEnum mEnum = CarMortgageSituationEnum.find(model.getCarMortgage());
                if(mEnum != null) {
                    cpMortgageSituationText.setText(mEnum.getTitle());
                    if(mEnum.equals(CarMortgageSituationEnum.未被抵押无按揭)) {
                        cpMortgageSituationMore.setVisibility(View.GONE);
                    } else {
                        cpMortgageSituationMore.setVisibility(View.VISIBLE);
                    }
                }
            }
            if(model.getCarLoanBalance() != null) cpLoanBalanceEdit.setText(String.valueOf(model.getCarLoanBalance()));

        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public CarPropertySaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(cpBrandEdit.getString())) request.setCarBrand(cpBrandEdit.getString());
        if(!TextUtils.isEmpty(cpLicenseNumEdit.getString())) request.setCarNo(cpLicenseNumEdit.getString());
        if(!TextUtils.isEmpty(cpPriceBuyEdit.getString())) request.setCarPrice(Integer.valueOf(cpPriceBuyEdit.getString()));
        if(!TextUtils.isEmpty(cpLoanBalanceEdit.getString())) request.setCarLoanBalance(Integer.valueOf(cpLoanBalanceEdit.getString()));
        return request;
    }
    /***  ICarPropertyView  ***/
}
