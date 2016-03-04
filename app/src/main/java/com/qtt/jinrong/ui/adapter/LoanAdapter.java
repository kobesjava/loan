package com.qtt.jinrong.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<LoanModel> mList;
    private LoanModel mLoanModel;

    public LoanAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void add(List<LoanModel> loans) {
        if(loans == null || loans.size() == 0) return;
        if(mList == null) mList = loans;
        else mList.addAll(loans);
        notifyDataSetChanged();
    }

    public void update(List<LoanModel> loans) {
        if(mList != null) mList.clear();
        mList = loans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public LoanModel getItem(int position) {
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
            convertView = mLayoutInflater.inflate(R.layout.loan_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        mLoanModel = getItem(position);

        viewHolder.enterprise.setText(mLoanModel.getName());
        viewHolder.type.setText(mLoanModel.getType());
        viewHolder.interest.setText("总利息 ："+mLoanModel.getInterest()+"元");
        viewHolder.monthPay.setText("月供 ："+mLoanModel.getMonthPay()+"元");
        viewHolder.rb.setRating(mLoanModel.getScore());

        Uri uri = Uri.parse(mLoanModel.getUrl());
        viewHolder.img.setImageURI(uri);

        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView img;
        TextView  enterprise;
        TextView  type;
        RatingBar rb;
        TextView  interest;
        TextView  monthPay;

        public ViewHolder(View view) {
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            enterprise = (TextView) view.findViewById(R.id.loanEnterprise);
            type = (TextView) view.findViewById(R.id.loanType);
            rb = (RatingBar) view.findViewById(R.id.rb);
            interest = (TextView) view.findViewById(R.id.interest);
            monthPay = (TextView) view.findViewById(R.id.monthPay);
        }
    }

}
