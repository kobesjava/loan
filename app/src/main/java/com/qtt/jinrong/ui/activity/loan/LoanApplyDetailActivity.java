package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanApplyModel;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.presenter.ILoanApplyDetailPresenter;
import com.qtt.jinrong.presenter.impl.LoanApplyDetailPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ILoanApplyDetailView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 贷款申请详情
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_detail)
public class LoanApplyDetailActivity extends BaseActivity implements ILoanApplyDetailView {

    public static final String INETNT_APPLY_MODEL = "INETNT_APPLY_MODEL";

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.img)
    SimpleDraweeView simpleDraweeView;
    @ViewById(R.id.name)
    TextView nameText;
    @ViewById(R.id.info)
    TextView infoText;

    @ViewById(R.id.trackView)
    LinearLayout mLinearLayout;

    LoanApplyModel loanApplyModel;
    ILoanApplyDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loanApplyModel = mIntent.getParcelableExtra(INETNT_APPLY_MODEL);
        mPresenter = new LoanApplyDetailPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle(getString(R.string.loan_apply_detail_title));
        mCommonTitleBar.setActivity(this);

        nameText.setText(loanApplyModel.getTitle());
        infoText.setText("金额: "+(loanApplyModel.getMoney()/10000)+"万元   期限:"+loanApplyModel.getExpires()+"个月");
        try {
            Uri uri = Uri.parse(loanApplyModel.getThumpImg());
            simpleDraweeView.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片", "URL=" + loanApplyModel.getThumpImg() + " Exception=" + e.getMessage());
        }

        mPresenter.request();
    }

    private void setUp(List<LoanApplyModel> list) {
        if(list == null || list.size() == 0) return;

        LoanApplyModel model;
        View view;
        for(int i=0;i<list.size();i++) {
            model = list.get(i);
            view = LayoutInflater.from(this).inflate(R.layout.loan_apply_track, null);

            if(i == 0) {
                view.findViewById(R.id.icon).setBackgroundResource(R.drawable.order_process_node_hl);
            }

            View statusView = view.findViewById(R.id.statusView);
            TextView reasonView = (TextView)view.findViewById(R.id.reason);
            ((TextView)view.findViewById(R.id.status)).setText(model.getStatus());
            ((TextView)view.findViewById(R.id.time)).setText(model.getApplyDate());
            reasonView.setText(model.getHandleReason());

            if(i==0 && !TextUtils.isEmpty(model.getHandleReason())) {
                TextView timeView = ((TextView) view.findViewById(R.id.time));
                timeView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.user_center_loan_status_down), null);
                reasonView.setText(model.getHandleReason());
                statusView.setTag(reasonView);
                statusView.setTag(R.id.time,timeView);
                statusView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view = (View) v.getTag();
                        TextView timeView = (TextView) v.getTag(R.id.time);
                        if(view.getVisibility() == View.VISIBLE) {
                            view.setVisibility(View.GONE);
                            timeView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.user_center_loan_status_down), null);
                        } else {
                            view.setVisibility(View.VISIBLE);
                            timeView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.user_center_loan_status_up), null);
                        }
                    }
                });
            }

            mLinearLayout.addView(view);
        }
    }

    @Click(R.id.product)
    void clickProductDetail() {
        Intent intent = new Intent(this, GeneratedClassUtils.get(LoanProductDetailActivity.class));
        LoanModel model = new LoanModel();
        model.setProductId(loanApplyModel.getProductId());
        model.setTitle(loanApplyModel.getTitle());
        model.setThumpImg(loanApplyModel.getThumpImg());
        intent.putExtra(LoanProductDetailActivity.INTENT_LOAN, model);
        intent.putExtra(LoanProductDetailActivity.INTENT_TOTAL,loanApplyModel.getMoney()/10000);
        intent.putExtra(LoanProductDetailActivity.INTENT_TERM,loanApplyModel.getExpires());
        startActivity(intent);
    }


    /** ILoanApplyDetailView **/
    @Override
    public String getApplyId() {
        return loanApplyModel.getId();
    }

    @Override
    public void onRequest(List<LoanApplyModel> list) {
        setUp(list);
    }
    /** ILoanApplyDetailView **/

}
