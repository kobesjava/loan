package com.android.finance.ui.widget.filter;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterManager {

    private FilterView mFilterView;

    private BaseFilterAdapter mBaseFilterAdapter;

    public BaseFilterAdapter getmBaseFilterAdapter() {
        return mBaseFilterAdapter;
    }

    public void setmBaseFilterAdapter(BaseFilterAdapter mBaseFilterAdapter) {
        this.mBaseFilterAdapter = mBaseFilterAdapter;
    }

    public FilterView getmFilterView() {
        return mFilterView;
    }

    public void setmFilterView(FilterView mFilterView,BaseFilterAdapter mBaseFilterAdapter) {
        this.mFilterView = mFilterView;
        this.mBaseFilterAdapter = mBaseFilterAdapter;
        mFilterView.setAdapter(mBaseFilterAdapter);
    }

}
