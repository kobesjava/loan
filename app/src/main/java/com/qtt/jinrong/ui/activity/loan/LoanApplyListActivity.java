package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanApplyListRequest;
import com.qtt.jinrong.bean.loan.LoanApplyModel;
import com.qtt.jinrong.presenter.ILoanApplyListPresenter;
import com.qtt.jinrong.presenter.impl.LoanApplyLIstPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.adapter.LoanApplyAdapter;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;
import com.qtt.jinrong.view.ILoanApplyListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 我的贷款申请列表
 * Created by yanxin on 16/3/1.
 */
@EActivity(R.layout.activity_loan_apply_list)
public class LoanApplyListActivity extends BaseActivity implements ILoanApplyListView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    LoanApplyAdapter mAdapter;

    LoanApplyListRequest request;
    ILoanApplyListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoanApplyLIstPresenterImpl(this);
        request = new LoanApplyListRequest();
        request.setUserId(getUserId());
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle(getString(R.string.loan_apply_title));
        mCommonTitleBar.setActivity(this);

        mSwipeRefreshLayout.setListView(mBottomRefreshListView);

        mBottomRefreshListView.addHeaderView(new View(this));
        mAdapter = new LoanApplyAdapter(this);
        mBottomRefreshListView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBottomRefreshListView.onAllLoaded();
                request.setPageNo(1);
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnLoadMoreListener(new BottomRefreshListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mSwipeRefreshLayout.setEnabled(false);
                request.setPageNo(mAdapter.getCount()/request.getPageSize()+1);
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LoanApplyListActivity.this, GeneratedClassUtils.get(LoanApplyDetailActivity.class));
                startActivity(intent);
            }
        });

        mPresenter.request();
    }

    @Override
    public LoanApplyListRequest getRequest() {
        return request;
    }

    @Override
    public void onRequest(List<LoanApplyModel> models) {
        if(request.getPageNo() == 1) {
            mSwipeRefreshLayout.setRefreshing(false);
            if(models != null) mAdapter.update(models);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
            mBottomRefreshListView.onLoadMoreComplete();
            if(models != null) mAdapter.add(models);
        }

        if(mAdapter.getCount()%request.getPageSize() != 0) {
            mBottomRefreshListView.onAllLoaded();
        } else {
            mBottomRefreshListView.resetAll();
        }
    }

    @Override
    public void onRequestFail() {
        if(request.getPageNo() == 1) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
            mBottomRefreshListView.onLoadMoreComplete();
        }
    }
}
