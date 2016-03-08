package com.qtt.jinrong.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.framework.util.DateUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.DemandModel;
import com.qtt.jinrong.bean.account.DemandSaveRequest;
import com.qtt.jinrong.enums.LoanPurposeEnum;
import com.qtt.jinrong.enums.RepayWayEnum;
import com.qtt.jinrong.enums.RepaymentSourceEnum;
import com.qtt.jinrong.presenter.IDemandInfoPresenter;
import com.qtt.jinrong.presenter.impl.DemandInfoPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.datepicker.SlideDateTimeListener;
import com.qtt.jinrong.ui.widget.datepicker.SlideDateTimePicker;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.view.IDemandsInfoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

/**
 * Created by yanxin on 16/3/3.
 */
@EActivity(R.layout.activity_user_demands)
public class DemandsActivity extends BaseSelectActivity implements IDemandsInfoView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.amount)
    InputEditText amountEdit;
    @ViewById(R.id.term)
    InputEditText termEdit;
    @ViewById(R.id.time)
    TextView mTimeText;
    @ViewById(R.id.purpose)
    TextView mPurposeText;
    @ViewById(R.id.repaysource)
    TextView mRepaymentSource;
    @ViewById(R.id.repayway)
    TextView mRepayWayText;

    DemandSaveRequest request;
    IDemandInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request = new DemandSaveRequest();
        mPresenter = new DemandInfoPresenterImpl(this);
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
                mPresenter.save();
            }
        });

    }

    @Click(R.id.time)
    void clickTime() {
        new SlideDateTimePicker.Builder(getSupportFragmentManager()).
                setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        String dateStr = DateUtil.getDate(date);
                        request.setLoDate(dateStr);
                        mTimeText.setText(dateStr);
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
                request.setLoPurpose(LoanPurposeEnum.values()[position].getCode());
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
                request.setLoPaymentSrc(RepaymentSourceEnum.values()[position].getCode());
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
                request.setLoPaymentWay(RepayWayEnum.values()[position].getCode());
                mRepayWayText.setText(val);
            }
        });
        show();
    }

    /*** IDemandsInfoView ***/
    @Override
    public void onRequest(DemandModel model) {
        if (model == null) return;

        if(model.getLoMoney() != null) amountEdit.setText(String.valueOf(model.getLoMoney()));
        if(model.getLoExpires() != null) termEdit.setText(String.valueOf(model.getLoExpires()));
        if(!TextUtils.isEmpty(model.getLoDate())) mTimeText.setText(model.getLoDate());
        if(model.getLoPurpose() != null) {
            LoanPurposeEnum loanPurposeEnum = LoanPurposeEnum.find(model.getLoPurpose());
            if(loanPurposeEnum != null) mPurposeText.setText(loanPurposeEnum.name());
        }
        if(model.getLoPaymentSrc() != null) {
            RepaymentSourceEnum repaymentSourceEnum = RepaymentSourceEnum.find(model.getLoPaymentSrc());
            if(repaymentSourceEnum != null) mRepaymentSource.setText(repaymentSourceEnum.name());
        }
        if(model.getLoPaymentWay() != null) {
            RepayWayEnum repayWayEnum = RepayWayEnum.find(model.getLoPaymentWay());
            if(repayWayEnum != null) mRepaymentSource.setText(repayWayEnum.name());
        }
    }

    @Override
    public void onSaveSuccess() {
        finish();
    }

    @Override
    public DemandSaveRequest getSaveRequest() {
        if(!TextUtils.isEmpty(amountEdit.getString())) request.setLoMoney(Float.valueOf(amountEdit.getString()));
        if(!TextUtils.isEmpty(termEdit.getString())) request.setLoExpires(Integer.parseInt(termEdit.getString()));
        return request;
    }
    /*** IDemandsInfoView ***/
}
