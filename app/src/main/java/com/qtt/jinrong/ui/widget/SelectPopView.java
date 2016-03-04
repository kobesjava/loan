package com.qtt.jinrong.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qtt.jinrong.R;

import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public class SelectPopView extends PopupWindow {

    private ListView mListview;
    private ItemAdapter mItemAdapter;
    private SelectCallback mSelectCallback;

    public SelectPopView(Context context) {
        super(context, null);
        //setAnimationStyle(R.style.PopupAnimation);
        setWidth(android.view.WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(context.getResources().getDrawable(R.color.color_aF000000));

        View view = LayoutInflater.from(context).inflate(R.layout.select_pop_view, null);
        mListview = (ListView) view.findViewById(R.id.listview);
        mItemAdapter = new ItemAdapter(context);
        mListview.setAdapter(mItemAdapter);

        view.findViewById(R.id.main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if(mSelectCallback != null) {
                    mSelectCallback.onItemSelect(position,mItemAdapter.getItem(position));
                }
            }
        });
        setContentView(view);
    }

    public void setData(List<String> list) {
        mItemAdapter.setData(list);
    }

    public void show(View view) {
        if(!isShowing()) {
            showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    static class ItemAdapter extends BaseAdapter {

        private LayoutInflater mLayoutInflater;
        private List<String> mList;

        public ItemAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(List<String> list) {
            this.mList = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList==null?0:mList.size();
        }

        @Override
        public String getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.select_pop_item,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(getItem(position));
            return convertView;
        }

        static class ViewHolder{
            TextView title;
            public ViewHolder(View view) {
                title = (TextView) view.findViewById(R.id.title);
            }
        }
    }


    public void setSelectCallback(SelectCallback callback) {
        this.mSelectCallback = callback;
    }
    public interface SelectCallback {
        void onItemSelect(int position,String val);
    }

}
