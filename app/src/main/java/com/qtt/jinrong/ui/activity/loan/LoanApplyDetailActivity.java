package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanApplyModel;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.framework.util.DateUtil;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_detail)
public class LoanApplyDetailActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.trackView)
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle(getString(R.string.loan_apply_detail_title));
        mCommonTitleBar.setActivity(this);

        requestOrderStatus();
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
            ((TextView)view.findViewById(R.id.time)).setText(DateUtil.getCalendarStrBySimpleDateFormat(model.getApplyDate(), "yyyy/MM/dd HH:mm:ss"));
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
        startActivity(intent);
    }

    void  requestOrderStatus() {
        List<LoanApplyModel> list = new ArrayList<>();

        LoanApplyModel model;

        model = new LoanApplyModel();
        model.setApplyDate(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setHandleReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setApplyDate(System.currentTimeMillis());
        model.setStatus("信贷经理反馈");
        model.setHandleReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setApplyDate(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setHandleReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setApplyDate(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setHandleReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setApplyDate(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setHandleReason("订单已过期");
        list.add(model);

        setUp(list);
    }

}
