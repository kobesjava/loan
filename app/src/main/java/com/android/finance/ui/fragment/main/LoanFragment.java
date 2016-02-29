package com.android.finance.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.finance.R;
import com.android.finance.bean.event.LoanTypeEvent;
import com.android.finance.bean.loan.LoanModel;
import com.android.finance.enums.CreditLevelEnum;
import com.android.finance.enums.LoanTypeEnum;
import com.android.finance.ui.activity.product.ProductDetailActivity;
import com.android.finance.ui.adapter.LoanAdapter;
import com.android.finance.ui.fragment.common.BaseFragment;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.filter.FilterLoanAdapter;
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

    FilterManager mFilterManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(ProductDetailActivity.class));
                startActivity(intent);
            }
        });

        //设置筛选
        mFilterManager = new FilterManager();
        FilterLoanAdapter filterAdapter = new FilterLoanAdapter(getContext(),mFilterManager);

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

        mFilterManager.setComponents(mFilterView, filterAdapter);

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
        LoanTypeEnum loanTypeEnum = event.getType();
        if(loanTypeEnum == null) {
            mFilterManager.reset(4);
        } else if(loanTypeEnum.equals(LoanTypeEnum.企业贷款)) {
            mFilterManager.setSelect(4,0,1);
        } else if(loanTypeEnum.equals(LoanTypeEnum.上班族贷款)) {
            mFilterManager.setSelect(4,0,3);
        } else if(loanTypeEnum.equals(LoanTypeEnum.信用贷款)) {
            mFilterManager.setSelect(4,1,1);
        }
    }

}
