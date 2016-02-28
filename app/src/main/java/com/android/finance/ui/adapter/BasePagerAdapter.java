package com.android.finance.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter extends PagerAdapter {

	private List<View> dirtys = new ArrayList<View>();
	private List<View> alives = new ArrayList<View>();

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 销毁arg1位置的界面
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
		alives.add((View)object);
	}
	
	protected abstract View createView();
	
	protected View getView() {
		View view = null;
		if(alives.size() > 0) {
			view = alives.remove(0);
			dirtys.add(view);
		}else {
			view = createView();
			if(view != null) {
				dirtys.add(view);
			}
		}
		return view;
	}
	
}
