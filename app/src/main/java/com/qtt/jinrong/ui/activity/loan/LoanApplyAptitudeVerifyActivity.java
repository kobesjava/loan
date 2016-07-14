package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.qtt.framework.util.DateUtil;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.loan.LoanApplyRequest;
import com.qtt.jinrong.bean.loan.LoanApplyVerifyInfoModel;
import com.qtt.jinrong.enums.CarLinscePositionEnum;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.enums.CompanyPositionEnum;
import com.qtt.jinrong.enums.CreditOverdueEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.CreditTotalLimitEnum;
import com.qtt.jinrong.enums.CreditUsedLimitEnum;
import com.qtt.jinrong.enums.GenderEnum;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.HousePropertyMortgageSituationEnum;
import com.qtt.jinrong.enums.HousePropertyPositionEnum;
import com.qtt.jinrong.enums.IdentityEnum;
import com.qtt.jinrong.enums.IncomePayMethodEnum;
import com.qtt.jinrong.enums.LegalPersonEnum;
import com.qtt.jinrong.enums.LoanPurposeEnum;
import com.qtt.jinrong.enums.OperatorYearsEnum;
import com.qtt.jinrong.enums.SocialFundEnum;
import com.qtt.jinrong.enums.StoreTypeEnum;
import com.qtt.jinrong.enums.WorkYearsEnum;
import com.qtt.jinrong.presenter.ILoanApplyPresenter;
import com.qtt.jinrong.presenter.impl.LoanApplyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.help.UiUtil;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.datepicker.SlideDateTimeListener;
import com.qtt.jinrong.ui.widget.datepicker.SlideDateTimePicker;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.ILoanApplyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

/**
 * 贷款资质审核
 * Created by yanxin on 16/3/9.
 */
@EActivity(R.layout.activity_loan_aptitude_verify)
public class LoanApplyAptitudeVerifyActivity extends BaseSelectActivity implements ILoanApplyView {

    public static final String INTENT_PRODUCT_ID = "INTENT_PRODUCT_ID";
    public static final String INTENT_RESPONSE_TERM = "INTENT_RESPONSE_TERM";
    public static final String INTENT_RESPONSE_AMOUNT = "INTENT_RESPONSE_AMOUNT";

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.identity)
    TextView mIdentifyText;

    //企业、个人
    @ViewById(R.id.enterprisePersonalMore)
    View enterprisePersonalMore;
    @ViewById(R.id.legalPerson)
    TextView legalPersonText;
    @ViewById(R.id.companyPosition)
    TextView companyPositionText;
    @ViewById(R.id.operationPeriod)
    TextView operationPeriodText;

    //工薪、其他
    @ViewById(R.id.workerOtherMore)
    View workerOtherMore;
    @ViewById(R.id.companyWorkYears)
    TextView companyWorkYearsText;
    @ViewById(R.id.incomePayWay)
    TextView incomePayWayText;
    @ViewById(R.id.monthSalary)
    InputEditText monthSalaryEdit;
    @ViewById(R.id.socialFund)
    TextView socialFundText;

    //电商
    @ViewById(R.id.stores)
    View storesMore;
    @ViewById(R.id.storeType)
    TextView storeType;
    @ViewById(R.id.storeName)
    InputEditText storeName;
    @ViewById(R.id.storeAddress)
    InputEditText storeAddress;
    @ViewById(R.id.storeUsername)
    InputEditText storeUsername;
    @ViewById(R.id.storeAccount)
    InputEditText storeAccount;
    @ViewById(R.id.storeMonthTurnover)
    InputEditText storeMonthTurnover;
