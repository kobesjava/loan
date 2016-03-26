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
import com.qtt.jinrong.bean.credit.CreditModel;
import com.qtt.jinrong.enums.CreditLevelEnum;

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

        viewHolder.nameTxt.setText(model.getCreTitle());
        viewHolder.descTxt.setText(model.getCreDesc());
        viewHolder.levelTxt.setText(CreditLevelEnum.findLevel(model.getCreClass()));
        viewHolder.cashTxt.setText("取现额度: "+model.getCreQuota());
        viewHolder.applysTxt.setText("申请人数: "+model.getClick());

        try {
            Uri uri = Uri.parse(model.getThumpImg());
            viewHolder.img.setImageURI(uri);
        }catch (Exception e) {
            LogUtil.d("加载图片", "URL=" + model.getThumpImg() + " Exception=" + e.getMessage());
        }

        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView img;
        TextView  nameTxt;
        TextView  descTxt;
        TextView levelTxt;
        TextView  cashTxt;
        TextView  applysTxt;

        public ViewHolder(View view) {
            img = (SimpleDraweeView) view.findViewById(R.id.img);
            nameTxt = (TextView) view.findViewById(R.id.name);
            descTxt = (TextView) view.findViewById(R.id.desc);
            levelTxt = (TextView) view.findViewById(R.id.level);
            cashTxt = (TextView) view.findViewById(R.id.cash);
            applysTxt = (TextView) view.findViewById(R.id.applyNum);
        }
    }

}
