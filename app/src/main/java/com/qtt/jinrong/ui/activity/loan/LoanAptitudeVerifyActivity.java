package com.qtt.jinrong.ui.activity.loan;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.enums.CarLinscePositionEnum;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.enums.CompanyPositionEnum;
import com.qtt.jinrong.enums.CompanyTypeEnum;
import com.qtt.jinrong.enums.CreditLimitEnum;
import com.qtt.jinrong.enums.CreditOverdueEnum;
import com.qtt.jinrong.enums.CreditUseStationEnum;
import com.qtt.jinrong.enums.CreditUsedLimitEnum;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.HousePropertyPositionEnum;
import com.qtt.jinrong.enums.HousePropertySituationEnum;
import com.qtt.jinrong.enums.IdentityEnum;
import com.qtt.jinrong.enums.IncomePayMethodEnum;
import com.qtt.jinrong.enums.JobTitleEnum;
import com.qtt.jinrong.enums.LegalPersonEnum;
import com.qtt.jinrong.enums.OperatorYearsEnum;
import com.qtt.jinrong.enums.ProvinceEnum;
import com.qtt.jinrong.enums.SocialFundEnum;
import com.qtt.jinrong.enums.WorkYearsEnum;
import com.qtt.jinrong.http.action.LoanReqsAction;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.ToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 贷款资质审核
 * Created by yanxin on 16/3/9.
 */
@EActivity(R.layout.activity_loan_aptitude_verify)
public class LoanAptitudeVerifyActivity extends BaseSelectActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.identity)
    TextView mIdentifyText;

    @ViewById(R.id.enterprisePersonalMore)
    View enterprisePersonalMore;
    @ViewById(R.id.legalPerson)
    TextView legalPersonText;
    @ViewById(R.id.companyPosition)
    TextView companyPositionText;
    @ViewById(R.id.operationPeriod)
    TextView operationPeriodText;

    @ViewById(R.id.workerOtherMore)
    View workerOtherMore;
    @ViewById(R.id.companyType)
    TextView companyTypeText;
    @ViewById(R.id.companyWorkYears)
    TextView companyWorkYearsText;
    @ViewById(R.id.jobTitle)
    TextView jobTitleText;
    @ViewById(R.id.incomePayWay)
    TextView incomePayWayText;
    @ViewById(R.id.monthSalary)
    InputEditText monthSalaryEdit;
    @ViewById(R.id.socialFund)
    TextView socialFundText;
    @ViewById(R.id.workProvince)
    TextView workProvinceText;
    @ViewById(R.id.workCity)
    TextView workCityText;

    @ViewById(R.id.age)
    InputEditText mAgeEdit;

    @ViewById(R.id.creditStation)
    TextView mCreditText;
    @ViewById(R.id.creditOverdueMore)
    View creditOverdueMore;
    @ViewById(R.id.overdue)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initViews() {
        mTitleBar.setTitle(getString(R.string.loan_vertify_title));
        mTitleBar.setActivity(this);
    }

    @Click(R.id.identity)
    void clickIdentity() {
        mSelectView.setData(IdentityEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IdentityEnum mEnums = IdentityEnum.values()[position];
                if (mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
                    enterprisePersonalMore.setVisibility(View.VISIBLE);
                    workerOtherMore.setVisibility(View.GONE);
                } else if (mEnums.equals(IdentityEnum.工薪族) || mEnums.equals(IdentityEnum.其他)) {
                    enterprisePersonalMore.setVisibility(View.GONE);
                    workerOtherMore.setVisibility(View.VISIBLE);
                }
                mIdentifyText.setText(val);
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
                LegalPersonEnum mEnums = LegalPersonEnum.values()[position];
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
                operationPeriodText.setText(val);
            }
        });
        show();
    }

    //工薪族 其他
    @Click(R.id.companyType)
    void clickCompanyType() {
        mSelectView.setData(CompanyTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyTypeEnum mEnums = CompanyTypeEnum.values()[position];
                companyTypeText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.companyWorkYears)
    void clickCompanyWorkYears() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                WorkYearsEnum mEnums = WorkYearsEnum.values()[position];
                companyWorkYearsText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.jobTitle)
    void clickJobTitle() {
        mSelectView.setData(JobTitleEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                JobTitleEnum mEnums = JobTitleEnum.values()[position];
                jobTitleText.setText(val);
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
                socialFundText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workProvince)
    void clickWorkProvince() {
        mSelectView.setData(ProvinceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                ProvinceEnum mEnums = ProvinceEnum.values()[position];
                workProvinceText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.workCity)
    void clickWorkCity() {
        /*mSelectView.setData(.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                ProvinceEnum mEnums = ProvinceEnum.values()[position];
                workProvinceText.setText(val);
            }
        });
        show();*/
    }


    @Click(R.id.creditStation)
    void clickCreditStation() {
        mSelectView.setData(CreditUseStationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditUseStationEnum mEnum = CreditUseStationEnum.values()[position];
                if(mEnum.equals(CreditUseStationEnum.有逾期)) creditOverdueMore.setVisibility(View.VISIBLE);
                else creditOverdueMore.setVisibility(View.GONE);
                //request.setCreInfo(CreditUseStationEnum.values()[position].getCode());
                mCreditText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.overdue)
    void clickOverdue() {
        mSelectView.setData(CreditOverdueEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditOverdueEnum mEnum = CreditOverdueEnum.values()[position];
                overdueText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.creditLimit)
    void clickCreditLimit() {
        mSelectView.setData(CreditLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditLimitEnum mEnum = CreditLimitEnum.values()[position];
                if (mEnum.equals(CreditLimitEnum.无信用卡)) creditMore.setVisibility(View.GONE);
                else creditMore.setVisibility(View.VISIBLE);
                //request.setCreMoney(CreditLimitEnum.values()[position].getCode());
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
                //request.setHouse(HousePropertyEnum.values()[position].getCode());
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
                housePropertyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.housePropertySituaion)
    void clickHousePropertySituaion() {
        mSelectView.setData(HousePropertySituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertySituationEnum mEnum = HousePropertySituationEnum.values()[position];
                housePropertySituaionText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.carproperty)
    void clickProperty() {
        mSelectView.setData(CarPropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarPropertyEnum mEnum = CarPropertyEnum.values()[position];
                if (mEnum.equals(CarPropertyEnum.无车产)) carPropertyMore.setVisibility(View.GONE);
                else carPropertyMore.setVisibility(View.VISIBLE);
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
                carLicenseText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.btnNext)
    void clickBtnNext() {
        LoanReqsAction.requestLoanProductApply(this, new MCListenerObj.IObjResListener<Response>() {
            @Override
            public void onSuccess(Response response, String url) {
                ToastUtil.showShortToast("成功");
            }

            @Override
            public void onFail(Exception exception, String url) {

            }
        });
    }

}
