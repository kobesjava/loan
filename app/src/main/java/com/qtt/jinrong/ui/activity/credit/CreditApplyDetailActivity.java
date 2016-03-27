package com.qtt.jinrong.ui.activity.credit;

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
import com.qtt.jinrong.bean.credit.CreditApplyDetailModel;
import com.qtt.jinrong.bean.credit.CreditApplyModel;
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.enums.ApplyStatusEnum;
import com.qtt.jinrong.presenter.ICreditApplyDetailPresenter;
import com.qtt.jinrong.presenter.impl.CreditApplyDetailPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.view.ICreditApplyDetailView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 信用卡申请详情
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_detail)
public class CreditApplyDetailActivity extends BaseActivity implements ICreditApplyDetailView {

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

    CreditApplyModel creditApplyModel;
    ICreditApplyDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        creditApplyModel = mIntent.getParcelableExtra(INETNT_APPLY_MODEL);
        mPresenter = new CreditApplyDetailPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle("信用卡申请详情");
        mCommonTitleBar.setActivity(this);

        nameText.setText(creditApplyModel.getCreditName());
        infoText.setText("银行: "+creditApplyModel.getBank()+"   卡类型: "+creditApplyModel.getType());
        try {
            Uri uri = Uri.parse(creditApplyModel.getThumpImg());
            simpleDraweeView.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片", "URL=" + creditApplyModel.getThumpImg() + " Exception=" + e.getMessage());
        }

        mPresenter.request();
    }

    private void setUp(List<CreditApplyDetailModel> list) {
        if(list == null || list.size() == 0) return;

        CreditApplyDetailModel model;
        View view;
        for(int i=0;i<list.size();i++) {
            model = list.get(i);
            view = LayoutInflater.from(this).inflate(R.layout.credit_apply_track, null);

            if(i == 0) {
                view.findViewById(R.id.icon).setBackgroundResource(R.drawable.order_process_node_hl);
            }

            View statusView = view.findViewById(R.id.statusView);
            TextView reasonView = (TextView)view.findViewById(R.id.reason);

            ApplyStatusEnum applyStatusEnum = ApplyStatusEnum.find(model.getStatus());
            ((TextView)view.findViewById(R.id.status)).setText(applyStatusEnum==null?"":applyStatusEnum.name());

            ((TextView)view.findViewById(R.id.time)).setText(model.getHandleDate());
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
        Intent intent = new Intent(this, GeneratedClassUtils.get(CreditDetailActivity.class));
        CreditModel model = new CreditModel();
        model.setId(creditApplyModel.getCreId());
        model.setThumpImg(creditApplyModel.getThumpImg());
        model.setCreTitle(creditApplyModel.getCreditName());
        intent.putExtra(CreditDetailActivity.INTENT_MODEL, model);
        startActivity(intent);
    }

    /** ICreditApplyDetailView **/
    @Override
    public String getApplyId() {
        return creditApplyModel.getCreditId();
    }

    @Override
    public void onRequest(List<CreditApplyDetailModel> list) {
        setUp(list);
    }
    /** ICreditApplyDetailView **/

}
