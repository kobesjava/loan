package com.qtt.jinrong.ui.activity.loan;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.bean.loan.LoanProductRecommendRequest;
import com.qtt.jinrong.presenter.ILoanApplyResultPresenter;
import com.qtt.jinrong.presenter.impl.LoanApplyResultPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ILoanApplyResultView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by yanxin on 16/3/14.
 */
@EActivity(R.layout.activity_apply_loan_result)
public class LoanApplyResultActivity extends BaseActivity implements ILoanApplyResultView{

    public static final String INTENT_RESPONSE_MESSAGE = "INTENT_RESPONSE_MESSAGE";
    public static final String INTENT_RESPONSE_SUCCESS = "INTENT_RESPONSE_SUCCESS";
    public static final String INTENT_RESPONSE_AMOUNT = "INTENT_RESPONSE_AMOUNT";
    public static final String INTENT_RESPONSE_TERM = "INTENT_RESPONSE_TERM";

    @ViewById(R.id.titleBar)
    CommonTitleBar titleBar;
    @ViewById(R.id.result)
    TextView resultText;
    @ViewById(R.id.recommed)
    TextView recommedText;
    @ViewById(R.id.recomedView)
    LinearLayout recomedView;

    String message;
    boolean success;
    int term,amount;

    ILoanApplyResultPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = mIntent.getStringExtra(INTENT_RESPONSE_MESSAGE);
        success = mIntent.getBooleanExtra(INTENT_RESPONSE_SUCCESS, true);
        term = mIntent.getIntExtra(INTENT_RESPONSE_TERM, 0);
        amount = mIntent.getIntExtra(INTENT_RESPONSE_AMOUNT,0);

        mPresenter = new LoanApplyResultPresenterImpl(this);
    }

    @AfterViews
    void initViews() {
        titleBar.setTitle("申请贷款");
        titleBar.setRightViewVisible(View.VISIBLE, "关闭");
        titleBar.setActivity(this);
        titleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                finish();
            }
        });

        resultText.setText(message);
        if(success) {
            resultText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.apply_result_succ), null, null, null);
        } else {
            resultText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.apply_result_failure),null,null,null);
        }
        recommedText.setText("根据您的资质,为您推荐以下产品 ("+amount+"万/"+term+"个月)");

        mPresenter.requestRecommend();
    }


    /***  ILoanApplyResultView  ***/
    @Override
    public LoanProductRecommendRequest getRequest() {
        LoanProductRecommendRequest request = new LoanProductRecommendRequest();
        request.setQuota(amount);
        request.setLimi(term);
        return request;
    }

    @Override
    public void onRequestRecommend(List<LoanModel> loans) {
        if(loans == null || loans.size() == 0) return;

        LoanModel mLoanModel;
        for(int i=0;i<loans.size();i++) {
            mLoanModel = loans.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.loan_recommend_item,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.margin_step_5);
            recomedView.addView(view,params);
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.img);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView interestTotal = (TextView) view.findViewById(R.id.interest);
            TextView monthPay = (TextView) view.findViewById(R.id.monthPay);

            title.setText(mLoanModel.getTitle());
            interestTotal.setText("总利息: "+mLoanModel.getRate());
            monthPay.setText("月供: " + mLoanModel.getMoney());

            try {
                Uri uri = Uri.parse(mLoanModel.getThumpImg());
                img.setImageURI(uri);
            }catch (Exception e) {
                LogUtil.d("加载图片", "URL=" + mLoanModel.getThumpImg() + " Exception=" + e.getMessage());
            }
        }

    }
    /***  ILoanApplyResultView  ***/
}
