package com.qtt.jinrong.ui.activity.credit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.credit.CreditApplyListRequest;
import com.qtt.jinrong.bean.credit.CreditApplyModel;
import com.qtt.jinrong.presenter.ICreditApplyListPresenter;
import com.qtt.jinrong.presenter.impl.CreditApplyListPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.activity.loan.LoanApplyDetailActivity;
import com.qtt.jinrong.ui.adapter.CreditApplyAdapter;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;
import com.qtt.jinrong.view.ICreditApplyListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 我的信用卡申请列表
 * Created by yanxin on 16/3/20.
 */
@EActivity(R.layout.activity_loan_apply_list)
public class CreditApplyListActivity extends BaseActivity implements ICreditApplyListView {

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    CreditApplyAdapter mAdapter;
    CreditApplyListRequest request;
    ICreditApplyListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CreditApplyListPresenterImpl(this);
        request = new CreditApplyListRequest();
        request.setUserId(getUserId());
    }

    @AfterViews
    void initView() {
        mCommonTitleBar.setTitle("我的信用卡申请列表");
        mCommonTitleBar.setActivity(this);

        mSwipeRefreshLayout.setListView(mBottomRefreshListView);

        mBottomRefreshListView.addHeaderView(new View(this));
        mAdapter = new CreditApplyAdapter(this);
        mBottomRefreshListView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBottomRefreshListView.onAllLoaded();
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnLoadMoreListener(new BottomRefreshListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mSwipeRefreshLayout.setEnabled(false);
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CreditApplyListActivity.this, GeneratedClassUtils.get(LoanApplyDetailActivity.class));
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.request();
    }

    @Override
    public CreditApplyListRequest getRequest() {
        if(mSwipeRefreshLayout.isRefreshing()) {
            request.setPageNo(1);
        } else {
            request.setPageNo(mAdapter.getCount()/request.getPageSize()+1);
        }
        return request;
    }

    @Override
    public void onRequest(List<CreditApplyModel> models) {
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
