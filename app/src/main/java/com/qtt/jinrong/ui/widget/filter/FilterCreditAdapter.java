package com.qtt.jinrong.ui.widget.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterCreditAdapter extends BaseFilterAdapter {

    private Context context;
    private FilterManager mFilterManager;
    private LayoutInflater mLayoutInflater;

    private String[] mFilter1,mFilter2,mFilter3;
    private int[] mFilter123Selected;

    public FilterCreditAdapter(Context context,FilterManager filterManager) {
        this.context = context;
        this.mFilterManager = filterManager;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(String[] mFilter1,String[] mFilter2,String[] mFilter3,int[] mFilter123Selected) {
        this.mFilter1 = mFilter1;
        this.mFilter2 = mFilter2;
        this.mFilter3 = mFilter3;
        this.mFilter123Selected = mFilter123Selected;
    }

    @Override
    protected boolean isVisible(int position) {
        if(position < 4) return true;
        return false;
    }

    @Override
    protected int getCount(int position) {
        if(position == 1) return mFilter1.length;
        if(position == 2) return mFilter2.length;
        if(position == 3) return mFilter3.length;
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
        if(index < 0) return "";
        if(position == 1) {
            return index<mFilter1.length?mFilter1[index]:"";
        } else if(position == 2) {
            return index<mFilter2.length?mFilter2[index]:"";
        } else if(position == 3) {
            return index<mFilter3.length?mFilter3[index]:"";
        }
        return "";
    }

    @Override
    protected int getSelected(int position) {
        return position;
    }

    @Override
    protected void setSelect(int position, int index) {
        //// TODO: 16/3/1 待完善
    }

    @Override
    protected View getFootView(int posion) {
        return null;
    }

    @Override
    protected View getHeadView(int posion) {
        return null;
    }

    @Override
    protected View getView(int position, int index, View view,int selected) {
        ViewHolder holder = null;

        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.filter_pop_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder holder = (ViewHolder) v.getTag();
                    mFilterManager.setSelect(holder.position, holder.index, "");
                }
            });
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.position = position;
        holder.index = index;

        String title = "";
        if(position == 1) {
            title = mFilter1[index];
        } else if(position == 2) {
            title = mFilter2[index];
        } else if(position == 3) {
            title = mFilter3[index];
        }
        holder.title.setText(title);

        boolean isSelected = index==selected;
        if(index == 0 || isSelected) {
            holder.title.setBackgroundResource(R.color.color_f7f7f7);
        } else {
            holder.title.setBackgroundResource(R.color.white);
        }

        if(isSelected) {
            holder.title.setTextColor(context.getResources().getColor(R.color.red_dark));
            holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.common_selected_mark), null);
        } else {
            holder.title.setTextColor(context.getResources().getColor(R.color.color_999999));
            holder.title.setCompoundDrawables(null, null, null, null);
        }

        return view;
    }

    static class ViewHolder {
        int position,index;
        TextView title;
        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
        }
    }

}
