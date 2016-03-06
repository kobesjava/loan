package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.adapter.LoanApplyAdapter;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    }

}
