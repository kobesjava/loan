package com.android.finance.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.enums.LoanPurposeEnum;
import com.android.finance.enums.RepayWayEnum;
import com.android.finance.enums.RepaymentSourceEnum;
import com.android.finance.ui.activity.common.BaseSelectActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.SelectPopView;
import com.android.finance.ui.widget.datepicker.SlideDateTimeListener;
import com.android.finance.ui.widget.datepicker.SlideDateTimePicker;
import com.android.finance.util.ToastUtil;
import com.finance.framework.util.DateUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_demands)
public class DemandsActivity extends BaseSelectActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.time)
    TextView mTimeText;

    @ViewById(R.id.purpose)
    TextView mPurposeText;

    @ViewById(R.id.repaysource)
    TextView mRepaymentSource;

    @ViewById(R.id.repayway)
    TextView mRepayWayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle(getString(R.string.user_demands_title));
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

    @Click(R.id.time)
    void clickTime() {
        new SlideDateTimePicker.Builder(getSupportFragmentManager()).
                setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        mTimeText.setText(DateUtil.getDate(date));
                    }
                }).setInitialDate(new Date())
                .setTitle(getString(R.string.user_loan_use_time))
                .build()
                .show();
    }

    @Click(R.id.purpose)
    void clickPurpose() {
        mSelectView.setData(LoanPurposeEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mPurposeText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.repaysource)
    void clickRepaysource() {
        mSelectView.setData(RepaymentSourceEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mRepaymentSource.setText(val);
            }
        });
        show();
    }

    @Click(R.id.repayway)
    void clickRepayWay() {
        mSelectView.setData(RepayWayEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                mRepayWayText.setText(val);
            }
        });
        show();
    }

}
