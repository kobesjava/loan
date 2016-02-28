package com.android.finance.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.finance.R;
import com.android.finance.bean.event.LoanTypeEvent;
import com.android.finance.bean.loan.LoanModel;
import com.android.finance.ui.activity.product.ProductDetailActivity;
import com.android.finance.ui.adapter.LoanAdapter;
import com.android.finance.ui.fragment.common.BaseFragment;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.filter.FilterAdapter;
import com.android.finance.ui.widget.filter.FilterManager;
import com.android.finance.ui.widget.filter.FilterView;
import com.android.finance.ui.widget.load.BottomRefreshListView;
import com.android.finance.ui.widget.load.RefreshLayout;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_loan)
public class LoanFragment extends BaseFragment {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.filterView)
    FilterView mFilterView;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    LoanAdapter mLoanAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.loan_title));
        mTitleBar.hideLeft();

        mSwipeRefreshLayout.setListView(mBottomRefreshListView);

        mBottomRefreshListView.addHeaderView(new View(getActivity()));
        mLoanAdapter = new LoanAdapter(getActivity());
        mBottomRefreshListView.setAdapter(mLoanAdapter);

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(ProductDetailActivity.class));
                startActivity(intent);
            }
        });

        //设置筛选
        FilterManager filterManager = new FilterManager();
        FilterAdapter filterAdapter = new FilterAdapter(getContext());

        List<String[]> list = new ArrayList<>();
        list.add(getResources().getStringArray(R.array.filter41));
        list.add(getResources().getStringArray(R.array.filter42));
        list.add(getResources().getStringArray(R.array.filter43));
        list.add(getResources().getStringArray(R.array.filter44));

        int[] mFilter123Selected = {4,4,0};
        int[] mFilter4Selected = {3,0,0,0};

        filterAdapter.setData(getResources().getStringArray(R.array.filter1),
                getResources().getStringArray(R.array.filter2),
                getResources().getStringArray(R.array.filter3),
                list, getResources().getStringArray(R.array.filter4Title),
                mFilter123Selected,mFilter4Selected);
        filterManager.setmFilterView(mFilterView, filterAdapter);

        requestLoans();
    }

    private void requestLoans() {
        List<LoanModel> lists = new ArrayList<>();

        LoanModel loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(3.5f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(2.5f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(3f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(5f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(4f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(4f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(1f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        loanModel = new LoanModel();
        loanModel.setId("123");
        loanModel.setScore(0.5f);
        loanModel.setInterest(2321);
        loanModel.setName("平安银行-新一贷");
        loanModel.setMonthPay(8765);
        loanModel.setType("平安银行");
        lists.add(loanModel);

        mLoanAdapter.add(lists);
    }

    public void onEventMainThread(LoanTypeEvent event) {
    }

}
