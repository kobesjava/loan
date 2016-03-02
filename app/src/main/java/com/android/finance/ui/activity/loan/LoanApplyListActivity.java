package com.android.finance.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.finance.R;
import com.android.finance.bean.loan.LoanApplyModel;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.adapter.LoanApplyAdapter;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.load.BottomRefreshListView;
import com.android.finance.ui.widget.load.RefreshLayout;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 我的贷款申请列表
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_list)
public class LoanApplyListActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    LoanApplyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle(getString(R.string.loan_apply_title));
        mCommonTitleBar.setActivity(this);

        mSwipeRefreshLayout.setListView(mBottomRefreshListView);

        mBottomRefreshListView.addHeaderView(new View(this));
        mAdapter = new LoanApplyAdapter(this);
        mBottomRefreshListView.setAdapter(mAdapter);

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LoanApplyListActivity.this, GeneratedClassUtils.get(LoanApplyDetailActivity.class));
                startActivity(intent);
            }
        });

        requestApplys();
    }

    void requestApplys() {
        List<LoanApplyModel> lists = new ArrayList<>();

        LoanApplyModel model = new LoanApplyModel();
        model.setId("123");
        model.setScore(3.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(2.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(3f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(4f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(4f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(1f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new LoanApplyModel();
        model.setId("123");
        model.setScore(0.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        model.setTerm("12个月");
        model.setSource("自申请");
        model.setTime(System.currentTimeMillis());
        model.setStatus("信用经理无反馈");
        lists.add(model);

        mAdapter.add(lists);
    }

}
