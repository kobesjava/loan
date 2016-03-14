package com.qtt.jinrong.ui.activity.credit;

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
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.bean.credit.CreditProductRecommendRequest;
import com.qtt.jinrong.presenter.ICreditApplyResultPresenter;
import com.qtt.jinrong.presenter.impl.CreditApplyResultPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ICreditApplyResultView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 信用卡申请结果
 * Created by yanxin on 16/3/14.
 */
@EActivity(R.layout.activity_loan_apply_result)
public class CreditApplyResultActivity extends BaseActivity implements ICreditApplyResultView{

    public static final String INTENT_RESPONSE_MESSAGE = "INTENT_RESPONSE_MESSAGE";
    public static final String INTENT_RESPONSE_SUCCESS = "INTENT_RESPONSE_SUCCESS";

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

    ICreditApplyResultPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = mIntent.getStringExtra(INTENT_RESPONSE_MESSAGE);
        success = mIntent.getBooleanExtra(INTENT_RESPONSE_SUCCESS, true);

        mPresenter = new CreditApplyResultPresenterImpl(this);
    }

    @AfterViews
    void initViews() {
        titleBar.setTitle("申请信用卡");
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
        recommedText.setText("根据您的资质,为您推荐以下产品");

        mPresenter.requestRecommend();
    }


    /***  ICreditApplyResultView  ***/
    @Override
    public CreditProductRecommendRequest getRequest() {
        CreditProductRecommendRequest request = new CreditProductRecommendRequest();
        return request;
    }

    @Override
    public void onRequestRecommend(List<CreditModel> loans) {
        if(loans == null || loans.size() == 0) return;

        CreditModel mLoanModel;
        for(int i=0;i<loans.size();i++) {
            mLoanModel = loans.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.credit_recommend_item,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.margin_step_5);
            recomedView.addView(view,params);
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.img);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView interestTotal = (TextView) view.findViewById(R.id.interest);
            TextView monthPay = (TextView) view.findViewById(R.id.monthPay);

            try {
                Uri uri = Uri.parse(mLoanModel.getThumpImg());
                img.setImageURI(uri);
            }catch (Exception e) {
                LogUtil.d("加载图片", "URL=" + mLoanModel.getThumpImg() + " Exception=" + e.getMessage());
            }
        }

    }
    /***  ICreditApplyResultView  ***/
}
