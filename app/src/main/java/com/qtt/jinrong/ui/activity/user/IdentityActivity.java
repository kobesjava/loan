package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.IdentityModel;
import com.qtt.jinrong.bean.account.IdentitySaveRequest;
import com.qtt.jinrong.enums.AvocationEnum;
import com.qtt.jinrong.enums.BillingYearEnum;
import com.qtt.jinrong.enums.CompanyPositionEnum;
import com.qtt.jinrong.enums.CompanyTypeEnum;
import com.qtt.jinrong.enums.DebtBalanceEnum;
import com.qtt.jinrong.enums.DebtRateEnum;
import com.qtt.jinrong.enums.DebtSituationEnterpriseEnum;
import com.qtt.jinrong.enums.FundYearsEnum;
import com.qtt.jinrong.enums.IdentityEnum;
import com.qtt.jinrong.enums.IncomePayMethodEnum;
import com.qtt.jinrong.enums.IncomeProofEnum;
import com.qtt.jinrong.enums.IncomeYearEnum;
import com.qtt.jinrong.enums.IndustryEnum;
import com.qtt.jinrong.enums.JobTitleEnum;
import com.qtt.jinrong.enums.LegalPersonEnum;
import com.qtt.jinrong.enums.MonthPrivateWaterEnum;
import com.qtt.jinrong.enums.MonthPublicWaterEnum;
import com.qtt.jinrong.enums.NetProfitEnum;
import com.qtt.jinrong.enums.OperatorYearsEnum;
import com.qtt.jinrong.enums.ProvinceEnum;
import com.qtt.jinrong.enums.RegistCaptialEnum;
import com.qtt.jinrong.enums.SocialFundEnum;
import com.qtt.jinrong.enums.SocialYearsEnum;
import com.qtt.jinrong.enums.StoreTypeEnum;
import com.qtt.jinrong.enums.WorkYearsEnum;
import com.qtt.jinrong.enums.shop180SalesEnum;
import com.qtt.jinrong.presenter.IIdentityPresenter;
import com.qtt.jinrong.presenter.impl.IdentityPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.help.UiUtil;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.DistrictUtil;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IIdentityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 身份信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_identity)
public class IdentityActivity extends BaseSelectActivity implements IIdentityView{

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.identity)
    TextView mIdentityText;
    //enterprise persional
    @ViewById(R.id.epView)
    View epView;
    //woker other
    @ViewById(R.id.woView)
    View woView;
    //woker dianshang
    @ViewById(R.id.dsView)
    View dsView;

    //企业主 个体户 item
    @ViewById(R.id.epLegalPerson)
    TextView epLegalPersonText;
    @ViewById(R.id.epName)
    InputEditText epNameEdit;
    @ViewById(R.id.epAddress)
    InputEditText epAddressEdit;
    @ViewById(R.id.epIndustry)
    TextView epIndustryText;
    @ViewById(R.id.epStaffNum)
    InputEditText epStaffNumEdit;
    @ViewById(R.id.epCompanyPosition)
    TextView epCompanyPositionText;
    @ViewById(R.id.epOperationPeriod)
    TextView epOperationPeriodText;
    @ViewById(R.id.epRegistCapital)
    TextView epRegistCapitalText;
    @ViewById(R.id.epBusiness)
    InputEditText epBusinessEdit;
    @ViewById(R.id.epTurnover)
    InputEditText epTurnoverEdit;
    @ViewById(R.id.epBilling)
    TextView epBillingText;
    @ViewById(R.id.epIncomeYear)
    TextView epIncomeYearText;
    @ViewById(R.id.epMonthPublicWater)
    TextView epMonthPublicWaterText;
    @ViewById(R.id.epMonthPrivateWater)
    TextView epMonthPrivateWaterText;
    @ViewById(R.id.epDebtRate)
    TextView epDebtRateText;
    @ViewById(R.id.epProfit)
    TextView epProfitText;
    @ViewById(R.id.epDebtSituation)
    TextView epDebtSituationText;
    @ViewById(R.id.epDebtSituationView)
    View epDebtSituationView;
    @ViewById(R.id.epDebtInstitutionName)
    InputEditText epDebtInstitutionNameEdit;
    @ViewById(R.id.epDebtBalance)
    TextView epDebtBalanceText;
    @ViewById(R.id.epMonthRepayment)
    InputEditText epMonthRepaymentEdit;

    //工薪族 其他 item
    @ViewById(R.id.workCompanyType)
    TextView workCompanyTypeText;
    @ViewById(R.id.workCompanyName)
    InputEditText workCompanyNameEdit;
    @ViewById(R.id.workJobTitle)
    TextView workJobTitleText;
    @ViewById(R.id.workCompanyAge)
    TextView workCompanyAgeText;
    @ViewById(R.id.workProvince)
    TextView workProvinceText;
    @ViewById(R.id.workCity)
    TextView workCityText;
    @ViewById(R.id.workAddress)
    InputEditText workAddressEdit;
    @ViewById(R.id.workIncomePayWay)
    TextView workIncomePayWayText;
    @ViewById(R.id.workMonthCardSalaryView)
    View workMonthCardSalaryView;
    @ViewById(R.id.workMonthCardSalary)
    InputEditText workMonthCardSalaryEdit;
    @ViewById(R.id.workMonthSalaryTotal)
    InputEditText workMonthSalaryTotalEdit;
    @ViewById(R.id.workIncomeProof)
    TextView workIncomeProofText;
    @ViewById(R.id.workSocialFund)
    TextView workSocialFundText;
    @ViewById(R.id.workFundYearsView)
    View workFundYearsView;
    @ViewById(R.id.workFundYears)
    TextView workFundYearsText;
    @ViewById(R.id.workSocialYearsView)
    View workSocialYearsView;
    @ViewById(R.id.workSocialYears)
    TextView workSocialYearsText;
    @ViewById(R.id.workHasAvocation)
    TextView workHasAvocationText;
    @ViewById(R.id.workAvoactionView)
    View workAvoactionView;
    @ViewById(R.id.workAvocation)
    InputEditText workAvocationEdit;
    @ViewById(R.id.workAvocationMonthSalary)
    InputEditText workAvocationMonthSalaryEdit;

    //电商
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

    private ProvinceEnum provinceEnum;
    IIdentityPresenter mPresenter;
    IdentitySaveRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new IdentityPresenterImpl(this);
        request = new IdentitySaveRequest();
        request.setUserId(getUserId());
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_identity_title));
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

    @Click(R.id.identity)
    void clickIdentity() {
        mSelectView.setData(IdentityEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IdentityEnum mEnum = IdentityEnum.values()[position];
                if (mEnum.equals(IdentityEnum.企业户) || mEnum.equals(IdentityEnum.个体户)) {
                    epView.setVisibility(View.VISIBLE);
                    woView.setVisibility(View.GONE);
                    dsView.setVisibility(View.GONE);
                } else if (mEnum.equals(IdentityEnum.工薪族) || mEnum.equals(IdentityEnum.其他)) {
                    epView.setVisibility(View.GONE);
                    woView.setVisibility(View.VISIBLE);
                    dsView.setVisibility(View.GONE);
                }else if(mEnum.equals(IdentityEnum.电商))
                {
                    epView.setVisibility(View.GONE);
                    woView.setVisibility(View.GONE);
                    dsView.setVisibility(View.VISIBLE);
                }
                mIdentityText.setText(val);
                request.setCapacity(mEnum.getCode());
            }
        });
        show();
    }

    //企业主 个体户 item click
    //法人股东
    @Click(R.id.epLegalPerson)
    void clickepLegalPerson() {
        mSelectView.setData(LegalPersonEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                LegalPersonEnum mEnum = LegalPersonEnum.values()[position];
                epLegalPersonText.setText(val);
                request.setCorporation(mEnum.getCode());
            }
        });
        show();
    }
    //所属行业
    @Click(R.id.epIndustry)
    void clickepIndustry() {
        mSelectView.setData(IndustryEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IndustryEnum mEnum = IndustryEnum.values()[position];
                request.setEpIndustry(mEnum.getCode());
                epIndustryText.setText(val);
            }
        });
        show();
    }
    //企业经营地
    @Click(R.id.epCompanyPosition)
    void clickepCompanyPosition() {
        mSelectView.setData(CompanyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyPositionEnum mEnum = CompanyPositionEnum.values()[position];
                request.setEpBuss(mEnum.getCode());
                epCompanyPositionText.setText(val);
            }
        });
        show();
    }
    //经营年限
    @Click(R.id.epOperationPeriod)
    void clickepOperationPeriod() {
        mSelectView.setData(OperatorYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                OperatorYearsEnum mEnum = OperatorYearsEnum.values()[position];
                request.setEpPeriod(mEnum.getCode());
                epOperationPeriodText.setText(val);
            }
        });
        show();
    }
    //注册资金
    @Click(R.id.epRegistCapital)
    void clickepRegistCapital() {
        mSelectView.setData(RegistCaptialEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                RegistCaptialEnum mEnum = RegistCaptialEnum.values()[position];
                request.setEpCapital(mEnum.getCode());
                epRegistCapitalText.setText(val);
            }
        });
        show();
    }
    //年开票额
    @Click(R.id.epBilling)
    void clickepBilling() {
        mSelectView.setData(BillingYearEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                BillingYearEnum mEnum = BillingYearEnum.values()[position];
                request.setEpTicket(mEnum.getCode());
                epBillingText.setText(val);
            }
        });
        show();
    }
    //年营业收入
    @Click(R.id.epIncomeYear)
    void clickepIncomeYear() {
        mSelectView.setData(IncomeYearEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomeYearEnum mEnum = IncomeYearEnum.values()[position];
                request.setEpAnnualRevenue(mEnum.getCode());
                epIncomeYearText.setText(val);
            }
        });
        show();
    }
    //月对公流水
    @Click(R.id.epMonthPublicWater)
    void clickepMonthPublicWater() {
        mSelectView.setData(MonthPublicWaterEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                MonthPublicWaterEnum mEnum = MonthPublicWaterEnum.values()[position];
                request.setEpContraryWater(mEnum.getCode());
                epMonthPublicWaterText.setText(val);
            }
        });
        show();
    }
    //月对私流水
    @Click(R.id.epMonthPrivateWater)
    void clickepMonthPrivateWater() {
        mSelectView.setData(MonthPrivateWaterEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                MonthPrivateWaterEnum mEnum = MonthPrivateWaterEnum.values()[position];
                epMonthPrivateWaterText.setText(val);
                request.setEpPrivateWater(mEnum.getCode());
            }
        });
        show();
    }
    //资产负债率
    @Click(R.id.epDebtRate)
    void clickepDebtRate() {
        mSelectView.setData(DebtRateEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtRateEnum mEnum = DebtRateEnum.values()[position];
                request.setEpLeverage(mEnum.getCode());
                epDebtRateText.setText(val);
            }
        });
        show();
    }
    //年净利润
    @Click(R.id.epProfit)
    void clickepProfit() {
        mSelectView.setData(NetProfitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                NetProfitEnum mEnum = NetProfitEnum.values()[position];
                epProfitText.setText(val);
                request.setEpNetProfit(mEnum.getCode());
            }
        });
        show();
    }
    //企业欠款情况
    @Click(R.id.epDebtSituation)
    void clickDebt() {
        mSelectView.setData(DebtSituationEnterpriseEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtSituationEnterpriseEnum mEnum = DebtSituationEnterpriseEnum.values()[position];
                request.setEpDebt(mEnum.getCode());
                if (mEnum.equals(DebtSituationEnterpriseEnum.无欠款)) epDebtSituationView.setVisibility(View.GONE);
                else epDebtSituationView.setVisibility(View.VISIBLE);
                epDebtSituationText.setText(val);
            }
        });
        show();
    }
    //欠款余额
    @Click(R.id.epDebtBalance)
    void clickDebtBalance() {
        mSelectView.setData(DebtBalanceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtBalanceEnum mEnum = DebtBalanceEnum.values()[position];
                request.setEpDebtAmt(mEnum.getCode());
                epDebtBalanceText.setText(val);
            }
        });
        show();
    }


    //工薪族 其他 item click
    //就职公司类型
    @Click(R.id.workCompanyType)
    void clickType() {
        mSelectView.setData(CompanyTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyTypeEnum mEnum = CompanyTypeEnum.values()[position];
                request.setCompanyType(mEnum.getCode());
                workCompanyTypeText.setText(val);
            }
        });
        show();
    }
    //职位
    @Click(R.id.workJobTitle)
    void clickPosition() {
        mSelectView.setData(JobTitleEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                JobTitleEnum mEnum = JobTitleEnum.values()[position];
                request.setJob(mEnum.getCode());
                workJobTitleText.setText(val);
            }
        });
        show();
    }
    //现单位工龄
    @Click(R.id.workCompanyAge)
    void clickIWorktime() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                WorkYearsEnum mEnum = WorkYearsEnum.values()[position];
                request.setCurrSeniority(mEnum.getCode());
                workCompanyAgeText.setText(val);
            }
        });
        show();
    }
    //工作地(省)
    @Click(R.id.workProvince)
    void clickProvince() {
        mSelectView.setData(ProvinceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                ProvinceEnum mEnum = ProvinceEnum.values()[position];
                provinceEnum = mEnum;
                workProvinceText.setText(val);
                request.setWorkProvince(mEnum.name());
            }
        });
        show();
    }
    //工作地(市)
    @Click(R.id.workCity)
    void clickCity() {
        if(provinceEnum == null) {
            ToastUtil.showShortToast("请先选择工作地(省)");
            return;
        }
        mSelectView.setData(DistrictUtil.getCities(getApplicationContext(),provinceEnum));
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                request.setWorkCity(val);
                workCityText.setText(val);
            }
        });
        show();
    }
    //收入发放方式
    @Click(R.id.workIncomePayWay)
    void clickIncomepayway() {
        mSelectView.setData(IncomePayMethodEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomePayMethodEnum mEnum = IncomePayMethodEnum.values()[position];
                workIncomePayWayText.setText(val);
                request.setPayWay(mEnum.getCode());
                if(mEnum.equals(IncomePayMethodEnum.银行打卡) || mEnum.equals(IncomePayMethodEnum.部分打卡部分现金)) {
                    workMonthCardSalaryView.setVisibility(View.VISIBLE);
                } else if(mEnum.equals(IncomePayMethodEnum.现金发放)) {
                    workMonthCardSalaryView.setVisibility(View.GONE);
                }
            }
        });
        show();
    }
    //能否提供收入证明
    @Click(R.id.workIncomeProof)
    void clickworkIncomeProof() {
        mSelectView.setData(IncomeProofEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomeProofEnum mEnum = IncomeProofEnum.values()[position];
                workIncomeProofText.setText(val);
                request.setVerifiableIncome(mEnum.getCode());
            }
        });
        show();
    }
    //社保和公积金
    @Click(R.id.workSocialFund)
    void clickSocialFund() {
        mSelectView.setData(SocialFundEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                SocialFundEnum mEnum = SocialFundEnum.values()[position];
                if(mEnum.equals(SocialFundEnum.无社保无公积金)) {
                    workFundYearsView.setVisibility(View.GONE);
                    workSocialYearsView.setVisibility(View.GONE);
                } else if(mEnum.equals(SocialFundEnum.无社保有公积金)) {
                    workFundYearsView.setVisibility(View.VISIBLE);
                    workSocialYearsView.setVisibility(View.GONE);
                } else if(mEnum.equals(SocialFundEnum.有社保无公积金)) {
                    workFundYearsView.setVisibility(View.GONE);
                    workSocialYearsView.setVisibility(View.VISIBLE);
                } else if(mEnum.equals(SocialFundEnum.有社保有公积金)) {
                    workFundYearsView.setVisibility(View.VISIBLE);
                    workSocialYearsView.setVisibility(View.VISIBLE);
                }
                workSocialFundText.setText(val);
                request.setSocialSecurity(mEnum.getCode());
            }
        });
        show();
    }
    //公积金连续缴纳年限
    @Click(R.id.workFundYears)
    void clickFundYears() {
        mSelectView.setData(FundYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                FundYearsEnum mEnum = FundYearsEnum.values()[position];
                workFundYearsText.setText(val);
                request.setAccuFundAge(mEnum.getCode());
            }
        });
        show();
    }
    //社保连续缴纳年限
    @Click(R.id.workSocialYears)
    void clickSocialYears() {
        mSelectView.setData(SocialYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                SocialYearsEnum mEnum = SocialYearsEnum.values()[position];
                workSocialYearsText.setText(val);
                request.setSocialSecurityAge(mEnum.getCode());
            }
        });
        show();
    }
    //有无副业
    @Click(R.id.workHasAvocation)
    void clickAvocation() {
        mSelectView.setData(AvocationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                AvocationEnum mEnum = AvocationEnum.values()[position];
                if(mEnum.equals(AvocationEnum.无副业)) workAvoactionView.setVisibility(View.GONE);
                else workAvoactionView.setVisibility(View.VISIBLE);
                workHasAvocationText.setText(val);
                request.setAvocation(mEnum.getCode());
            }
        });
        show();
    }
    //店铺类型
    @Click(R.id.storeType)
    void clickstoreType() {
        mSelectView.setData(StoreTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                StoreTypeEnum mEnum = StoreTypeEnum.values()[position];
                request.setShopTpye(mEnum.getCode());
                storeType.setText(val);
            }
        });
        show();
    }

    //店铺开始经营时间
    @Click(R.id.storeOpenTime)
    void clickstoreOpenTime() {
        mSelectView.setData(OperatorYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                OperatorYearsEnum mEnum = OperatorYearsEnum.values()[position];
                request.setShopStartTime(mEnum.getCode());
                storeOpenTime.setText(val);
            }
        });
        show();
    }

    //店铺近180天销售总金额
    @Click(R.id.store180Sales)
    void clickstore180Sales() {
        mSelectView.setData(shop180SalesEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                shop180SalesEnum mEnum = shop180SalesEnum.values()[position];
                request.setShopStartTime(mEnum.getCode());
                storeType.setText(val);
            }
        });
        show();
    }


    /** IIdentityView **/
    @Override
    public void onRequest(IdentityModel model) {
        IdentityEnum iEnum = IdentityEnum.find(model.getCapacity());
        if(iEnum != null) {
            mIdentityText.setText(iEnum.name());
            request.setCapacity(iEnum.getCode());
            if(iEnum.equals(IdentityEnum.企业户) || iEnum.equals(IdentityEnum.个体户)) {
                epView.setVisibility(View.VISIBLE);
                woView.setVisibility(View.GONE);
                dsView.setVisibility(View.GONE);
            }else if(iEnum.equals(IdentityEnum.工薪族) || iEnum.equals(IdentityEnum.其他)) {
                epView.setVisibility(View.GONE);
                woView.setVisibility(View.VISIBLE);
                dsView.setVisibility(View.GONE);
            }else if(iEnum.equals(IdentityEnum.电商)) {
                epView.setVisibility(View.GONE);
                woView.setVisibility(View.GONE);
                dsView.setVisibility(View.VISIBLE);
            }
            }

        //企业主 个体户
        LegalPersonEnum lpEnum = LegalPersonEnum.find(model.getCorporation());
        if(lpEnum != null) epLegalPersonText.setText(lpEnum.getTitle());
        if(!TextUtils.isEmpty(model.getEpName())) epNameEdit.setText(model.getEpName());
        if(!TextUtils.isEmpty(model.getEpAddr())) epAddressEdit.setText(model.getEpAddr());
        IndustryEnum industryEnum = IndustryEnum.find(model.getEpIndustry());
        if(industryEnum != null) epIndustryText.setText(industryEnum.name());
        if(model.getEpScale() != null && model.getEpScale() != 0) epStaffNumEdit.setText(String.valueOf(model.getEpScale()));
        CompanyPositionEnum cpEnum = CompanyPositionEnum.find(model.getEpBuss());
        if(cpEnum != null) epCompanyPositionText.setText(cpEnum.name());
        OperatorYearsEnum oyEnum = OperatorYearsEnum.find(model.getEpPeriod());
        if(oyEnum != null) epOperationPeriodText.setText(oyEnum.getTitle());
        //注册资金
        RegistCaptialEnum rcEnum = RegistCaptialEnum.find(model.getEpCapital());
        if(rcEnum != null) epRegistCapitalText.setText(rcEnum.getTitle());
        //主营业务
        if(!TextUtils.isEmpty(model.getEpMain())) epBusinessEdit.setText(model.getEpMain());
        //年营业额
        if(model.getEpAnnualTurnover() != null && model.getEpAnnualTurnover() != 0) epTurnoverEdit.setText(String.valueOf(model.getEpAnnualTurnover()));
        //年开票额
        BillingYearEnum byEnum = BillingYearEnum.find(model.getEpTicket());
        if(byEnum != null) epBillingText.setText(byEnum.getTitle());
        //年营业收入
        IncomeYearEnum iyEnum = IncomeYearEnum.find(model.getEpAnnualRevenue());
        if(iyEnum != null) epIncomeYearText.setText(iyEnum.getTitle());
        //月对公流水
        MonthPublicWaterEnum mpwEnum = MonthPublicWaterEnum.find(model.getEpContraryWater());
        if(mpwEnum != null) epMonthPublicWaterText.setText(mpwEnum.getTitle());
        //月对私流水
        MonthPrivateWaterEnum mpriwEnum = MonthPrivateWaterEnum.find(model.getEpPrivateWater());
        if(mpriwEnum != null) epMonthPrivateWaterText.setText(mpriwEnum.getTitle());
        //资产负债率
        DebtRateEnum drEnum = DebtRateEnum.find(model.getEpLeverage());
        if(drEnum != null) epDebtRateText.setText(drEnum.getTitle());
        //年净利润
        NetProfitEnum npEnum = NetProfitEnum.find(model.getEpNetProfit());
        if(npEnum != null) epProfitText.setText(npEnum.getTitle());
        //企业欠款情况
        DebtSituationEnterpriseEnum dseEnum = DebtSituationEnterpriseEnum.find(model.getEpDebt());
        if(dseEnum != null) {
            epDebtSituationText.setText(dseEnum.name());
            if(dseEnum.equals(DebtSituationEnterpriseEnum.无欠款)) epDebtSituationView.setVisibility(View.GONE);
            else epDebtSituationView.setVisibility(View.VISIBLE);
        }

        //欠款机构名称
        if(!TextUtils.isEmpty(model.getEpDebtName())) epDebtInstitutionNameEdit.setText(model.getEpDebtName());
        //欠款余额
        DebtBalanceEnum dbEnum = DebtBalanceEnum.find(model.getEpDebtAmt());
        if(dbEnum != null) epDebtBalanceText.setText(dbEnum.getTitle());
        //月还款额
        if(model.getEpMonthRepay() != null && model.getEpMonthRepay() != 0) epMonthRepaymentEdit.setText(String.valueOf(model.getEpMonthRepay()));


        //工薪族 其他
        //就职公司类型
        CompanyTypeEnum ctEnum = CompanyTypeEnum.find(model.getCompanyType());
        if(ctEnum != null) workCompanyTypeText.setText(ctEnum.getTitle());
        //就职公司名称
        if(!TextUtils.isEmpty(model.getCompanyName())) workCompanyNameEdit.setText(model.getCompanyName());
        //职位
        JobTitleEnum jtEnum = JobTitleEnum.find(model.getJob());
        if(jtEnum != null) workJobTitleText.setText(jtEnum.name());
        //现单位工龄
        WorkYearsEnum wyEnum = WorkYearsEnum.find(model.getCurrSeniority());
        if(wyEnum != null) workCompanyAgeText.setText(wyEnum.getTitle());
        //工作地(省)
        provinceEnum = ProvinceEnum.find(model.getWorkProvince());
        if(provinceEnum != null) workProvinceText.setText(provinceEnum.name());
        //工作地(市)
        //String city = DistrictUtil.getCity(this, provinceEnum, model.getWorkCity());
        String city =  model.getWorkCity();
        if(!TextUtils.isEmpty(city)) workCityText.setText(city);
        //工作详细地址
        if(!TextUtils.isEmpty(model.getWorkAddr())) workAddressEdit.setText(model.getWorkAddr());
        //收入发放方式
        IncomePayMethodEnum ipmEnum = IncomePayMethodEnum.find(model.getPayWay());
        if(ipmEnum != null) {
            workIncomePayWayText.setText(ipmEnum.getTitle());
            if(ipmEnum.equals(IncomePayMethodEnum.现金发放)) workMonthCardSalaryView.setVisibility(View.GONE);
            else workMonthCardSalaryView.setVisibility(View.VISIBLE);
        }
        //月打卡工资
        if(model.getWages() != null && model.getWages() != 0) workMonthCardSalaryEdit.setText(String.valueOf(model.getWages()));
        //月均总收入
        if(model.getMonthlyIncome() != null && model.getMonthlyIncome() != 0) workMonthSalaryTotalEdit.setText(String.valueOf(model.getMonthlyIncome()));
        //能否提供收入证明
        IncomeProofEnum ipEnum = IncomeProofEnum.find(model.getVerifiableIncome());
        if(ipEnum != null) workIncomeProofText.setText(ipEnum.name());
        //社保和公积金
        SocialFundEnum sfEnum = SocialFundEnum.find(model.getSocialSecurity());
        if(sfEnum != null) {
            workSocialFundText.setText(sfEnum.name());
            if(sfEnum.equals(SocialFundEnum.有社保有公积金) || sfEnum.equals(SocialFundEnum.有社保无公积金)) {
                workSocialYearsView.setVisibility(View.VISIBLE);
            }
            if(sfEnum.equals(SocialFundEnum.有社保有公积金) || sfEnum.equals(SocialFundEnum.无社保有公积金)){
                workFundYearsView.setVisibility(View.VISIBLE);
            }
        }
        //公积金连续缴纳年限
        FundYearsEnum fyEnum = FundYearsEnum.find(model.getAccuFundAge());
        if(fyEnum != null) workFundYearsText.setText(fyEnum.getTitle());
        //社保连续缴纳年限
        SocialYearsEnum syEnum = SocialYearsEnum.find(model.getSocialSecurityAge());
        if(syEnum != null) workSocialYearsText.setText(syEnum.getTitle());
        //有无副业
        AvocationEnum aEnum = AvocationEnum.find(model.getAvocation());
        if(aEnum != null) {
            workHasAvocationText.setText(aEnum.name());
            if(aEnum.equals(AvocationEnum.有副业)) workAvoactionView.setVisibility(View.VISIBLE);
            else workAvoactionView.setVisibility(View.GONE);
        }
        //副业内容
        if(!TextUtils.isEmpty(model.getAvocationInfo())) workAvocationEdit.setText(model.getAvocationInfo());
        //副业月收入
        if(model.getAvocationAmt() != null && model.getAvocationAmt() != 0) workAvocationMonthSalaryEdit.setText(String.valueOf(model.getAvocationAmt()));

        //电商
        StoreTypeEnum mEnum = StoreTypeEnum.find(model.shopTpye);
        if(mEnum != null) storeType.setText(mEnum.getTitle());
        OperatorYearsEnum oy1Enum = OperatorYearsEnum.find(model.shopStartTime);
        if(oy1Enum != null) storeOpenTime.setText(oy1Enum.getTitle());
        storeName.setText(model.shopName);
        storeAddress.setText(model.shopAddr);
        storeUsername.setText(model.shopUserName);
        storeAccount.setText(model.shopAccount);
        if(model.shopAvgMon!=null) storeMonthTurnover.setText(String.valueOf(model.shopAvgMon));
        if(model.shopMonPay!=null) storeMonthPays.setText(String.valueOf(model.shopMonPay));
        storeRegistUser.setText(model.shopReg);
        loanUsername.setText(model.borrowerName);
        loanMobile.setText(model.borrowerPhone);
        loanMobile1.setText(model.borrowerSecondPhone);
        loanTelPhone.setText(model.borrowerTel);
        loanIdCard.setText(model.borrowerIdNum);
        loanAddress.setText(model.borrowerCompanyAddr);
        contactsUsername.setText(model.contactName);
        contactsRelationship.setText(model.contactRelation);
        contactsConpany.setText(model.contactCompany);
        contactsMobile.setText(model.contactPhone);

    }

    @Override
    public IdentitySaveRequest getSaveRequest() {
        //企业主 个体户
        //企业名称
        request.setEpName(epNameEdit.getString());
        //企业地址
        request.setEpAddr(epAddressEdit.getString());
        //企业规模(人)
        if(TextUtils.isEmpty(epStaffNumEdit.getString())) request.setEpScale(0);
        else request.setEpScale(Integer.valueOf(epStaffNumEdit.getString()));
        //主营业务
        request.setEpMain(epBusinessEdit.getString());
        //年营业额(万元)
        if(TextUtils.isEmpty(epTurnoverEdit.getString())) request.setEpAnnualTurnover(0);
        else request.setEpAnnualTurnover(Integer.valueOf(epTurnoverEdit.getString()));
        //欠款机构名称
        request.setEpDebtName(epDebtInstitutionNameEdit.getString());
        //月还款额
        if(TextUtils.isEmpty(epMonthRepaymentEdit.getString())) request.setEpMonthRepay(0);
        else request.setEpMonthRepay(Integer.valueOf(epMonthRepaymentEdit.getString()));

        //工薪族 其他
        //就职公司名称
        request.setCompanyName(workCompanyNameEdit.getString());
        //工作详细地址
        request.setWorkAddr(workAddressEdit.getString());
        //月打卡工资
        if(TextUtils.isEmpty(workMonthCardSalaryEdit.getString())) request.setWages(0);
        else request.setWages(Integer.valueOf(workMonthCardSalaryEdit.getString()));
        //月均总收入
        if(TextUtils.isEmpty(workMonthSalaryTotalEdit.getString())) request.setMonthlyIncome(0);
        else request.setMonthlyIncome(Integer.valueOf(workMonthSalaryTotalEdit.getString()));
        //副业内容
        request.setAvocationInfo(workAvocationEdit.getString());
        //副业月收入
        if(TextUtils.isEmpty(workAvocationMonthSalaryEdit.getString())) request.setAvocationAmt(0);
        else request.setAvocationAmt(Integer.valueOf(workAvocationMonthSalaryEdit.getString()));

        //电商
        request.setShopName(storeName.getString());
        request.setShopAddr(storeAddress.getString());
        request.setShopUserName(storeUsername.getString());
        request.setShopAccount(storeAccount.getString());
        request.setShopAvgMon(UiUtil.getIntVal(storeMonthTurnover,null));
        request.setShopMonPay(UiUtil.getIntVal(storeMonthPays,null));
        request.setShopReg(storeRegistUser.getString());
        request.setBorrowerName(loanUsername.getString());
        request.setBorrowerPhone(loanMobile.getString());
        request.setBorrowerSecondPhone(loanMobile1.getString());
        request.setBorrowerTel(loanTelPhone.getString());
        request.setBorrowerIdNum(loanIdCard.getString());
        request.setBorrowerCompanyAddr(loanAddress.getString());
        request.setContactName(contactsUsername.getString());
        request.setContactRelation(contactsRelationship.getString());
        request.setContactCompany(contactsConpany.getString());
        request.setContactPhone(contactsMobile.getString());

        return request;
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }
    /** IIdentityView **/
}
