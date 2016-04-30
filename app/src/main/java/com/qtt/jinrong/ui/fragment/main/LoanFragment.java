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
import com.qtt.jinrong.bean.event.LoanTypeEvent;
import com.qtt.jinrong.bean.loan.LoanListRequest;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.qtt.jinrong.enums.LoanTypeEnum;
import com.qtt.jinrong.presenter.ILoanListPresenter;
import com.qtt.jinrong.presenter.impl.LoanListPresenterImpl;
import com.qtt.jinrong.ui.activity.loan.LoanProductDetailActivity;
import com.qtt.jinrong.ui.adapter.LoanAdapter;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.help.UiUtil;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.filter.FilterLoanAdapter;
import com.qtt.jinrong.ui.widget.filter.FilterManager;
import com.qtt.jinrong.ui.widget.filter.FilterSelect;
import com.qtt.jinrong.ui.widget.filter.FilterView;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;
import com.qtt.jinrong.view.ILoanListView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_loan)
public class LoanFragment extends BaseFragment implements ILoanListView {

    View mView;

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.filterView)
    FilterView mFilterView;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    LoanAdapter mLoanAdapter;
    FilterLoanAdapter mFilterAdapter;
    FilterManager mFilterManager;
    ILoanListPresenter mPresenter;
    LoanListRequest mRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresenter = new LoanListPresenterImpl(this);

        //初始化请求对象
        mRequest = new LoanListRequest();
        mRequest.setQuota(10);
        mRequest.setLimi(12);
        mRequest.setIdentity(1);
        mRequest.setGuaranteeWay(1);
        mRequest.setRepay(1);
        mRequest.setOrderNo(1);
        mRequest.setPageNo(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_loan, container, false);
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

        mTitleBar.setTitle(getString(R.string.loan_title));
        mTitleBar.hideLeft();

        mSwipeRefreshLayout.setListView(mBottomRefreshListView);
        mBottomRefreshListView.addHeaderView(new View(getActivity()));
        mLoanAdapter = new LoanAdapter(getActivity());
        mBottomRefreshListView.setAdapter(mLoanAdapter);
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
                mPresenter.request();
            }
        });

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanProductDetailActivity.class));
                intent.putExtra(LoanProductDetailActivity.INTENT_LOAN,mLoanAdapter.getItem(position-1));
                startActivity(intent);
            }
        });

        //设置筛选
        mFilterManager = new FilterManager();
        mFilterAdapter = new FilterLoanAdapter(getContext(),mFilterManager);
        List<String[]> list = new ArrayList<>();
        list.add(getResources().getStringArray(R.array.filter41));
        list.add(getResources().getStringArray(R.array.filter42));
        list.add(getResources().getStringArray(R.array.filter43));
        int[] mFilter123Selected = {4, 4, 0};
        int[] mFilter4Selected = {0, 0, 0};
        mFilterAdapter.setData(getResources().getStringArray(R.array.filter1),
                getResources().getStringArray(R.array.filter2),
                getResources().getStringArray(R.array.filter3),
                list, getResources().getStringArray(R.array.filter4Title),
                mFilter123Selected, mFilter4Selected);
        mFilterManager.setComponents(mFilterView, mFilterAdapter);
        mFilterView.setSelectLisenter(new FilterSelect.SelectLisenter() {

            @Override
            public void onSelect(int position, int index, String val) {
                mLoanAdapter.update(null);
                if (position == 1) {
                    mRequest.setQuota(Integer.parseInt(val.replace("万", "")));
                } else if (position == 2) {
                    mRequest.setLimi(Integer.parseInt(val.replace("个月", "")));
                } else if (position == 3) {
                    mRequest.setOrderNo(index + 1);
                } else if (position == 4) {
                    int mIndex[] = mFilterAdapter.getSelected();
                    mRequest.setIdentity(mIndex[0] + 1);
                    mRequest.setGuaranteeWay(mIndex[1] + 1);
                    mRequest.setRepay(mIndex[2] + 1);
                }
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.request();
            }
        });

        doSelectType((LoanTypeEnum) mBundle.getSerializable("type"));
    }

    private void doSelectType(LoanTypeEnum loanTypeEnum) {
        if(loanTypeEnum == null) {
            mFilterManager.setSelect(4,0,"");
        } else if(loanTypeEnum.equals(LoanTypeEnum.企业贷款)) {
            mFilterManager.setSelect(4,11,"");
        } else if(loanTypeEnum.equals(LoanTypeEnum.上班族贷款)) {
            mFilterManager.setSelect(4,13,"");
        } else if(loanTypeEnum.equals(LoanTypeEnum.信用贷款)) {
            mFilterManager.setSelect(4,21,"");
        }
    }

    public void onEventMainThread(LoanTypeEvent event) {
        LoanTypeEnum loanTypeEnum = event.getType();
        doSelectType(loanTypeEnum);
    }

    /********/
    @Override
    public LoanListRequest getRequest() {
        if(mSwipeRefreshLayout.isRefreshing()) mRequest.setPageNo(1);
        else mRequest.setPageNo(mLoanAdapter.getCount()/mRequest.getPageSize()+1);
        return mRequest;
    }

    @Override
    public void onRequest(List<LoanModel> list) {
        boolean isRefresh = mSwipeRefreshLayout.isRefreshing();

        if(isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mBottomRefreshListView.onLoadMoreComplete();
        }

        if(list == null) return;

        if(isRefresh) {
            mLoanAdapter.update(list);
        } else {
            mLoanAdapter.add(list);
        }

        //计算总利息和月供
        LoanModel model;
        for(int i=0;i<list.size();i++) {
            model = list.get(i);
            float monthRate = UiUtil.getMonthRate(model.monthRate);
            int totalRate = UiUtil.calculateRate(model.compound,monthRate,mRequest.getLimi(),mRequest.getQuota()*10000);
            totalRate += model.monthManageFee*mRequest.getLimi()+model.onceManageFee;
            model.setRate(totalRate+"元");
            model.setMoney((totalRate+mRequest.getQuota()*10000)/mRequest.getLimi()+"元");
        }

        if(list.size() < mRequest.getPageSize()) {
            mBottomRefreshListView.onAllLoaded();
        } else {
            mBottomRefreshListView.resetAll();
        }
    }
}
