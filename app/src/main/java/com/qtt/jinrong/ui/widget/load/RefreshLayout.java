package com.qtt.jinrong.ui.widget.load;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by yanxin on 16/2/24.
 */
public class RefreshLayout extends SwipeRefreshLayout {

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListView(final ListView listView) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    if (listView.getChildAt(0) != null) {
                        if (listView.getChildAt(0).getTop() == 0) {
                            setEnabled(true);
                        } else {
                            setEnabled(false);
                        }
                    }
                } else {
                    setEnabled(false);
                }
            }
        });
    }

}
