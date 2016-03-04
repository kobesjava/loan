package com.qtt.jinrong.ui.widget.filter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterView extends FrameLayout implements FilterSelect,View.OnClickListener {

    View mFilter1,mFilter2,mFilter3,mFilter4;
    TextView mFilter1Text,mFilter2Text,mFilter3Text,mFilter4Text;

    TextView mFilter4NumText;

    BaseFilterAdapter mAdapter;

    private int mPosition;

    private FilterPop mFilterPop012;
    private Filter3Pop mFilterPop3;

    private int mFilter1Selected;
    private int mFilter2Selected;
    private int mFilter3Selected;

    /** 一共显示几个筛选项*/
    private int mShowItemNum;


    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public void setAdapter(BaseFilterAdapter mAdapter) {
        this.mAdapter = mAdapter;
        initFilterItem();
    }

    @Override
    public void setSelect(int position, int index, String defaultStr) {
        if(mFilterPop012 != null) mFilterPop012.dismiss();
        if(mFilterPop3 != null) mFilterPop3.dismiss();

        String title = mAdapter.getString(position,index);
        if(TextUtils.isEmpty(title)) title = defaultStr;
        if(position == 1) {
            mFilter1Selected = index;
            mFilter1Text.setText(title);
            mFilter1Text.setTextColor(getResources().getColor(R.color.red_dark));
            mFilter1Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
        } else if(position == 2) {
            mFilter2Selected = index;
            mFilter2Text.setText(title);
            mFilter2Text.setTextColor(getResources().getColor(R.color.red_dark));
            mFilter2Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
        } else if(position == 3) {
            mFilter3Selected = index;
            mFilter3Text.setText(title);
            mFilter3Text.setTextColor(getResources().getColor(R.color.red_dark));
            mFilter3Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
        } else if(position == 4) {
            mAdapter.setSelect(position,index);
            int selected = mAdapter.getSelected(4);
            mFilter4NumText.setText(selected+"");
            mFilter4NumText.setVisibility(selected>0?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void reset(int position) {
        //// TODO: 16/3/1 待完善
        if(mFilterPop3 != null) mFilterPop3.reset();
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_layout,null);

        mFilter1 = view.findViewById(R.id.filter1View);
        mFilter2 = view.findViewById(R.id.filter2View);
        mFilter3 = view.findViewById(R.id.filter3View);
        mFilter4 = view.findViewById(R.id.filter4View);

        mFilter1Text = (TextView) view.findViewById(R.id.filter1Text);
        mFilter2Text = (TextView) view.findViewById(R.id.filter2Text);
        mFilter3Text = (TextView) view.findViewById(R.id.filter3Text);
        mFilter4Text = (TextView) view.findViewById(R.id.filter4Text);

        mFilter4NumText = (TextView) view.findViewById(R.id.filter4NumText);

        view.findViewById(R.id.filter1View).setOnClickListener(this);
        view.findViewById(R.id.filter2View).setOnClickListener(this);
        view.findViewById(R.id.filter3View).setOnClickListener(this);
        view.findViewById(R.id.filter4View).setOnClickListener(this);

        FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                getContext().getResources().getDimensionPixelSize(R.dimen.margin_step_40));
        addView(view,params);
    }

    /**
     * 初始化item显示
     */
    private void initFilterItem() {

        if(mAdapter.isVisible(1)) {
            mShowItemNum++;
            mFilter1Selected = mAdapter.getDefaultIndex(1);
            mFilter1Text.setText(mAdapter.getString(1, mFilter1Selected));
        } else {
            mFilter1.setVisibility(View.GONE);
        }

        if(mAdapter.isVisible(2)) {
            mShowItemNum++;
            mFilter2Selected = mAdapter.getDefaultIndex(2);
            mFilter2Text.setText(mAdapter.getString(2, mFilter2Selected));
        } else {
            mFilter2.setVisibility(View.GONE);
        }

        if(mAdapter.isVisible(3)) {
            mShowItemNum++;
            mFilter3Selected = mAdapter.getDefaultIndex(3);
            mFilter3Text.setText(mAdapter.getString(3, mFilter3Selected));
        } else {
            mFilter3.setVisibility(View.GONE);
        }

        if(!mAdapter.isVisible(4)) {
            mFilter4.setVisibility(View.GONE);
        } else {
            mShowItemNum++;
        }

    }

    @Override
    public void onClick(View v) {
        int position = 0;
        if(v.getId() == R.id.filter1View) {
            position = 1;
        } else if(v.getId() == R.id.filter2View) {
            position = 2;
        } else if(v.getId() == R.id.filter3View) {
            position = 3;
        } else if(v.getId() == R.id.filter4View) {
            position = 4;
        }

        if(position != 4 && mFilterPop012 == null) {
            mFilterPop012 = new FilterPop(getContext());
        }

        if(position == 4 && mFilterPop3 == null) {
            mFilterPop3 = new Filter3Pop(getContext());
        }

        boolean refresh = position != mPosition;
        mPosition = position;
        if(position != 4) {
            mFilterPop012.show(refresh);
        } else {
            mFilterPop3.show(refresh);
        }
    }

    private class FilterPopWindow extends PopupWindow {

        public FilterPopWindow(Context context) {
            super(context, null);
            //setAnimationStyle(0);
            setWidth(android.view.WindowManager.LayoutParams.MATCH_PARENT);
            setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
        }

    }

    private class FilterPop extends FilterPopWindow {

        ListView mListView;
        PopAdapter mPopAdapter;
        View[] mFootViews;
        View mFootView;
        View[] mHeadViews;
        View mHeadView;

        public FilterPop(Context context) {
            super(context);
            final View popView = LayoutInflater.from(context).inflate(R.layout.filter_pop, null);
            setContentView(popView);

            mListView = (ListView) popView.findViewById(R.id.listview);
            mListView.setVisibility(View.VISIBLE);
            mPopAdapter = new PopAdapter();

            mFootViews = new View[mShowItemNum];
            mHeadViews = new View[mShowItemNum];
            for(int i=0;i<mFootViews.length;i++) {
                mFootViews[i] = mAdapter.getFootView(i+1);
                mHeadViews[i] = mAdapter.getHeadView(i + 1);
            }

            if(mHeadViews.length > 1 && mHeadViews[0] != null) {
                mListView.addHeaderView(mHeadViews[0]);
                mHeadView = mHeadViews[0];
            }

            if(mFootViews.length > 1 && mFootViews[0] != null) {
                mListView.addFooterView(mFootViews[0]);
                mFootView = mFootViews[0];
            }

            mListView.setAdapter(mPopAdapter);
        }

        void show(boolean refresh) {
            //检查head
            if(mHeadView != null && mHeadView != mHeadViews[mPosition-1]) {
                mListView.removeHeaderView(mHeadView);
            }
            if(mHeadViews[mPosition-1] == null) {
                mHeadView = null;
            } else if(mHeadViews[mPosition-1] != mHeadView) {
                mHeadView = mHeadViews[mPosition-1];
                mListView.addHeaderView(mHeadView);
            }

            //检查foot
            if(mFootView != null && mFootView != mFootViews[mPosition-1]) {
                mListView.removeFooterView(mFootView);
            }
            if(mFootViews[mPosition-1] == null) {
                mFootView = null;
            } else if(mFootViews[mPosition-1] != mFootView) {
                mFootView = mFootViews[mPosition-1];
                mListView.addFooterView(mFootView);
            }


            mPopAdapter.notifyDataSetChanged();
            showAsDropDown(FilterView.this);
        }

    }

    private class Filter3Pop extends FilterPopWindow {

        ListView mListView;
        PopAdapter mPopAdapter;
        TextView moneyText;
        EditText moneyInput;

        public Filter3Pop(Context context) {
            super(context);
            View popView = LayoutInflater.from(context).inflate(R.layout.filter_pop, null);
            setContentView(popView);

            View mBottom = popView.findViewById(R.id.bottom);
            mBottom.setVisibility(View.VISIBLE);
            mBottom.findViewById(R.id.btnConfirm).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirm();
                    dismiss();
                }
            });
            mBottom.findViewById(R.id.btnReset).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                }
            });

            mListView = (ListView) popView.findViewById(R.id.listview);
            mListView.setVisibility(View.VISIBLE);
            mListView.setDividerHeight(0);

            View mHeadView = LayoutInflater.from(context).inflate(R.layout.filter_pop_header, null);
            moneyText = (TextView) mHeadView.findViewById(R.id.money);
            moneyInput = (EditText) mHeadView.findViewById(R.id.editText);
            moneyInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(TextUtils.isEmpty(s.toString())) {
                        moneyText.setText("1万");
                    } else {
                        try {
                            int m = Integer.parseInt(s.toString());
                            if(m <= 0) m = 1;
                            moneyText.setText(m+"万");
                        }catch (Exception e) {
                            moneyText.setText("1万");
                        }
                    }
                }
            });
            moneyInput.setText("10");
            mListView.addHeaderView(mHeadView);

            mPopAdapter = new PopAdapter();
            mListView.setAdapter(mPopAdapter);
        }

        void show(boolean refresh) {
            mPopAdapter.notifyDataSetChanged();
            showAsDropDown(FilterView.this);
        }

        void confirm() {
            int selected = mAdapter.getSelected(4);
            mFilter4NumText.setText(selected+"");
            mFilter4NumText.setVisibility(selected>0?View.VISIBLE:View.GONE);
        }

        void reset() {
            moneyInput.setText("10");
            mFilter4NumText.setVisibility(View.GONE);
            mAdapter.reset(4);
            mPopAdapter.notifyDataSetChanged();
        }

    }

    private class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAdapter==null?0:mAdapter.getCount(mPosition);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int selected = -1;
            if(mPosition == 1) selected = mFilter1Selected;
            else if(mPosition == 2) selected = mFilter2Selected;
            else if(mPosition == 3) selected = mFilter3Selected;
            convertView = mAdapter.getView(mPosition,position,convertView,selected);
            return convertView;
        }
    }

}
