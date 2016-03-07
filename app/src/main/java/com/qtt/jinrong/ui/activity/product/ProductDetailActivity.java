package com.qtt.jinrong.ui.activity.product;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.loan.LoanProductDetail;
import com.qtt.jinrong.presenter.ILoanProductPresenter;
import com.qtt.jinrong.presenter.impl.LoanProductPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.ImgText;
import com.qtt.jinrong.ui.widget.TabIndictor;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.view.ILoanProductDetailView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_product_detail)
public class ProductDetailActivity extends BaseActivity implements ILoanProductDetailView {

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
    @ViewById(R.id.interestRate)
    TextView mInterestRate;
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
        mAmount.setText(mIntent.getIntExtra(INTENT_TOTAL, 10) + "");
        mTerm.setText(mIntent.getIntExtra(INTENT_TERM, 12) + "");

        try {
            Uri uri = Uri.parse(mLoanModel.getThumpImg());
            mProductIcon.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片出错", "url=" + mLoanModel.getThumpImg() + " Exception=" + e.getMessage());
        }

        mTabIndictor.setAdapter(new TabIndictor.BaseAdapter() {
            @Override
            public String getString(int position) {
                if(mDetail == null) return "";
                else if(position == 0) return mDetail.getApply();
                else if(position == 1) return mDetail.getMater();
                else if(position == 2) return mDetail.getRepay();
                return "";
            }
        });

        mPresenter.request();
    }

    @Click(R.id.interestTotalView)
    void clickInsterestTotal() {
        if(mDetail == null) return;
        String s = "总利息=利息+服务费\n参考月利率 : "+mDetail.getRate()+"\n参考服务费率 : "+mDetail.getRateDetail();
        if(mDialog == null) {
            mDialog = AlertDialogUtils.showPrompt(this, "总利息说明", s);
        }
        mDialog.show();
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {
        if(mDetail == null) return;
    }


    /***  ILoanProductDetailView  ***/
    @Override
    public String getProductId() {
        return mLoanModel.getProductId();
    }

    @Override
    public void onRequest(LoanProductDetail detail) {
        this.mDetail = detail;
        mInterestRate.setText("月利率 : " + mDetail.getRate());
        String exp = mDetail.getExp();
        mImgText.setConts(exp.split(","));
        mLoanTip.setText("额度范围 : " + mDetail.getAmountLow() + "万-" + mDetail.getAmountHigh() + "万"
                + "  期限范围 : " + mDetail.getExpiresLow() + "个月-" + mDetail.getExpiresHigh() + "个月");
        mTabIndictor.check();
        mTime.setText(mDetail.getComplete());
    }
    /***  ILoanProductDetailView  ***/
}
