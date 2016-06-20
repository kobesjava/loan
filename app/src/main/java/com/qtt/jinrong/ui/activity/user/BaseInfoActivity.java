package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.BaseInfoModel;
import com.qtt.jinrong.bean.account.BaseInfoSaveRequest;
import com.qtt.jinrong.enums.CreditOverdueEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.GenderEnum;
import com.qtt.jinrong.enums.MarriageEnum;
import com.qtt.jinrong.enums.ProvinceEnum;
import com.qtt.jinrong.enums.SpouseGuaranteeEnum;
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

    //婚姻状况
    @ViewById(R.id.marriage)
    TextView mMarriageText;
    @ViewById(R.id.baseMarriageMore)
    View marriageMore;
    @ViewById(R.id.baseSpouseMonthIncome)
    InputEditText baseSpouseMonthIncomeEdit;
    @ViewById(R.id.baseSpouseGuarantee)
    TextView baseSpouseGuaranteeText;
    @ViewById(R.id.baseSpouseCreditSituation)
    TextView baseSpouseCreditSituationText;
    @ViewById(R.id.baseSpouseOverdueSituationView)
    View baseSpouseOverdueSituationView;
    @ViewById(R.id.baseSpouseOverdueSituation)
    TextView baseSpouseOverdueSituationText;

    IBaseInfoPresenter mPresenter;
    ProvinceEnum provinceEnum;
    BaseInfoSaveRequest request;

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
                request.setRegisterProvince(provinceEnum.name());
                mProvinceText.setText(val);
                mCityText.setText("");
                request.setRegisterCity(null);
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
                request.setRegisterCity(val);
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
                mMarriageText.setText(val);
                MarriageEnum mEnum = MarriageEnum.values()[position];
                request.setMarriage(mEnum.getCode());
                if(mEnum.equals(MarriageEnum.已婚)) {
                    marriageMore.setVisibility(View.VISIBLE);
                } else {
                    marriageMore.setVisibility(View.GONE);
                }
            }
        });
        show();
    }
    @Click(R.id.baseSpouseGuarantee)
    void clickbaseSpouseGuarantee() {
        mSelectView.setData(SpouseGuaranteeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                baseSpouseGuaranteeText.setText(val);
                request.setSpouseSign(SpouseGuaranteeEnum.values()[position].getCode());
            }
        });
        show();
    }
    @Click(R.id.baseSpouseCreditSituation)
    void clickbaseSpouseCreditSituation() {
        mSelectView.setData(CreditSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                baseSpouseCreditSituationText.setText(val);
                CreditSituationEnum mEnum = CreditSituationEnum.values()[position];
                request.setSpouseCredit(mEnum.getCode());
                if(mEnum.equals(CreditSituationEnum.有逾期)) baseSpouseOverdueSituationView.setVisibility(View.VISIBLE);
                else baseSpouseOverdueSituationView.setVisibility(View.GONE);
            }
        });
        show();
    }
    @Click(R.id.baseSpouseOverdueSituation)
    void clickbaseSpouseOverdueSituation() {
        mSelectView.setData(CreditOverdueEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                baseSpouseOverdueSituationText.setText(val);
                request.setSpouseOverdue(CreditOverdueEnum.values()[position].getCode());
            }
        });
        show();
    }


    /*** IBaseInfoView ***/
    @Override
    public void onRequest(BaseInfoModel model) {
        if(model == null) return;

        if(!TextUtils.isEmpty(model.getUsername())) nameText.setText(model.getUsername());

        GenderEnum gEnum = GenderEnum.find(model.getGender());
        if(gEnum != null) {
            if(gEnum.equals(GenderEnum.男)) {
                sexToggleBtn.setChecked(true);
            } else {
                sexToggleBtn.setChecked(false);
            }
        }

        if(model.getAge() != null) ageText.setText(String.valueOf(model.getAge()));

        if(!TextUtils.isEmpty(model.getIdNumber())) idsText.setText(model.getIdNumber());

        ProvinceEnum provinceEnum = ProvinceEnum.find(model.getRegisterProvince());
        if(provinceEnum != null) {
            this.provinceEnum = provinceEnum;
            mProvinceText.setText(provinceEnum.name());
        }
        //String city = DistrictUtil.getCity(getApplication(),this.provinceEnum,model.getRegisterCity());
        String city = model.getRegisterCity();
        if(!TextUtils.isEmpty(city)) mCityText.setText(city);
        if(!TextUtils.isEmpty(model.getRegisterAddr())) addressText.setText(model.getRegisterAddr());

        MarriageEnum mEnum = MarriageEnum.find(model.getMarriage());
        if(mEnum != null) {
            mMarriageText.setText(mEnum.getTitle());
            if (mEnum.equals(MarriageEnum.已婚)) {
                marriageMore.setVisibility(View.VISIBLE);
            } else {
                marriageMore.setVisibility(View.GONE);
            }
        }

        if(model.getSpouseIncome() != null) baseSpouseMonthIncomeEdit.setText(String.valueOf(model.getSpouseIncome()));
        SpouseGuaranteeEnum sgEnum = SpouseGuaranteeEnum.find(model.getSpouseSign());
        if(sgEnum != null) baseSpouseGuaranteeText.setText(sgEnum.name());

        CreditSituationEnum csEnum = CreditSituationEnum.find(model.getSpouseCredit());
        if(csEnum != null) {
            baseSpouseCreditSituationText.setText(csEnum.getTitle());
            if(csEnum.equals(CreditSituationEnum.有逾期)) {
                baseSpouseOverdueSituationView.setVisibility(View.VISIBLE);
            }
        }
        CreditOverdueEnum coEnum = CreditOverdueEnum.find(model.getSpouseOverdue());
        if(coEnum != null) {
            baseSpouseOverdueSituationText.setText(coEnum.name());
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
        if(!TextUtils.isEmpty(idsText.getString())) request.setIdNumber(idsText.getString().replaceAll(" ", ""));
        if(!TextUtils.isEmpty(addressText.getString())) request.setRegisterAddr(addressText.getString());
        if(!TextUtils.isEmpty(baseSpouseMonthIncomeEdit.getString())) request.setSpouseIncome(Integer.valueOf(baseSpouseMonthIncomeEdit.getString()));
        return request;
    }
    /*** IBaseInfoView ***/
}
