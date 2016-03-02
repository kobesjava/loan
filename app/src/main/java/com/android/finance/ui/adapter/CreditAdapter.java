package com.android.finance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.bean.credit.CreditModel;
import com.android.finance.bean.loan.LoanModel;
import com.android.finance.enums.CreditLevelEnum;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<CreditModel> mList;
    private CreditModel model;

    public CreditAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void add(List<CreditModel> loans) {
        if(loans == null || loans.size() == 0) return;
        if(mList == null) mList = loans;
        else mList.addAll(loans);
        notifyDataSetChanged();
    }

    public void update(List<CreditModel> loans) {
        if(mList != null) mList.clear();
        mList = loans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public CreditModel getItem(int position) {
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
            convertView = mLayoutInflater.inflate(R.layout.credit_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        model = getItem(position);

        viewHolder.nameTxt.setText(model.getName());
        viewHolder.descTxt.setText(model.getDesc());
        viewHolder.levelTxt.setText(CreditLevelEnum.findLevel(model.getLevel()));
        viewHolder.cashTxt.setText("取现额度: "+model.getCashPerscent());
        viewHolder.applysTxt.setText("申请人数: "+model.getApplys());

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView  nameTxt;
        TextView  descTxt;
        TextView levelTxt;
        TextView  cashTxt;
        TextView  applysTxt;

        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img);
            nameTxt = (TextView) view.findViewById(R.id.name);
            descTxt = (TextView) view.findViewById(R.id.desc);
            levelTxt = (TextView) view.findViewById(R.id.level);
            cashTxt = (TextView) view.findViewById(R.id.cash);
            applysTxt = (TextView) view.findViewById(R.id.applyNum);
        }
    }

}
