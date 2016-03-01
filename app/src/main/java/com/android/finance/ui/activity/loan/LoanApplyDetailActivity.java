package com.android.finance.ui.activity.loan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.bean.loan.LoanApplyModel;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.finance.framework.util.DateUtil;

import org.androidannotations.annotations.AfterViews;
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

            ((TextView)view.findViewById(R.id.status)).setText(model.getStatus());
            ((TextView)view.findViewById(R.id.time)).setText(DateUtil.getCalendarStrBySimpleDateFormat(model.getTime(), "yyyy/MM/dd HH:mm:ss"));
            ((TextView)view.findViewById(R.id.reason)).setText(model.getReason());

            final TextView reasonView = (TextView)view.findViewById(R.id.reason);

            if(!TextUtils.isEmpty(model.getReason())) {
                reasonView.setText(model.getReason());
                view.findViewById(R.id.statusView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            mLinearLayout.addView(view);
        }
    }

    void  requestOrderStatus() {
        List<LoanApplyModel> list = new ArrayList<>();

        LoanApplyModel model;

        model = new LoanApplyModel();
        model.setTime(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setTime(System.currentTimeMillis());
        model.setStatus("信贷经理反馈");
        model.setReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setTime(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setTime(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setReason("订单已过期");
        list.add(model);

        model = new LoanApplyModel();
        model.setTime(System.currentTimeMillis());
        model.setStatus("拒绝接单");
        model.setReason("订单已过期");
        list.add(model);

        setUp(list);
    }

}
