package com.qtt.jinrong.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.loan.LoanModel;

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

        viewHolder.title.setText(mLoanModel.getTitle());
        viewHolder.company.setText(mLoanModel.getOwnedCompany());
        viewHolder.interestTotal.setText("总利息 ："+mLoanModel.getRate()+"元");
        viewHolder.monthPay.setText("月供 ：" + mLoanModel.getMoney() + "元");
        viewHolder.rb.setRating(mLoanModel.getScore());

        try {
            Uri uri = Uri.parse(mLoanModel.getThumpImg());
            viewHolder.img.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片","URL="+mLoanModel.getThumpImg()+" Exception="+e.getMessage());
        }

        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView img;
        TextView  title;
        TextView  company;
        RatingBar rb;
        TextView  interestTotal;
        TextView  monthPay;

        public ViewHolder(View view) {
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            company = (TextView) view.findViewById(R.id.company);
            rb = (RatingBar) view.findViewById(R.id.rb);
            interestTotal = (TextView) view.findViewById(R.id.interest);
            monthPay = (TextView) view.findViewById(R.id.monthPay);
        }
    }

}
