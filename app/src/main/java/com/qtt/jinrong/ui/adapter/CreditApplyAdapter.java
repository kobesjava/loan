package com.qtt.jinrong.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.credit.CreditApplyModel;
import com.qtt.jinrong.enums.ApplyStatusEnum;

import java.util.List;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditApplyAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<CreditApplyModel> mList;
    private CreditApplyModel model;

    public CreditApplyAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void add(List<CreditApplyModel> loans) {
        if(loans == null || loans.size() == 0) return;
        if(mList == null) mList = loans;
        else mList.addAll(loans);
        notifyDataSetChanged();
    }

    public void update(List<CreditApplyModel> loans) {
        if(mList != null) mList.clear();
        mList = loans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public CreditApplyModel getItem(int position) {
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
            convertView = mLayoutInflater.inflate(R.layout.credit_apply_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        model = getItem(position);

        viewHolder.nameTxt.setText(model.getCreditName());
        viewHolder.bankTxt.setText(model.getBank());

        ApplyStatusEnum applyStatusEnum = ApplyStatusEnum.find(model.getState());
        viewHolder.statusTxt.setText(applyStatusEnum==null?"":applyStatusEnum.name());
        viewHolder.quotaTxt.setText("取现额度: "+model.getQuota());
        viewHolder.typeTxt.setText(model.getType());
        viewHolder.timeTxt.setText(model.getApplyDate());

        try {
            Uri uri = Uri.parse(model.getThumpImg());
            viewHolder.img.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片出错", "url=" + model.getThumpImg() + " Exception=" + e.getMessage());
        }

        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView img;
        TextView  nameTxt;
        TextView  bankTxt;
        TextView  statusTxt;
        TextView  typeTxt;
        TextView quotaTxt;
        TextView  timeTxt;

        public ViewHolder(View view) {
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            nameTxt = (TextView) view.findViewById(R.id.name);
            bankTxt = (TextView) view.findViewById(R.id.bank);
            statusTxt = (TextView) view.findViewById(R.id.status);
            quotaTxt = (TextView) view.findViewById(R.id.quota);
            typeTxt = (TextView) view.findViewById(R.id.type);
            timeTxt = (TextView) view.findViewById(R.id.time);
        }
    }

}
