package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.enums.AvocationEnum;
import com.qtt.jinrong.enums.CompanyTypeEnum;
import com.qtt.jinrong.enums.FundYearsEnum;
import com.qtt.jinrong.enums.IdentityEnum;
import com.qtt.jinrong.enums.IncomMethodEnum;
import com.qtt.jinrong.enums.IncomeProofEnum;
import com.qtt.jinrong.enums.PositionEnum;
import com.qtt.jinrong.enums.ProvinceEnum;
import com.qtt.jinrong.enums.SocialFundEnum;
import com.qtt.jinrong.enums.SocialYearsEnum;
import com.qtt.jinrong.enums.WorkYearsEnum;
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
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_identity)
public class IdentityActivity extends BaseSelectActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.identity)
    TextView mIdentityText;
    @ViewById(R.id.type)
    TextView mTypeText;
    @ViewById(R.id.name)
    InputEditText mNameText;
    @ViewById(R.id.position)
    TextView mPositionText;
    @ViewById(R.id.worktime)
    TextView mWorkYearsText;
    @ViewById(R.id.province)
    TextView mProvinceText;
    @ViewById(R.id.city)
    TextView mCityText;
    @ViewById(R.id.address)
    InputEditText mAddressText;
    @ViewById(R.id.incomepayway)
    TextView mIncomePaymentWayText;
    @ViewById(R.id.salaryMonthCard)
    InputEditText mSalaryMonthCardText;
    @ViewById(R.id.salaryMonthTotal)
    InputEditText mSalaryMonthTotalText;
    @ViewById(R.id.proof)
    TextView mIncomeProofText;
    @ViewById(R.id.socialFund)
    TextView mSocialFundText;
    @ViewById(R.id.fundYears)
    TextView mFundYearsText;
    @ViewById(R.id.socialYears)
    TextView mSocialYearsText;
    @ViewById(R.id.avocation)
    TextView mAvocationText;


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
                mIdentityText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.type)
    void clickType() {
        mSelectView.setData(CompanyTypeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mTypeText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.position)
    void clickPosition() {
        mSelectView.setData(PositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mPositionText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.worktime)
    void clickIWorktime() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mWorkYearsText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.province)
    void clickProvince() {
        mSelectView.setData(ProvinceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mProvinceText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.city)
    void clickCity() {

    }

    @Click(R.id.incomepayway)
    void clickIncomepayway() {
        mSelectView.setData(IncomMethodEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mIncomePaymentWayText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.proof)
    void clickProof() {
        mSelectView.setData(IncomeProofEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mIncomeProofText.setText(val);
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
                mSocialFundText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.fundYears)
    void clickFundYears() {
        mSelectView.setData(FundYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mFundYearsText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.socialYears)
    void clickSocialYears() {
        mSelectView.setData(SocialYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mSocialYearsText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.avocation)
    void clickAvocation() {
        mSelectView.setData(AvocationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mAvocationText.setText(val);
            }
        });
        show();
    }

}
