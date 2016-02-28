package com.android.finance.ui.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.finance.R;


/**
 * 滑动 带有圆形指示图片
 * @author yanxin01
 *
 */
public class IndicatorView extends LinearLayout implements OnClickListener {
	
	private IndicatorListener listener;
	
	private LinearLayout indicatorView;
	private ViewPager pager;
	
	public boolean cycle = true;
	
	public Drawable guide_default;
	public Drawable guide_focus;
	
	private int indicatorCount;
	
	public IndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mLayoutInflater.inflate(R.layout.indicator, null);
		LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				getContext().getResources().getDimensionPixelSize(R.dimen.ad_height));
		addView(view,params);
		pager = (ViewPager) view.findViewById(R.id.pager);
		indicatorView = (LinearLayout) view.findViewById(R.id.indicator);
		
		init();
	}
	
	private void init() {
		guide_default = getResources().getDrawable(R.drawable.point_white);
		guide_focus = getResources().getDrawable(R.drawable.point_focus);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageSelected(int arg0) {
				if(indicatorCount > 0) {
					if(cycle) {
						select(arg0 % indicatorCount);
					}else {
						select(arg0);
					}
				}
			}
			
		});
		
		clearViewAll();
		
	}
	
	public ViewPager getPager() {
		return pager;
	}
	
	public void setIcon(int def,int focus) {
		if(def > 0) {
			guide_default = getResources().getDrawable(def);
		}
		if(focus > 0) {
			guide_focus = getResources().getDrawable(focus);
		}
	}
	
	/**
	 * 设置圆形导向图
	 * @param count
	 */
	public void setIndicator(int count) {
		
		indicatorView.removeAllViews();
		this.indicatorCount = count;
		
		if(count < 1 || !cycle) {
			stop();
		}
		
		ImageView imageView = null;
		for (int i = 0; i < indicatorCount; i++) {
			imageView = new ImageView(getContext());
			LayoutParams layoutParams = new LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			indicatorView.addView(imageView, layoutParams);
			if (i == 0) {
				imageView.setImageDrawable(guide_focus);
			} else {
				imageView.setImageDrawable(guide_default);
			}
		}
		
		
		/*int childCount = indicatorView.getChildCount();
		int maxCount = Math.max(count, childCount);
		ImageView imageView = null;
		for (int i = 0; i < maxCount; i++) {
			if(i <= childCount-1
					&& i<= count-1) {
				imageView = (ImageView) indicatorView.getChildAt(i);
			} else if(i <= childCount-1) {
				indicatorView.getChildAt(i).setVisibility(View.GONE);
				continue;
			} else if(i<= count-1) {
				imageView = new ImageView(getContext());
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				layoutParams.leftMargin = 5;
				layoutParams.rightMargin = 5;
				indicatorView.addView(imageView, layoutParams);
			}
			if (i == 0) {
				imageView.setImageDrawable(guide_focus);
			} else {
				imageView.setImageDrawable(guide_default);
			}
		}*/
		
		start();
	}
	
	Runnable g = new Runnable() {
		
		@Override
		public void run() {
			int index = pager.getCurrentItem();
			pager.setCurrentItem(index+1,true);
			start();
		}
	};
	
	public void select(int index) {
		if(index < 0 || index >= indicatorView.getChildCount()) {
			return;
		}
		for (int i = 0; i < indicatorView.getChildCount(); i++) {
			ImageView imageView = (ImageView) indicatorView.getChildAt(i);
			if (i == index) {
				imageView.setImageDrawable(guide_focus);
			} else {
				imageView.setImageDrawable(guide_default);
			}
		}
	}
	
	public void clearViewAll() {
		if (indicatorView != null) {
			indicatorView.removeAllViews();
		}
	}
	
	public void setAdapter(PagerAdapter adapter) {
		pager.setAdapter(adapter);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		stop();
	}
	
	/**
	 * 启动 定时器
	 */
	public void start() {
		if(cycle && indicatorCount > 1) {
			pager.removeCallbacks(g);
			pager.postDelayed(g, 5000L);
		}
	}
	
	/**
	 * 关闭定时器
	 */
	public void stop() {
		pager.removeCallbacks(g);
	}
	
	@Override
	public void onClick(View v) {

	}

	public void setIndicatorListener(IndicatorListener listener) {
		this.listener = listener;
	}
	
	public interface IndicatorListener {
		public void close();
	}
	
}
