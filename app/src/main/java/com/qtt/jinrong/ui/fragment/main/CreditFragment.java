package com.qtt.jinrong.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.credit.CreditListRequest;
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.bean.event.CreditLevelEvent;
import com.qtt.jinrong.enums.CreditLevelEnum;
import com.qtt.jinrong.presenter.ICreditListPresenter;
import com.qtt.jinrong.presenter.impl.CreditListPresenterImpl;
import com.qtt.jinrong.ui.activity.credit.CreditDetailActivity;
import com.qtt.jinrong.ui.adapter.CreditAdapter;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.filter.FilterCreditAdapter;
import com.qtt.jinrong.ui.widget.filter.FilterManager;
import com.qtt.jinrong.ui.widget.filter.FilterSelect;
import com.qtt.jinrong.ui.widget.filter.FilterView;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;
import com.qtt.jinrong.view.ICreditListView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 信用卡列表
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_credit)
public class CreditFragment extends BaseFragment implements ICreditListView{

    View mView;

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.filterView)
    FilterView mFilterView;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    CreditAdapter mCreditAdapter;
    FilterManager mFilterManager;
    ICreditListPresenter mPresenter;
    CreditListRequest request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresenter = new CreditListPresenterImpl(this);
        request = new CreditListRequest();
        request.setPageNo(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_credit, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void initView() {
        if(isInit) return;
        super.initView();

        mCommonTitleBar.hideLeft();
        mCommonTitleBar.setTitle(getString(R.string.credit_title));
        mSwipeRefreshLayout.setListView(mBottomRefreshListView);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBottomRefreshListView.onAllLoaded();
                mPresenter.request();
            }
        });

        mBottomRefreshListView.addHeaderView(new View(getActivity()));
        mCreditAdapter = new CreditAdapter(getActivity());
        mBottomRefreshListView.setAdapter(mCreditAdapter);
        mBottomRefreshListView.setOnLoadMoreListener(new BottomRefreshListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(CreditDetailActivity.class));
                intent.putExtra(CreditDetailActivity.INTENT_MODEL, mCreditAdapter.getItem(position - 1));
                startActivity(intent);
            }
        });

        //设置筛选
        mFilterManager = new FilterManager();
        FilterCreditAdapter filterAdapter = new FilterCreditAdapter(getContext(),mFilterManager);
        int[] mFilter123Selected = {0,0,0};
        filterAdapter.setData(getResources().getStringArray(R.array.credit_bank),
                getResources().getStringArray(R.array.credit_type),
                getResources().getStringArray(R.array.credit_level),
                mFilter123Selected);
        mFilterManager.setComponents(mFilterView, filterAdapter);
        mFilterView.setSelectLisenter(new FilterSelect.SelectLisenter() {

            @Override
            public void onSelect(int position, int index, String val) {
                if (position == 1) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    if (index == 0) request.setCreBank(null);
                    else request.setCreBank(val);
                } else if (position == 2) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    if (index == 0) request.setCreType(null);
                    else request.setCreType(val);
                } else if (position == 3) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    if (index == 0) request.setCreClass(null);
                    else request.setCreClass(val);
                }
                mPresenter.request();
            }
        });

        doLevelSelect((CreditLevelEnum) mBundle.getSerializable("level"));

        mPresenter.request();
    }

    private void doLevelSelect(CreditLevelEnum levelEnum) {
        if(levelEnum == null) return;
        mFilterManager.setSelect(3, levelEnum.getCode(), "");
    }

    public void onEventMainThread(CreditLevelEvent event) {
        doLevelSelect(event.getLevel());
    }

    /*** ICreditListView ***/
    @Override
    public CreditListRequest getRequest() {
        if(mSwipeRefreshLayout.isRefreshing()) request.setPageNo(1);
        else request.setPageNo(mCreditAdapter.getCount()/request.getPageSize()+1);
        return request;
    }

    @Override
    public void onRequest(List<CreditModel> list) {
        boolean isRefresh = mSwipeRefreshLayout.isRefreshing();

        if(isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mBottomRefreshListView.onLoadMoreComplete();
        }

        if(list == null) return;

        if(isRefresh) {
            mCreditAdapter.update(list);
        } else {
            mCreditAdapter.add(list);
        }

        if(list.size() < request.getPageSize()) {
            mBottomRefreshListView.onAllLoaded();
        } else {
            mBottomRefreshListView.resetAll();
        }
    }
    /*** ICreditListView ***/
}
