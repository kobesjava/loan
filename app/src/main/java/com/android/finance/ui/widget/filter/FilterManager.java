package com.android.finance.ui.widget.filter;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterManager {

    private FilterSelect mFilterView;

    private BaseFilterAdapter mBaseFilterAdapter;

    public BaseFilterAdapter getmBaseFilterAdapter() {
        return mBaseFilterAdapter;
    }

    public void setAdapter(BaseFilterAdapter mBaseFilterAdapter) {
        this.mBaseFilterAdapter = mBaseFilterAdapter;
    }

    public FilterSelect getmFilterView() {
        return mFilterView;
    }

    public void setComponents(FilterSelect mFilterView,BaseFilterAdapter mBaseFilterAdapter) {
        this.mFilterView = mFilterView;
        this.mBaseFilterAdapter = mBaseFilterAdapter;
        mFilterView.setAdapter(mBaseFilterAdapter);
    }

    public void setSelect(int position,int index,String defaultStr) {
        mFilterView.setSelect(position, index, defaultStr);
    }

    public void reset(int position) {
        mFilterView.reset(position);
    }

    public void setSelect(int position,int index,int subIndex) {
        this.mFilterView.setSelect(position,index,subIndex);
    }

}
