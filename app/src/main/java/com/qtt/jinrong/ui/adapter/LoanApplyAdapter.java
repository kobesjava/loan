package com.qtt.jinrong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanApplyModel;
import com.qtt.framework.util.DateUtil;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<LoanApplyModel> mList;
    private LoanApplyModel model;

    public LoanApplyAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void add(List<LoanApplyModel> loans) {
        if(loans == null || loans.size() == 0) return;
        if(mList == null) mList = loans;
        else mList.addAll(loans);
        notifyDataSetChanged();
    }

    public void update(List<LoanApplyModel> loans) {
        if(mList != null) mList.clear();
        mList = loans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public LoanApplyModel getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.loan_apply_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        model = getItem(position);

        viewHolder.nameTxt.setText(model.getName());
        viewHolder.amountTxt.setText("金额: "+model.getAomount());
        viewHolder.statusTxt.setText(model.getStatus());
        viewHolder.sourceTxt.setText(model.getSource());
        viewHolder.termTxt.setText("期限: "+model.getTerm());
        viewHolder.timeTxt.setText(DateUtil.getCalendarStrBySimpleDateFormat(model.getTime(),"yyyy/MM/dd HH:mm:ss"));

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView  nameTxt;
        TextView  amountTxt;
        TextView  statusTxt;
        TextView  termTxt;
        TextView  sourceTxt;
        TextView  timeTxt;

        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img);
            nameTxt = (TextView) view.findViewById(R.id.name);
            amountTxt = (TextView) view.findViewById(R.id.amount);
            statusTxt = (TextView) view.findViewById(R.id.status);
            sourceTxt = (TextView) view.findViewById(R.id.source);
            termTxt = (TextView) view.findViewById(R.id.term);
            timeTxt = (TextView) view.findViewById(R.id.time);
        }
    }

}
