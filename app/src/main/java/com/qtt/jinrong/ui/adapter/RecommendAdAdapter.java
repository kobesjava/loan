package com.qtt.jinrong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.recommend.AdModel;
import com.qtt.jinrong.ui.activity.web.WebViewActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.GeneratedClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxin
 */
public class RecommendAdAdapter extends BasePagerAdapter {

	private Context mContext;

	private List<AdModel> mFocusImageInfoList = new ArrayList<AdModel>();
	
	public RecommendAdAdapter(Context context, List<AdModel> mFocusImageInfoList) {
		this.mContext = context;
		this.mFocusImageInfoList = mFocusImageInfoList;
	}
	
	public void update(List<AdModel> mFocusImageInfoList) {
		this.mFocusImageInfoList = mFocusImageInfoList;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if(mFocusImageInfoList == null ||
				mFocusImageInfoList.size() == 0) {
			return 0;
		}else if(mFocusImageInfoList.size() == 1) {
			return mFocusImageInfoList.size();
		}else {
			return Integer.MAX_VALUE;
		}
	}

	// 销毁arg1位置的界面
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		//SimpleDraweeView view = (SimpleDraweeView)object;
		//view.setImageBitmap(null);
		((View)object).setTag(null);
		super.destroyItem(container,position,object);
	}
	
	@Override
	protected View createView() {
		SimpleDraweeView draweeView = (SimpleDraweeView) LayoutInflater.from(mContext).inflate(R.layout.recommend_ad_img,null);
		draweeView.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getTag() != null)  {
				Intent intent = new Intent(mContext,GeneratedClassUtils.get(WebViewActivity.class));
				intent.putExtra(WebViewActivity.PARAM_URL, v.getTag().toString());
				mContext.startActivity(intent);
			}
		}
		});
		return draweeView;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		SimpleDraweeView draweeView = (SimpleDraweeView) getView();
		final AdModel adModel = mFocusImageInfoList.get(position % mFocusImageInfoList.size());
		draweeView.setTag(adModel);
		try {
			Uri uri = Uri.parse(adModel.getImgUrl());
			draweeView.setImageURI(uri);
		}catch (Exception e) {
			LogUtil.d("加载图片出错","url="+adModel.getImgUrl()+" Exception="+e.getMessage());
		}
		container.addView(draweeView);
		return draweeView;
	}

}