//    @ViewById(R.id.store180Sales)
//    InputEditText store180Sales;
//    @ViewById(R.id.store90Orders)
//    InputEditText store90Orders;
    @ViewById(R.id.storeMonthPays)
    InputEditText storeMonthPays;
    @ViewById(R.id.storeOpenTime)
    TextView storeOpenTime;
    @ViewById(R.id.storeRegistUser)
    InputEditText storeRegistUser;
    @ViewById(R.id.loanUsername)
    InputEditText loanUsername;
    @ViewById(R.id.loanMobile)
    InputEditText loanMobile;
    @ViewById(R.id.loanMobile1)
    InputEditText loanMobile1;
    @ViewById(R.id.loanTelPhone)
    InputEditText loanTelPhone;
    @ViewById(R.id.loanIdCard)
    InputEditText loanIdCard;
    @ViewById(R.id.loanAddress)
    InputEditText loanAddress;
    @ViewById(R.id.contactsUsername)
    InputEditText contactsUsername;
    @ViewById(R.id.contactsRelationship)
    InputEditText contactsRelationship;
    @ViewById(R.id.contactsConpany)
    InputEditText contactsConpany;
    @ViewById(R.id.contactsMobile)
    InputEditText contactsMobile;

    //公共
    @ViewById(R.id.name)
    InputEditText mNameEdit;
    @ViewById(R.id.gender)
    ToggleButton mGender;
    @ViewById(R.id.phone)
    InputEditText mPhoneEdit;
    @ViewById(R.id.purpose)
    TextView mLoUseText;
    @ViewById(R.id.age)
    InputEditText mAgeEdit;
    @ViewById(R.id.creditStation)
    TextView mCreditText;
    @ViewById(R.id.creditOverdueMore)
    View creditOverdueMore;
    @ViewById(R.id.creditOverdueSituation)
    TextView overdueText;
    @ViewById(R.id.creditLimit)
    TextView mCreditLimitText;
    @ViewById(R.id.creditMore)
    View creditMore;
    @ViewById(R.id.creditUsed)
    TextView creditUsedText;
    @ViewById(R.id.houseproperty)
    TextView mHousePropertyText;
    @ViewById(R.id.housePropertyMore)
    View housePropertyMore;
    @ViewById(R.id.housePropertyPosition)
    TextView housePropertyPositionText;
    @ViewById(R.id.housePropertySituaion)
    TextView housePropertySituaionText;
    @ViewById(R.id.carproperty)
    TextView mCarPropertyText;
    @ViewById(R.id.carPropertyMore)
    View carPropertyMore;
    @ViewById(R.id.carLicense)
    TextView carLicenseText;

    String productId;
    int term,amount;
    ILoanApplyPresenter mPresenter;
    LoanApplyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productId = mIntent.getStringExtra(INTENT_PRODUCT_ID);
        term = mIntent.getIntExtra(INTENT_RESPONSE_TERM, 0);
        amount = mIntent.getIntExtra(INTENT_RESPONSE_AMOUNT,0);
        mPresenter = new LoanApplyPresenterImpl(this);
        request = new LoanApplyRequest();
        request.userId = getUserId();
        request.expires = term;
        request.money = amount * 10000;
        request.productId = productId;
    }

    @AfterViews
    void initViews() {
        mTitleBar.setTitle(getString(R.string.loan_vertify_title));
        mTitleBar.setActivity(this);
        mPresenter.requestVerify();
    }

    @Click(R.id.identity)
    void clickIdentity() {
        mSelectView.setData(IdentityEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IdentityEnum mEnums = IdentityEnum.values()[position];
                mIdentifyText.setText(val);
                request.capacity = mEnums.getCode();

                if (mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
                    enterprisePersonalMore.setVisibility(View.VISIBLE);
                    workerOtherMore.setVisibility(View.GONE);
                    storesMore.setVisibility(View.GONE);
                } else if (mEnums.equals(IdentityEnum.工薪族) || mEnums.equals(IdentityEnum.其他)) {
                    enterprisePersonalMore.setVisibility(View.GONE);
                    workerOtherMore.setVisibility(View.VISIBLE);
                    storesMore.setVisibility(View.GONE);
                } else if (mEnums.equals(IdentityEnum.电商)) {
                    enterprisePersonalMore.setVisibility(View.GONE);
                    workerOtherMore.setVisibility(View.GONE);
                    storesMore.setVisibility(View.VISIBLE);
                }
            }
        });
        show();
    }

    //企业主 个体户
    @Click(R.id.legalPerson)
    void clickLegalPerson() {
        mSelectView.setData(LegalPersonEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                LegalPersonEnum mEnum = LegalPersonEnum.values()[position];
                request.corporation = mEnum.getCode();
                legalPersonText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.companyPosition)
    void clickCompanyPosition() {
        mSelectView.setData(CompanyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyPositionEnum mEnums = CompanyPositionEnum.values()[position];
                request.epBuss = mEnums.getCode();
                companyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.operationPeriod)
    void clickOperationPeriod() {
        mSelectView.setData(OperatorYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                OperatorYearsEnum mEnums = OperatorYearsEnum.values()[position];
                request.epPeriod = mEnums.getCode();
                operationPeriodText.setText(val);
            }
        });
        show();
    }

    //工薪族 其他
    @Click(R.id.companyWorkYears)
    void clickCompanyWorkYears() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                WorkYearsEnum mEnums = WorkYearsEnum.values()[position];
                request.currSeniority = mEnums.getCode();
                companyWorkYearsText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.incomePayWay)
    void clickIncomePayWay() {
        mSelectView.setData(IncomePayMethodEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomePayMethodEnum mEnums = IncomePayMethodEnum.values()[position];
                request.payWay = mEnums.getCode();
                incomePayWayText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.socialFund)
    void clickSocialFund() {
        mSelectView.setData(SocialFundEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                SocialFundEnum mEnums = SocialFundEnum.values()[position];
                request.socialSecurity = mEnums.getCode();
                socialFundText.setText(val);
            }
        });
        show();
    }

    //电商
    @Click(R.id.storeType)
    void clickstoreType() {
        mSelectView.setData(StoreTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                StoreTypeEnum mEnums = StoreTypeEnum.values()[position];
                request.shopTpye = mEnums.getCode();
                storeType.setText(val);
            }
        });
        show();
    }
    @Click(R.id.storeOpenTime)
    void clickStoreOpenTime() {
        new SlideDateTimePicker.Builder(getSupportFragmentManager()).
                setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        String dateStr = DateUtil.getDate(date);
                        storeOpenTime.setText(dateStr);
                        request.shopStartTime = dateStr;
                    }
                }).setInitialDate(new Date())
                .setTitle("店铺开始经营时间")
                .build()
                .show();
    }

    //公共属性
    @Click(R.id.purpose)
    void clickPurpose() {
        mSelectView.setData(LoanPurposeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.loUse = LoanPurposeEnum.values()[position].getCode();
                mLoUseText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.creditStation)
    void clickCreditStation() {
        mSelectView.setData(CreditSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditSituationEnum mEnum = CreditSituationEnum.values()[position];
                if(mEnum.equals(CreditSituationEnum.有逾期)) {
                    creditOverdueMore.setVisibility(View.VISIBLE);
                } else {
                    creditOverdueMore.setVisibility(View.GONE);
                }
                request.creInfo = mEnum.getCode();
                mCreditText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditOverdueSituation)
    void clickOverdue() {
        mSelectView.setData(CreditOverdueEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditOverdueEnum mEnum = CreditOverdueEnum.values()[position];
                request.overdue = mEnum.getCode();
                overdueText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditLimit)
    void clickCreditLimit() {
        mSelectView.setData(CreditTotalLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditTotalLimitEnum mEnum = CreditTotalLimitEnum.values()[position];
                if (mEnum.equals(CreditTotalLimitEnum.无信用卡)) creditMore.setVisibility(View.GONE);
                else creditMore.setVisibility(View.VISIBLE);
                request.creMoney = mEnum.getCode();
                mCreditLimitText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditUsed)
    void clickCreditUsed() {
        mSelectView.setData(CreditUsedLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditUsedLimitEnum mEnum = CreditUsedLimitEnum.values()[position];
                request.creUsed = mEnum.getCode();
                creditUsedText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.houseproperty)
    void clickHuseProperty() {
        mSelectView.setData(HousePropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyEnum mEnum = HousePropertyEnum.values()[position];
                if (mEnum.equals(HousePropertyEnum.无房产)) housePropertyMore.setVisibility(View.GONE);
                else housePropertyMore.setVisibility(View.VISIBLE);
                request.houseInfo = mEnum.getCode();
                mHousePropertyText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.housePropertyPosition)
    void clickHousePropertyPosition() {
        mSelectView.setData(HousePropertyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyPositionEnum mEnum = HousePropertyPositionEnum.values()[position];
                request.district = mEnum.getCode();
                housePropertyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.housePropertySituaion)
    void clickHousePropertySituaion() {
        mSelectView.setData(HousePropertyMortgageSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyMortgageSituationEnum mEnum = HousePropertyMortgageSituationEnum.values()[position];
                request.mortgage = mEnum.getCode();
                housePropertySituaionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.carproperty)
    void clickCarProperty() {
        mSelectView.setData(CarPropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarPropertyEnum mEnum = CarPropertyEnum.values()[position];
                if (mEnum.equals(CarPropertyEnum.无车产)) carPropertyMore.setVisibility(View.GONE);
                else carPropertyMore.setVisibility(View.VISIBLE);
                request.car = mEnum.getCode();
                mCarPropertyText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.carLicense)
    void clickCarLicense() {
        mSelectView.setData(CarLinscePositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarLinscePositionEnum mEnum = CarLinscePositionEnum.values()[position];
                request.carBelong = mEnum.getCode();
                carLicenseText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.btnNext)
    void clickBtnNext() {

        if(UiUtil.isEmpty(mIdentifyText,"请选择身份")) return;

        IdentityEnum identityEnum = IdentityEnum.find(request.capacity);
        if(identityEnum.equals(IdentityEnum.企业户) || identityEnum.equals(IdentityEnum.个体户)) {
            if(UiUtil.isEmpty(legalPersonText,"请选择法人或股东")) return;
            if(UiUtil.isEmpty(companyPositionText,"请选择企业经营地")) return;
            if(UiUtil.isEmpty(operationPeriodText,"请选择经营年限")) return;
        } else if(identityEnum.equals(IdentityEnum.工薪族) || identityEnum.equals(IdentityEnum.其他)){
            if(UiUtil.isEmpty(companyWorkYearsText,"请选择现单位工龄")) return;
            if(UiUtil.isEmpty(incomePayWayText,"请选择收入发放方式")) return;
            if(!UiUtil.isValidInt(monthSalaryEdit, "请填写月均总收入")) return;
            if(UiUtil.isEmpty(socialFundText,"请选择社保和公积金")) return;
        } else if(identityEnum.equals(IdentityEnum.电商)) {
            if(UiUtil.isEmpty(storeType,"请选择店铺类型")) return;
            if(UiUtil.isEmpty(storeName,"请填写店铺名称")) return;
            if(UiUtil.isEmpty(storeAddress,"请填写店铺地址")) return;
            if(UiUtil.isEmpty(storeUsername,"请填写店铺用户名")) return;
            if(UiUtil.isEmpty(storeAccount,"请填写店铺账号")) return;
            if(!UiUtil.isValidInt(storeMonthTurnover, "请填写店铺月均交易额")) return;
//            if(!UiUtil.isValidInt(store180Sales,"请填写店铺近180天销售总金额")) return;
//            if(!UiUtil.isValidInt(store90Orders, "请填写店铺近90天成功支付订单")) return;
            if(!UiUtil.isValidInt(storeMonthPays, "请填写店铺月支付金额")) return;
            if(UiUtil.isEmpty(storeOpenTime,"请填写店铺开始经营时间")) return;
            if(UiUtil.isEmpty(storeRegistUser,"请填写店铺实际注册人")) return;
            if(UiUtil.isEmpty(loanUsername,"请填写借款人姓名")) return;
            if(UiUtil.isEmpty(loanMobile,"请填写借款人移动电话")) return;
            if(UiUtil.isEmpty(loanMobile1,"请填写借款人备用电话")) return;
            if(UiUtil.isEmpty(loanTelPhone,"请填写借款人固话")) return;
            if(UiUtil.isEmpty(loanIdCard,"请填写借款人身份证号码")) return;
            if(UiUtil.isEmpty(loanAddress,"请填写借款人单位地址")) return;
            if(UiUtil.isEmpty(contactsUsername,"请填写联系人姓名")) return;
            if(UiUtil.isEmpty(contactsRelationship,"请填写联系人关系")) return;
            if(UiUtil.isEmpty(contactsConpany,"请填写联系人公司单位")) return;
            if(UiUtil.isEmpty(contactsMobile,"请填写联系人手机号码")) return;
        }

        if(UiUtil.isEmpty(mNameEdit,"请填写贷款人姓名")) return;
        if(UiUtil.isEmpty(mPhoneEdit,"请填写贷款人手机号")) return;
        if(UiUtil.isEmpty(mLoUseText,"请选择贷款用途")) return;

        Integer age = UiUtil.getIntVal(mAgeEdit,"请填写年龄");
        if(age.intValue()==0) return;

        if(UiUtil.isEmpty(mCreditText,"请选择信用情况")) return;

        CreditSituationEnum scEnum = CreditSituationEnum.find(request.creInfo);
        if(scEnum.equals(CreditSituationEnum.有逾期)) {
            if(UiUtil.isEmpty(overdueText,"请选择逾期情况")) return;
        }

        if(UiUtil.isEmpty(mCreditLimitText,"请选择信用卡总额度")) return;
        CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(request.creMoney);
        if(!ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
            if(UiUtil.isEmpty(creditUsedText,"请选择已使用额度")) return;
        }

        if(UiUtil.isEmpty(mHousePropertyText,"请选择房产信息")) return;
        HousePropertyEnum hpEnum = HousePropertyEnum.find(request.houseInfo);
        if(!hpEnum.equals(HousePropertyEnum.无房产)) {
            if(UiUtil.isEmpty(housePropertyPositionText,"请选择房产位置")) return;
            if(UiUtil.isEmpty(housePropertySituaionText,"请选择房产抵押/按揭情况")) return;
        }

        if(UiUtil.isEmpty(mCarPropertyText,"请选择车产信息")) return;
        CarPropertyEnum cpEnum = CarPropertyEnum.find(request.car);
        if(cpEnum.equals(CarPropertyEnum.有车产)) {
            if(UiUtil.isEmpty(carLicenseText,"请选择牌照归属地")) return;
        }


        request.monthlyIncome = UiUtil.getIntVal(monthSalaryEdit,null);

        request.shopName = storeName.getString();
        request.shopAddr = storeAddress.getString();
        request.shopUserName = storeUsername.getString();
        request.shopAccount = storeAccount.getString();
        request.shopAvgMon = UiUtil.getIntVal(storeMonthTurnover, null);
//        request.shop180Sales = UiUtil.getIntVal(store180Sales, null);
//        request.shop90Order = UiUtil.getIntVal(store90Orders, null);
        request.shopMonPay = UiUtil.getIntVal(storeMonthPays, null);
        request.shopReg = storeRegistUser.getString();
        request.borrowerName = loanUsername.getString();
        request.borrowerPhone = loanMobile.getString();
        request.borrowerSecondPhone = loanMobile1.getString();
        request.borrowerTel = loanTelPhone.getString();
        request.borrowerIdNum = loanIdCard.getString();
        request.borrowerCompanyAddr = loanAddress.getString();
        request.contactName = contactsUsername.getString();
        request.contactRelation= contactsRelationship.getString();
        request.contactCompany = contactsConpany.getString();
        request.contactPhone = contactsMobile.getString();

        request.name = mNameEdit.getString();
        request.gender = mGender.isChecked()? GenderEnum.男.getCode():GenderEnum.女.getCode();
        request.cell = mPhoneEdit.getString();
        request.age = age;

        mPresenter.apply();
    }

    /*** ILoanApplyView ***/
    @Override
    public void onRequestVerify(LoanApplyVerifyInfoModel model) {
        IdentityEnum identityEnum = IdentityEnum.find(model.capacity);
        if(identityEnum != null) {
            mIdentifyText.setText(identityEnum.name());
            request.capacity = identityEnum.getCode();
            if(identityEnum.equals(IdentityEnum.企业户) || identityEnum.equals(IdentityEnum.个体户)) {
                enterprisePersonalMore.setVisibility(View.VISIBLE);
                workerOtherMore.setVisibility(View.GONE);
                storesMore.setVisibility(View.GONE);
            } else if(identityEnum.equals(IdentityEnum.工薪族) || identityEnum.equals(IdentityEnum.其他)) {
                enterprisePersonalMore.setVisibility(View.GONE);
                workerOtherMore.setVisibility(View.VISIBLE);
                storesMore.setVisibility(View.GONE);
            } else if(identityEnum.equals(IdentityEnum.电商)) {
                enterprisePersonalMore.setVisibility(View.GONE);
                workerOtherMore.setVisibility(View.GONE);
                storesMore.setVisibility(View.VISIBLE);
            }
        }


        LegalPersonEnum lpEnum = LegalPersonEnum.find(model.corporation);
        if(lpEnum != null) legalPersonText.setText(lpEnum.getTitle());
        CompanyPositionEnum cpEnum = CompanyPositionEnum.find(model.epBuss);
        if(cpEnum != null) companyPositionText.setText(cpEnum.name());
        OperatorYearsEnum oyEnum = OperatorYearsEnum.find(model.epPeriod);
        if(oyEnum != null) operationPeriodText.setText(oyEnum.getTitle());


        WorkYearsEnum wyEnum = WorkYearsEnum.find(model.currSeniority);
        if(wyEnum != null) companyWorkYearsText.setText(wyEnum.getTitle());
        IncomePayMethodEnum ipEnum = IncomePayMethodEnum.find(model.payWay);
        if(ipEnum != null) incomePayWayText.setText(ipEnum.getTitle());
        if(model.monthlyIncome != null) monthSalaryEdit.setText(String.valueOf(model.monthlyIncome));
        SocialFundEnum sfEnum = SocialFundEnum.find(model.socialSecurity);
        if(sfEnum != null) socialFundText.setText(sfEnum.name());


        StoreTypeEnum storeTypeEnum = StoreTypeEnum.find(model.shopTpye);
        if(storeTypeEnum != null) storeType.setText(storeTypeEnum.getTitle());
        if(!TextUtils.isEmpty(model.shopName))
            storeName.setText(model.shopName);
        if(!TextUtils.isEmpty(model.shopAddr))
            storeAddress.setText(model.shopAddr);
        if(!TextUtils.isEmpty(model.shopUserName))
            storeUsername.setText(model.shopUserName);
        if(!TextUtils.isEmpty(model.shopAccount))
            storeAccount.setText(model.shopAccount);
        if(model.shopAvgMon != null && model.shopAvgMon.intValue() > 0)
            storeMonthTurnover.setText(String.valueOf(model.shopAvgMon));
//        if(model.shop180Sales != null && model.shop180Sales.intValue()>0)
//            store180Sales.setText(String.valueOf(model.shop180Sales));
//        if(model.shop90Order != null && model.shop90Order.intValue()>0)
//            store90Orders.setText(String.valueOf(model.shop90Order));
        if(model.shopMonPay != null && model.shopMonPay.intValue()>0)
            storeMonthPays.setText(String.valueOf(model.shopMonPay));
        if(!TextUtils.isEmpty(model.shopStartTime))
            storeOpenTime.setText(model.shopStartTime);
        if(!TextUtils.isEmpty(model.shopReg))
            storeRegistUser.setText(model.shopReg);
        if(!TextUtils.isEmpty(model.borrowerName))
            loanUsername.setText(model.borrowerName);
        if(!TextUtils.isEmpty(model.borrowerPhone))
            loanMobile.setText(model.borrowerPhone);
        if(!TextUtils.isEmpty(model.borrowerSecondPhone))
            loanMobile1.setText(model.borrowerSecondPhone);
        if(!TextUtils.isEmpty(model.borrowerTel))
            loanTelPhone.setText(model.borrowerTel);
        if(!TextUtils.isEmpty(model.borrowerIdNum))
            loanIdCard.setText(model.borrowerIdNum);
        if(!TextUtils.isEmpty(model.borrowerCompanyAddr))
            loanAddress.setText(model.borrowerCompanyAddr);
        if(!TextUtils.isEmpty(model.contactName))
            contactsUsername.setText(model.contactName);
        if(!TextUtils.isEmpty(model.contactRelation))
            contactsRelationship.setText(model.contactRelation);
        if(!TextUtils.isEmpty(model.contactCompany))
            contactsConpany.setText(model.contactCompany);
        if(!TextUtils.isEmpty(model.contactPhone))
            contactsMobile.setText(model.contactPhone);


        GenderEnum gEnum = GenderEnum.find(model.gender);
        if(gEnum != null) {
            if(gEnum.equals(GenderEnum.男)) {
                mGender.setChecked(true);
            } else {
                mGender.setChecked(false);
            }
        }
        if(model.age != null) mAgeEdit.setText(String.valueOf(model.age));
        CreditSituationEnum csEnum = CreditSituationEnum.find(model.creInfo);
        if(csEnum != null) {
            mCreditText.setText(csEnum.getTitle());
            request.creInfo = csEnum.getCode();
            if(csEnum.equals(CreditSituationEnum.有逾期)) creditOverdueMore.setVisibility(View.VISIBLE);
            else creditOverdueMore.setVisibility(View.GONE);
        }
        CreditOverdueEnum coEnum = CreditOverdueEnum.find(model.overdue);
        if(coEnum != null) overdueText.setText(coEnum.name());
        CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(model.creMoney);
        if(ctlEnum != null) {
            mCreditLimitText.setText(ctlEnum.getTitle());
            request.creMoney = ctlEnum.getCode();
            if(ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) creditMore.setVisibility(View.GONE);
            else creditMore.setVisibility(View.VISIBLE);
        }
        CreditUsedLimitEnum culEnum = CreditUsedLimitEnum.find(model.creUsed);
        if(culEnum != null) creditUsedText.setText(culEnum.getTitle());
        HousePropertyEnum hpEnum = HousePropertyEnum.find(model.houseInfo);
        if(hpEnum != null) {
            mHousePropertyText.setText(hpEnum.getTitle());
            request.houseInfo = hpEnum.getCode();
            if(hpEnum.equals(HousePropertyEnum.无房产)) housePropertyMore.setVisibility(View.GONE);
            else housePropertyMore.setVisibility(View.VISIBLE);
        }
        HousePropertyPositionEnum hppEnum = HousePropertyPositionEnum.find(model.district);
        if(hppEnum != null) housePropertyPositionText.setText(hppEnum.name());
        HousePropertyMortgageSituationEnum hpmsEnum = HousePropertyMortgageSituationEnum.find(model.mortgage);
        if(hpmsEnum != null) housePropertySituaionText.setText(hpmsEnum.getTitle());

        CarPropertyEnum carPropertyEnumEnum = CarPropertyEnum.find(model.car);
        if(carPropertyEnumEnum != null) {
            mCarPropertyText.setText(carPropertyEnumEnum.name());
            request.car = carPropertyEnumEnum.getCode();
            if(carPropertyEnumEnum.equals(CarPropertyEnum.无车产)) carPropertyMore.setVisibility(View.GONE);
            else carPropertyMore.setVisibility(View.VISIBLE);
        }
        CarLinscePositionEnum clpEnum = CarLinscePositionEnum.find(model.carBelong);
        if(clpEnum != null) carLicenseText.setText(clpEnum.name());

    }

    @Override
    public LoanApplyRequest getRequest() {

        if(!TextUtils.isEmpty(storeName.getString()))
            request.shopName = storeName.getString();
        if(!TextUtils.isEmpty(storeAddress.getString()))
            request.shopAddr = storeAddress.getString();
        if(!TextUtils.isEmpty(storeUsername.getString()))
            request.shopUserName = storeUsername.getString();
        if(!TextUtils.isEmpty(storeAccount.getString()))
            request.shopAccount = storeAccount.getString();
        if(!TextUtils.isEmpty(storeMonthTurnover.getString()))
            request.shopAvgMon = Integer.parseInt(storeMonthTurnover.getString());
//        if(!TextUtils.isEmpty(store180Sales.getString()))
//            request.shop180Sales = Integer.parseInt(store180Sales.getString());
//        if(!TextUtils.isEmpty(store90Orders.getString()))
//            request.shop90Order = Integer.parseInt(store90Orders.getString());
        if(!TextUtils.isEmpty(storeMonthPays.getString()))
            request.shopMonPay = Integer.parseInt(storeMonthPays.getString());
        if(!TextUtils.isEmpty(storeRegistUser.getString()))
            request.shopReg = storeRegistUser.getString();
        if(!TextUtils.isEmpty(loanUsername.getString()))
            request.borrowerName = loanUsername.getString();
        if(!TextUtils.isEmpty(loanMobile.getString()))
            request.borrowerPhone = loanMobile.getString();
        if(!TextUtils.isEmpty(loanMobile1.getString()))
            request.borrowerSecondPhone = loanMobile1.getString();
        if(!TextUtils.isEmpty(loanTelPhone.getString()))
            request.borrowerTel = loanTelPhone.getString();
        if(!TextUtils.isEmpty(loanIdCard.getString()))
            request.borrowerIdNum = loanIdCard.getString().replaceAll(" ","");
        if(!TextUtils.isEmpty(loanAddress.getString()))
            request.borrowerCompanyAddr = loanAddress.getString();
        if(!TextUtils.isEmpty(contactsUsername.getString()))
            request.contactName = contactsUsername.getString();
        if(!TextUtils.isEmpty(contactsRelationship.getString()))
            request.contactRelation = contactsRelationship.getString();
        if(!TextUtils.isEmpty(contactsConpany.getString()))
            request.contactCompany = contactsConpany.getString();
        if(!TextUtils.isEmpty(contactsMobile.getString()))
            request.contactPhone = contactsMobile.getString();

        return request;
    }

    @Override
    public void onApply(Response response) {
        Intent intent = new Intent(this, GeneratedClassUtils.get(LoanApplyResultActivity.class));
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_SUCCESS,response.isSuccess());
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_MESSAGE,response.getMessage());
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_AMOUNT,amount);
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_TERM,term);
        startActivity(intent);
        finish();
    }
    /*** ILoanApplyView ***/
}
