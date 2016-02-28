package com.android.finance.ui.widget.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.ui.widget.radio.BorderRadioAdapter;
import com.android.finance.ui.widget.radio.BorderRadioGruop;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterAdapter extends BaseFilterAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;

    private String[] mFilter1,mFilter2,mFilter3;
    private List<String[]> mFilter4;
    private String[] mFilter4Title;

    private int[] mDefaultFilter4Selected;
    private int[] mFilter123Selected;
    private int[] mFilter4Selected;

    public FilterAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(String[] mFilter1,String[] mFilter2,String[] mFilter3,List<String[]> mFilter4,String[] mFilter4Title,
                        int[] mFilter123Selected,int[] mFilter4Selected) {
        this.mFilter1 = mFilter1;
        this.mFilter2 = mFilter2;
        this.mFilter3 = mFilter3;
        this.mFilter4 = mFilter4;
        this.mFilter4Title = mFilter4Title;
        this.mFilter123Selected = mFilter123Selected;
        this.mDefaultFilter4Selected = mFilter4Selected;
        this.mFilter4Selected = Arrays.copyOf(mDefaultFilter4Selected, mDefaultFilter4Selected.length);
    }

    @Override
    protected int getCount(int position) {
        if(position == 1) return mFilter1.length;
        if(position == 2) return mFilter2.length;
        if(position == 3) return mFilter3.length;
        if(position == 4) return mFilter4.size();
        return 0;
    }

    @Override
    protected int getIndex(int position, String cont) {
        if(position == 1) {
            for(int i=0;i<mFilter1.length;i++) {
                if(cont.equals(mFilter1[i])) return i;
            }
        } else if(position == 2) {
            for(int i=0;i<mFilter2.length;i++) {
                if(cont.equals(mFilter2[i])) return i;
            }
        } else if(position == 3) {
            for(int i=0;i<mFilter3.length;i++) {
                if(cont.equals(mFilter3[i])) return i;
            }
        }

        return -1;
    }

    @Override
    protected int getDefaultIndex(int position) {
        if(position<=mFilter123Selected.length) {
            return mFilter123Selected[position-1];
        }
        return -1;
    }

    @Override
    protected String getString(int position, int index) {
        if(position == 1) {
            return mFilter1[index];
        } else if(position == 2) {
            return mFilter2[index];
        } else if(position == 3) {
            return mFilter3[index];
        }

        return "";
    }

    @Override
    protected void reset(int position) {
        if(position == 4) {
            this.mFilter4Selected = Arrays.copyOf(mDefaultFilter4Selected,mDefaultFilter4Selected.length);
        }
    }

    @Override
    protected int getSelected(int position) {
        if(position == 4) {
            int selected = 0;
            for(int i=0;i<mFilter4Selected.length;i++) {
                if(mFilter4Selected[i] > 0) selected++;
            }
            return selected;
        }
        return 1;
    }

    @Override
    protected View getView(int position, int index, View view,boolean selected) {

        if(position == 4) {
           return getView4(index,view);
        } else {
           return getView123(position,index,view,selected);
        }
    }

    private View getView123(int position, int index, View view,boolean selected) {

        if(view == null) view = mLayoutInflater.inflate(R.layout.filter_pop_item, null);

        String title = "";
        if(position == 1) {
            title = mFilter1[index];
        } else if(position == 2) {
            title = mFilter2[index];
        } else if(position == 3) {
            title = mFilter3[index];
        }
        ((TextView) view.findViewById(R.id.title)).setText(title);

        if(index == 0 || selected) {
            view.findViewById(R.id.title).setBackgroundResource(R.color.color_f7f7f7);
        } else {
            view.findViewById(R.id.title).setBackgroundResource(R.color.white);
        }

        if(selected) {
            ((TextView) view.findViewById(R.id.title)).setTextColor(context.getResources().getColor(R.color.red_dark));
            ((TextView) view.findViewById(R.id.title)).setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.common_selected_mark), null);
        } else {
            ((TextView) view.findViewById(R.id.title)).setTextColor(context.getResources().getColor(R.color.color_999999));
            ((TextView) view.findViewById(R.id.title)).setCompoundDrawables(null, null, null, null);
        }

        return view;
    }

    private View getView4(final int index, View view) {
        ViewHolder holder = null;
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.filter_pop_item1, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(mFilter4Title[index] + ": ");
        holder.group.setAdapter(new BorderRadioAdapter() {

            final int mIndex = index;

            @Override
            public String getItemData(int position) {
                return mFilter4.get(mIndex)[position];
            }

            @Override
            public int getRowMaxCount() {
                return 3;
            }

            @Override
            public int getCount() {
                return mFilter4.get(mIndex).length;
            }
        });

        final ViewHolder mViewHolder = holder;
        holder.group.setOnCheckedChangeListener(new BorderRadioGruop.OnCheckedChangeListener() {

            final ViewHolder mHolder = mViewHolder;
            final int mIndex = index;

            @Override
            public void onCheckedChanged(BorderRadioGruop group, int checkedId, String checkedStr, int checkedPosition) {
                mFilter4Selected[mIndex] = checkedPosition;
                mHolder.val.setText(mFilter4.get(mIndex)[checkedPosition]);
            }
        });

        holder.group.checkPosition(mFilter4Selected[index]);

        holder.val.setText(mFilter4.get(index)[mFilter4Selected[index]]);

        return view;
    }

    static class ViewHolder {
        TextView title,val;
        BorderRadioGruop group;
        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            val = (TextView) view.findViewById(R.id.val);
            group = (BorderRadioGruop) view.findViewById(R.id.group);
        }
    }

}
