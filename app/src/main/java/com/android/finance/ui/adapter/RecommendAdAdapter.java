package com.android.finance.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.android.finance.bean.recommend.AdModel;
import com.android.finance.ui.activity.web.WebViewActivity;
import com.finance.framework.util.GeneratedClassUtils;

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
		ImageView view = (ImageView)object;
		view.setImageBitmap(null);
		view.setTag(null);
		super.destroyItem(container,position,object);
	}
	
	@Override
	protected View createView() {
		ImageView imageView = new ImageView(mContext);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		imageView.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getTag() != null)  {
				Intent intent = new Intent(mContext,GeneratedClassUtils.get(WebViewActivity.class));
				intent.putExtra(WebViewActivity.PARAM_URL, v.getTag().toString());
				mContext.startActivity(intent);
			}
		}
		});
		return imageView;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = (ImageView) getView();
		final AdModel adModel = mFocusImageInfoList.get(position % mFocusImageInfoList.size());
		imageView.setTag(adModel);
		//CommonDataLoader.getInstance(mContext).startImageLoader(imageView, info.picUrl);
		((ViewPager)container).addView(imageView);
		return imageView;
	}

	

}
