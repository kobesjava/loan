package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
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
import com.qtt.jinrong.enums.WorkYearsEnum;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.DistrictUtil;
import com.qtt.jinrong.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 身份信息(我的融资需求书)
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_identity)
public class IdentityActivity extends BaseSelectActivity {

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

    //enterprise persional item
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

    //woker other item
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

    private ProvinceEnum provinceEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                ToastUtil.showShortToast("保存");
            }
        });

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
                } else if (mEnum.equals(IdentityEnum.工薪族) || mEnum.equals(IdentityEnum.其他)) {
                    epView.setVisibility(View.GONE);
                    woView.setVisibility(View.VISIBLE);
                }
                mIdentityText.setText(val);
            }
        });
        show();
    }


    //enterprise personal item click
    @Click(R.id.epLegalPerson)
    void clickepLegalPerson() {
        mSelectView.setData(LegalPersonEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                LegalPersonEnum mEnum = LegalPersonEnum.values()[position];
                epLegalPersonText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epIndustry)
    void clickepIndustry() {
        mSelectView.setData(IndustryEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IndustryEnum mEnum = IndustryEnum.values()[position];
                epIndustryText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epCompanyPosition)
    void clickepCompanyPosition() {
        mSelectView.setData(CompanyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyPositionEnum mEnum = CompanyPositionEnum.values()[position];
                epCompanyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epOperationPeriod)
    void clickepOperationPeriod() {
        mSelectView.setData(OperatorYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                OperatorYearsEnum mEnum = OperatorYearsEnum.values()[position];
                epOperationPeriodText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epRegistCapital)
    void clickepRegistCapital() {
        mSelectView.setData(RegistCaptialEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                RegistCaptialEnum mEnum = RegistCaptialEnum.values()[position];
                epRegistCapitalText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epBilling)
    void clickepBilling() {
        mSelectView.setData(BillingYearEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                BillingYearEnum mEnum = BillingYearEnum.values()[position];
                epBillingText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epIncomeYear)
    void clickepIncomeYear() {
        mSelectView.setData(IncomeYearEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomeYearEnum mEnum = IncomeYearEnum.values()[position];
                epIncomeYearText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epMonthPublicWater)
    void clickepMonthPublicWater() {
        mSelectView.setData(MonthPublicWaterEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                MonthPublicWaterEnum mEnum = MonthPublicWaterEnum.values()[position];
                epMonthPublicWaterText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epMonthPrivateWater)
    void clickepMonthPrivateWater() {
        mSelectView.setData(MonthPrivateWaterEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                MonthPrivateWaterEnum mEnum = MonthPrivateWaterEnum.values()[position];
                epMonthPrivateWaterText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epDebtRate)
    void clickepDebtRate() {
        mSelectView.setData(DebtRateEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtRateEnum mEnum = DebtRateEnum.values()[position];
                epDebtRateText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epProfit)
    void clickepProfit() {
        mSelectView.setData(NetProfitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                NetProfitEnum mEnum = NetProfitEnum.values()[position];
                epProfitText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epDebtSituation)
    void clickDebt() {
        mSelectView.setData(DebtSituationEnterpriseEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtSituationEnterpriseEnum mEnum = DebtSituationEnterpriseEnum.values()[position];
                if (mEnum.equals(DebtSituationEnterpriseEnum.无欠款)) epDebtSituationView.setVisibility(View.GONE);
                else epDebtSituationView.setVisibility(View.VISIBLE);
                epDebtSituationText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.epDebtBalance)
    void clickDebtBalance() {
        mSelectView.setData(DebtBalanceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                DebtBalanceEnum mEnum = DebtBalanceEnum.values()[position];
                epDebtBalanceText.setText(val);
            }
        });
        show();
    }


    //worker other item click
    @Click(R.id.workCompanyType)
    void clickType() {
        mSelectView.setData(CompanyTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyTypeEnum mEnum = CompanyTypeEnum.values()[position];
                workCompanyTypeText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workJobTitle)
    void clickPosition() {
        mSelectView.setData(JobTitleEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                JobTitleEnum mEnum = JobTitleEnum.values()[position];
                workJobTitleText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workCompanyAge)
    void clickIWorktime() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                WorkYearsEnum mEnum = WorkYearsEnum.values()[position];
                workCompanyAgeText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workProvince)
    void clickProvince() {
        mSelectView.setData(ProvinceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                ProvinceEnum mEnum = ProvinceEnum.values()[position];
                provinceEnum = mEnum;
                workProvinceText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workCity)
    void clickCity() {
        mSelectView.setData(DistrictUtil.getCities(getApplicationContext(),provinceEnum));
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                //ProvinceEnum mEnum = ProvinceEnum.values()[position];
                //provinceEnum = mEnum;
                workCityText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workIncomePayWay)
    void clickIncomepayway() {
        mSelectView.setData(IncomePayMethodEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomePayMethodEnum mEnum = IncomePayMethodEnum.values()[position];
                workIncomePayWayText.setText(val);
                if(mEnum.equals(IncomePayMethodEnum.银行打卡) || mEnum.equals(IncomePayMethodEnum.部分打卡部分现金)) {
                    workMonthCardSalaryView.setVisibility(View.VISIBLE);
                } else if(mEnum.equals(IncomePayMethodEnum.现金发放)) {
                    workMonthCardSalaryView.setVisibility(View.GONE);
                }
            }
        });
        show();
    }
    @Click(R.id.workIncomeProof)
    void clickworkIncomeProof() {
        mSelectView.setData(IncomeProofEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomeProofEnum mEnum = IncomeProofEnum.values()[position];
                workIncomeProofText.setText(val);
            }
        });
        show();
    }
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
            }
        });
        show();
    }
    @Click(R.id.workFundYears)
    void clickFundYears() {
        mSelectView.setData(FundYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                FundYearsEnum mEnum = FundYearsEnum.values()[position];
                workFundYearsText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workSocialYears)
    void clickSocialYears() {
        mSelectView.setData(SocialYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                SocialYearsEnum mEnum = SocialYearsEnum.values()[position];
                workSocialYearsText.setText(val);
            }
        });
        show();
    }
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
            }
        });
        show();
    }

}
