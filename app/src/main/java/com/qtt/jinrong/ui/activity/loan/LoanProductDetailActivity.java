package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.loan.LoanProductDetail;
import com.qtt.jinrong.presenter.ILoanProductPresenter;
import com.qtt.jinrong.presenter.impl.LoanProductPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.user.LoginActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.ImgText;
import com.qtt.jinrong.ui.widget.TabIndictor;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanProductDetailView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 贷款产品详情
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_loan_product_detail)
public class LoanProductDetailActivity extends BaseActivity implements ILoanProductDetailView {

    /** 金额*/
    public final static String INTENT_TOTAL = "INTENT_TOTAL";
    /** 期限*/
    public final static String INTENT_TERM = "INTENT_TERM";
    public final static String INTENT_LOAN = "INTENT_LOAN";

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;
    @ViewById(R.id.img)
    SimpleDraweeView mProductIcon;
    @ViewById(R.id.name)
    TextView mProductName;
    @ViewById(R.id.monthRate)
    TextView monthRateText;
    @ViewById(R.id.imgText)
    ImgText mImgText;
    @ViewById(R.id.amount)
    EditText mAmount;
    @ViewById(R.id.term)
    EditText mTerm;
    @ViewById(R.id.loanTip)
    TextView mLoanTip;
    @ViewById(R.id.interestTotal)
    TextView mInterestTotal;
    @ViewById(R.id.interestMonth)
    TextView mInterestMonth;
    @ViewById(R.id.time)
    TextView mTime;
    @ViewById(R.id.material)
    TabIndictor mTabIndictor;

    LoanModel mLoanModel;
    LoanProductDetail mDetail;
    ILoanProductPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoanProductPresenterImpl(this);
        mLoanModel = mIntent.getParcelableExtra(INTENT_LOAN);
    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.product_detail_title));
        mTitleBar.setActivity(this);

        mProductName.setText(mLoanModel.getTitle());

        try {
            Uri uri = Uri.parse(mLoanModel.getThumpImg());
            mProductIcon.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片出错", "url=" + mLoanModel.getThumpImg() + " Exception=" + e.getMessage());
        }

        mTabIndictor.setAdapter(new TabIndictor.BaseAdapter() {
            @Override
            public CharSequence getString(int position) {
                if(mDetail == null) return "";
                else if(position == 0) return Html.fromHtml(mDetail.getApply());
                else if(position == 1) return Html.fromHtml(mDetail.getMater());
                else if(position == 2) return Html.fromHtml(mDetail.getRepay());
                return "";
            }
        });

        mPresenter.request();
    }

    @Click(R.id.interestTotalView)
    void clickInsterestTotal() {
        if(mDetail == null) return;
        String s = "总利息=利息+服务费\n参考月利率 : "+mDetail.getRateLow()+"%\n参考服务费率 : "+mDetail.getRateDetail()+"%";
        if(mDialog == null) {
            mDialog = AlertDialogUtils.showPrompt(this, "总利息说明", s);
        }
        mDialog.show();
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {
        if(mDetail == null) return;

        if(mUserInfo == null) {
            Intent intent = new Intent(this, GeneratedClassUtils.get(LoginActivity.class));
            startActivity(intent);
            return;
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(mAmount.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int term = 0;
        try {
            term = Integer.parseInt(mTerm.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, GeneratedClassUtils.get(LoanApplyAptitudeVerifyActivity.class));
        intent.putExtra(LoanApplyAptitudeVerifyActivity.INTENT_PRODUCT_ID,mLoanModel.getProductId());
        intent.putExtra(LoanApplyAptitudeVerifyActivity.INTENT_RESPONSE_AMOUNT,amount);
        intent.putExtra(LoanApplyAptitudeVerifyActivity.INTENT_RESPONSE_TERM,term);
        startActivity(intent);
    }

    /**
     * 计算总利息 月供
     */
    private void updateInterestRate() {
        Float rateMonth = 0f;
        if(mDetail.getRateLow() != null && mDetail.getRateLow()>0) rateMonth = mDetail.getRateLow();
        else if(mDetail.getRateHigh() != null && mDetail.getRateHigh()>0) rateMonth = mDetail.getRateHigh();

        int amount = 0;
        try{
            amount = Integer.parseInt(mAmount.getText().toString());
        }catch (Exception e) {
            e.printStackTrace();
        }

        int term = 0;
        try {
            term = Integer.parseInt(mTerm.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalInterest = (int)(rateMonth*term*amount*10000/100);
        if(mDetail.getRateDetail() != null) totalInterest += amount*mDetail.getRateDetail();
        mInterestTotal.setText(totalInterest+"元");

        int monthRepay = term==0?0:(totalInterest+amount*10000)/term;
        mInterestMonth.setText(monthRepay+"元");
    }


    /***  ILoanProductDetailView  ***/
    @Override
    public String getProductId() {
        return mLoanModel.getProductId();
    }

    @Override
    public void onRequest(LoanProductDetail detail) {
        this.mDetail = detail;

        StringBuilder monthRate = new StringBuilder("月利率 : ");
        if(mDetail.getRateLow() != null) monthRate.append(mDetail.getRateLow()).append("%-");
        if(mDetail.getRateHigh() != null) monthRate.append(mDetail.getRateHigh()).append("%");
        monthRateText.setText(monthRate.toString());

        String exp = mDetail.getExp();
        if(!TextUtils.isEmpty(exp)) exp = Html.fromHtml(exp).toString();
        if(exp.contains(",")) {
            mImgText.setConts(exp.split(","));
        } else {
            mImgText.setConts(exp.split("，"));
        }

        mAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(s.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (amount < mDetail.getAmountLow() || amount > mDetail.getAmountHigh()) {
                    ToastUtil.showShortToast("额度范围: " + mDetail.getAmountLow() + "万-" + mDetail.getAmountHigh() + "万");
                }
                updateInterestRate();
            }
        });
        mTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int term = 0;
                try {
                    term = Integer.parseInt(s.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (term < mDetail.getExpiresLow() || term > mDetail.getExpiresHigh()) {
                    ToastUtil.showShortToast("期限范围: " + mDetail.getExpiresLow() + "个月-" + mDetail.getExpiresHigh() + "个月");
                }
                updateInterestRate();
            }
        });

        Integer amount = mIntent.getIntExtra(INTENT_TOTAL,0)>0?mIntent.getIntExtra(INTENT_TOTAL,0):detail.getAmount();
        Integer term = mIntent.getIntExtra(INTENT_TERM, 0)>0?mIntent.getIntExtra(INTENT_TERM, 0):detail.getExpires();
        mAmount.setText(String.valueOf(amount));
        mTerm.setText(String.valueOf(term));

        mLoanTip.setText("额度范围 : " + mDetail.getAmountLow() + "万-" + mDetail.getAmountHigh() + "万"
                + "  期限范围 : " + mDetail.getExpiresLow() + "个月-" + mDetail.getExpiresHigh() + "个月");

        mTime.setText(mDetail.getComplete());

        mTabIndictor.check();
    }
    /***  ILoanProductDetailView  ***/
}
