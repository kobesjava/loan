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

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<CreditModel> mList;
    private CreditModel mLoanModel;

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

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView  enterprise;
        TextView  type;
        RatingBar rb;
        TextView  interest;
        TextView  monthPay;

        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img);
            enterprise = (TextView) view.findViewById(R.id.loanEnterprise);
            type = (TextView) view.findViewById(R.id.loanType);
            rb = (RatingBar) view.findViewById(R.id.rb);
            interest = (TextView) view.findViewById(R.id.interest);
            monthPay = (TextView) view.findViewById(R.id.monthPay);
        }
    }

}
