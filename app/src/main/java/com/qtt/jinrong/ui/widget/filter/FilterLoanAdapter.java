package com.qtt.jinrong.ui.widget.filter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.ui.widget.radio.BorderRadioAdapter;
import com.qtt.jinrong.ui.widget.radio.BorderRadioGruop;
import com.qtt.jinrong.util.ToastUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanxin on 16/2/25.
 */
public class FilterLoanAdapter extends BaseFilterAdapter {

    private FilterManager mFilterManager;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private String[] mFilter1,mFilter2,mFilter3;
    private List<String[]> mFilter4;
    private String[] mFilter4Title;

    private int[] mDefaultFilter4Selected;
    private int[] mFilter123Selected;
    private int[] mFilter4Selected;

    public FilterLoanAdapter(Context context,FilterManager filterManager) {
        this.mContext = context;
        this.mFilterManager = filterManager;
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
    protected boolean isVisible(int position) {
        return true;
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
    protected void setSelect(int position, int index) {
        //// TODO: 16/3/1 待完善
        if(position == 4) {
            if(index >= 10) {
                int mIndex = index/10-1;
                int mSubindex = index%10;
                mFilter4Selected[mIndex] = mSubindex;
            } else {
                this.mFilter4Selected = Arrays.copyOf(mDefaultFilter4Selected,mDefaultFilter4Selected.length);
            }

        }
    }

    @Override
    protected boolean isSelected(int position, int index) {
        //// TODO: 16/3/1 待完善
        if(position == 4 && index>=10) {
            int mIndex = index/10-1;
            return mFilter4Selected[mIndex]>0;
        }
        return false;
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
        return position;
    }

    @Override
    protected View getFootView(final int posion) {
        if(posion == 1) {
            View mFootView = LayoutInflater.from(mContext).inflate(R.layout.filter_pop_item_input, null);
            final EditText input = (EditText) mFootView.findViewById(R.id.input);
            mFootView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {

                int mPosition = posion;

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
                        int mFilter1Selected = -1;

                        int index = getIndex(1,moneyStr);
                        if(index >= 0) mFilter1Selected = index;
                        mFilterManager.setSelect(mPosition,mFilter1Selected,moneyStr);
                    }catch (Exception e) {
                        ToastUtil.showShortToast("您输入金额有误");
                        e.printStackTrace();
                    }
                }
            });

            return mFootView;
        }
        return null;
    }

    @Override
    protected View getHeadView(int posion) {
        return null;
    }

    @Override
    protected View getView(int position, int index, View view,int selected) {

        if(position == 4) {
           return getView4(index,view,selected);
        } else {
           return getView123(position,index,view,selected==index);
        }
    }

    private View getView123(final int position, int index, View view,boolean selected) {

        ViewHolder0 holder = null;
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.filter_pop_item, null);
            holder = new ViewHolder0(view);
            view.setTag(holder);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder0 holder = (ViewHolder0) v.getTag();
                    if((holder.position == 1 || holder.position == 2) && holder.index == 0 ) return;
                    mFilterManager.setSelect(holder.position,holder.index,"");
                }
            });
        } else {
            holder = (ViewHolder0) view.getTag();
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

        if(index == 0 || selected) {
            holder.title.setBackgroundResource(R.color.color_f7f7f7);
        } else {
            holder.title.setBackgroundResource(R.color.white);
        }

        if(selected) {
            holder.title.setTextColor(mContext.getResources().getColor(R.color.red_dark));
            holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, mContext.getResources().getDrawable(R.drawable.common_selected_mark), null);
        } else {
            holder.title.setTextColor(mContext.getResources().getColor(R.color.color_999999));
            holder.title.setCompoundDrawables(null, null, null, null);
        }

        return view;
    }

    private View getView4(final int index, View view,int selected) {
        ViewHolder holder = null;
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.filter_pop_item1, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(selected >= 10) {
            int mIndex = selected/10;
            int mSubindex = selected%10;
            mFilter4Selected[mIndex] = mSubindex;
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

    static class ViewHolder0 {
        int position,index;
        TextView title;
        public ViewHolder0(View view) {
            title = (TextView) view.findViewById(R.id.title);
        }
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
