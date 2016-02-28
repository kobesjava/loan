package com.android.finance.ui.widget.filter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.util.ToastUtil;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterView extends FrameLayout implements View.OnClickListener {

    TextView mFilter1Text,mFilter2Text,mFilter3Text,mFilter4Text;

    TextView mFilter4NumText;

    BaseFilterAdapter mBaseFilterAdapter;

    private int mPosition;

    private FilterPop mFilterPop012;
    private Filter3Pop mFilterPop3;

    private int mFilter1Selected;
    private int mFilter2Selected;
    private int mFilter3Selected;


    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setAdapter(BaseFilterAdapter mBaseFilterAdapter) {
        this.mBaseFilterAdapter = mBaseFilterAdapter;
        notifyChanged();
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_layout,null);

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
    private void notifyChanged() {
        mFilter1Selected = mBaseFilterAdapter.getDefaultIndex(1);
        mFilter2Selected = mBaseFilterAdapter.getDefaultIndex(2);
        mFilter3Selected = mBaseFilterAdapter.getDefaultIndex(3);

        mFilter1Text.setText(mBaseFilterAdapter.getString(1, mFilter1Selected));
        mFilter2Text.setText(mBaseFilterAdapter.getString(2, mFilter2Selected));
        mFilter3Text.setText(mBaseFilterAdapter.getString(3, mFilter3Selected));
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

    private class FilterPop extends PopupWindow {

        ListView mListView;
        PopAdapter mPopAdapter;
        View mFootView;

        public FilterPop(Context context) {
            super(context, null);
            //setAnimationStyle(0);
            setWidth(android.view.WindowManager.LayoutParams.MATCH_PARENT);
            setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));

            final View popView = LayoutInflater.from(context).inflate(R.layout.filter_pop, null);
            setContentView(popView);

            mListView = (ListView) popView.findViewById(R.id.listview);
            mListView.setVisibility(View.VISIBLE);
            mPopAdapter = new PopAdapter();

            mFootView = LayoutInflater.from(context).inflate(R.layout.filter_pop_item_input, null);
            final EditText input = (EditText) mFootView.findViewById(R.id.input);
            mFootView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(input.toString())) {
                        ToastUtil.showShortToast("您输入金额有误");
                        return;
                    }
                    String cont = input.getText().toString();
                    try {
                        int money = Integer.parseInt(cont);

                        if(money <= 0) {
                            ToastUtil.showShortToast("金额必须大于0");
                            return;
                        }

                        String moneyStr = money+"万";
                        mFilter1Selected = -1;

                        int index = mBaseFilterAdapter.getIndex(1,moneyStr);
                        if(index >= 0) mFilter1Selected = index;
                        mFilter1Text.setText(moneyStr);
                        mFilter1Text.setTextColor(getResources().getColor(R.color.red_dark));
                        mFilter1Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
                        dismiss();
                    }catch (Exception e) {
                        ToastUtil.showShortToast("您输入金额有误");
                        e.printStackTrace();
                    }
                }
            });

            mListView.addFooterView(mFootView);
            mListView.setAdapter(mPopAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if((mPosition==1 || mPosition== 2) && position == 0) return;
                    if(position >= mBaseFilterAdapter.getCount(mPosition)) return;

                    if(mPosition == 1) {
                        mFilter1Selected = position;
                        mFilter1Text.setText(mBaseFilterAdapter.getString(mPosition,position));
                        mFilter1Text.setTextColor(getResources().getColor(R.color.red_dark));
                        mFilter1Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
                    } else if(mPosition == 2) {
                        mFilter2Selected = position;
                        mFilter2Text.setText(mBaseFilterAdapter.getString(mPosition,position));
                        mFilter2Text.setTextColor(getResources().getColor(R.color.red_dark));
                        mFilter2Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
                    } else if(mPosition == 3) {
                        mFilter3Selected = position;
                        mFilter3Text.setText(mBaseFilterAdapter.getString(mPosition,position));
                        mFilter3Text.setTextColor(getResources().getColor(R.color.red_dark));
                        mFilter3Text.setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.product_filter_focus),null);
                    }

                    mFilterPop012.dismiss();
                }
            });
        }

        void show(boolean refresh) {
            if (mPosition == 1) {
                mFootView.setVisibility(View.VISIBLE);
            } else {
                mFootView.setVisibility(View.GONE);
            }
            mPopAdapter.notifyDataSetChanged();
            showAsDropDown(FilterView.this);
        }

    }

    private class Filter3Pop extends PopupWindow {

        ListView mListView;
        PopAdapter mPopAdapter;
        TextView moneyText;
        EditText moneyInput;

        public Filter3Pop(Context context) {
            super(context, null);
            //setAnimationStyle(0);
            setWidth(android.view.WindowManager.LayoutParams.MATCH_PARENT);
            setHeight(android.view.WindowManager.LayoutParams.WRAP_CONTENT);
            setFocusable(true);
            setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));

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
            int selected = mBaseFilterAdapter.getSelected(4);
            mFilter4NumText.setText(selected+"");
            mFilter4NumText.setVisibility(selected>0?View.VISIBLE:View.GONE);
        }

        void reset() {
            moneyInput.setText("10");
            mFilter4NumText.setVisibility(View.GONE);
            mBaseFilterAdapter.reset(4);
            mPopAdapter.notifyDataSetChanged();
        }

    }

    private class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mBaseFilterAdapter==null?0:mBaseFilterAdapter.getCount(mPosition);
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
            boolean selected = false;
            if(mPosition == 1) selected = mFilter1Selected == position;
            else if(mPosition == 2) selected = mFilter2Selected == position;
            else if(mPosition == 3) selected = mFilter3Selected == position;
            convertView = mBaseFilterAdapter.getView(mPosition,position,convertView,selected);
            return convertView;
        }
    }

}
