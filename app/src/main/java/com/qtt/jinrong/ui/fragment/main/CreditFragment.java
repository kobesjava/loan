package com.qtt.jinrong.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.bean.event.CreditLevelEvent;
import com.qtt.jinrong.enums.CreditLevelEnum;
import com.qtt.jinrong.ui.activity.credit.CreditDetailActivity;
import com.qtt.jinrong.ui.adapter.CreditAdapter;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.filter.FilterCreditAdapter;
import com.qtt.jinrong.ui.widget.filter.FilterManager;
import com.qtt.jinrong.ui.widget.filter.FilterView;
import com.qtt.jinrong.ui.widget.load.BottomRefreshListView;
import com.qtt.jinrong.ui.widget.load.RefreshLayout;
import com.qtt.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_credit)
public class CreditFragment extends BaseFragment {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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

        mBottomRefreshListView.addHeaderView(new View(getActivity()));
        mCreditAdapter = new CreditAdapter(getActivity());
        mBottomRefreshListView.setAdapter(mCreditAdapter);

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(CreditDetailActivity.class));
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

        doLevelSelect((CreditLevelEnum)mBundle.getSerializable("level"));

        requestLoans();
    }

    private void requestLoans() {
        List<CreditModel> lists = new ArrayList<>();

        CreditModel model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setApplys(2736);
        model.setCashPerscent("40%");
        model.setName("平安银行-新一贷");
        model.setLevel(7);
        model.setDesc("那顿饭你哦万佛破发物品能否门票佛网平安银行");
        lists.add(model);

        mCreditAdapter.add(lists);
    }

    private void doLevelSelect(CreditLevelEnum levelEnum) {
        if(levelEnum == null) levelEnum = CreditLevelEnum.不限;
        mFilterManager.setSelect(3, levelEnum.getCode(), "");
    }

    public void onEventMainThread(CreditLevelEvent event) {
        doLevelSelect(event.getLevel());
    }

}
