package com.qtt.jinrong.ui.widget.load;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qtt.jinrong.R;


/**
 * Function: 底部自动刷新list_view
 *
 * @author ted
 */
public class BottomRefreshListView extends ListView implements OnScrollListener {

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    private static final String TAG = "BottomFreashListView";
    /**
     * mOnScrollListener:滚动监听
     */
    private OnScrollListener mOnScrollListener;

    /**
     * mFooterDividerView:底部分割线
     */
    private View mFooterDividerView;
    /**
     * mFooterView:底部view
     */
    private RelativeLayout mFooterView;
    /**
     * mProgressLayout:加载布局
     */
    private LinearLayout mProgressLayout;
    /**
     * mProgressBarLoadMore:加载框
     */
    private View mProgressBarLoadMore;
    /**
     * mLoadComplete:结束文字
     */
    private TextView mLoadComplete;
    /**
     * mLoadingText:加载中文字
     */
    private TextView mLoadingText;
    /**
     * mOnLoadMoreListener:加载监听
     */
    private OnLoadMoreListener mOnLoadMoreListener;
    /**
     * bIsLoadingMore:是否正在加载下一页
     */
    private boolean bIsLoadingMore = false;
    /**
     * bIsLoaded:全部加载完毕
     */
    private boolean bIsLoaded = false;
    /**
     * nCurrentScrollState:滚动状态
     */
    private int nCurrentScrollState;
    /**
     * oldFirstVisibleItem:
     */
    private int oldFirstVisibleItem = 0;
    /**
     * itemClickListener:item点击监听
     */
    private OnItemClickListener itemClickListener;
    private TypedArray a;
    private boolean showLine;

    private OnItemClickListener selfItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position != parent.getCount() - 1) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(parent, view, position, id);
                }
            }
        }
    };

    public BottomRefreshListView(Context context) {
        this(context, null);
    }

    public BottomRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        a = context.obtainStyledAttributes(attrs, R.styleable.BottomRefreshListView/*, defStyle, R.style.CtripBottomRefreshListView*/);
        init(context);
    }

    /**
     * 功能描述:初始化
     */
    private void init(Context context) {

        setCacheColorHint(getResources().getColor(R.color.transparent));
        setOverscrollHeader(null);
        setOverscrollFooter(null);
        setOverScrollMode(OVER_SCROLL_NEVER);

        if (a != null) {
            showLine = a.getBoolean(R.styleable.BottomRefreshListView_show_bottom_line, false);
            a.recycle();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFooterView = (RelativeLayout) inflater.inflate(R.layout.load_more_footer, this, false);
        mProgressLayout = (LinearLayout) mFooterView.findViewById(R.id.loading_more_layout);
        mProgressBarLoadMore =mFooterView.findViewById(R.id.load_more_progressBar);
        mLoadComplete = (TextView) mFooterView.findViewById(R.id.load_complete);
        mLoadingText = (TextView) mFooterView.findViewById(R.id.load_text);
        mFooterView.setClickable(false);
        if (showLine) {
            View view = new View(getContext());
            view.setBackgroundColor(getResources().getColor(R.color.color_black));
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
            mFooterDividerView = view;
            addFooterView(view, null, false);
        }
        addFooterView(mFooterView, null, false);
        setFooterDividersEnabled(false);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            setSelector(context.getResources().getDrawable(R.drawable.bg_black_a10_selector));
        }
        setCacheColorHint(Color.TRANSPARENT);
        super.setOnScrollListener(this);
        super.setOnItemClickListener(selfItemClickListener);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        if (mOnLoadMoreListener != null && !bIsLoaded) {
            if (visibleItemCount == totalItemCount) {
                mProgressBarLoadMore.setVisibility(View.GONE);
                mProgressLayout.setVisibility(View.GONE);
                return;
            }
            if (firstVisibleItem > this.oldFirstVisibleItem) {
                boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount - 4;
                if (!bIsLoadingMore && loadMore && nCurrentScrollState != SCROLL_STATE_IDLE) {
                    mProgressBarLoadMore.setVisibility(View.VISIBLE);
                    mProgressLayout.setVisibility(View.VISIBLE);
                    bIsLoadingMore = true;
                    onLoadMore();
                }
            }
            oldFirstVisibleItem = firstVisibleItem;
        } else {
            if (mProgressBarLoadMore != null) {
                mProgressBarLoadMore.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        nCurrentScrollState = scrollState;
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    /**
     * 功能描述:加载更多
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     */
    public void onLoadMore() {
        Log.d(TAG, "onLoadMore");
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    /**
     * 功能描述:状态回至
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     */
    public void resetAll() {
        bIsLoadingMore = false;
        bIsLoaded = false;
        mLoadComplete.setVisibility(View.GONE);
    }


    /**
     * 功能描述:单次查询结束
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     */
    public void onLoadMoreComplete() {
        bIsLoadingMore = false;
        mProgressBarLoadMore.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.GONE);
    }

    /**
     * 功能描述:已到最后一页
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     */
    public void onAllLoaded() {
        onLoadMoreComplete();
        bIsLoaded = true;
        mLoadComplete.setVisibility(View.VISIBLE);
    }

    /**
     * 功能描述:设置结束文字
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     *
     * @param text
     */
    public void setPromptText(String text) {
        if (mLoadComplete != null) {
            mLoadComplete.setText(text);
        }
    }

    /**
     * 功能描述:设置加载中文字
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     *
     * @param text
     */
    public void setLoadingText(String text) {
        if (mLoadingText != null) {
            mLoadingText.setText(text);
        }
    }

    public void addLoadMoreFootView() {
        addFooterView(mFooterView, null, false);
    }


    public void setDividerHeight() {
        if (mFooterDividerView != null) {
            LayoutParams lp = (LayoutParams) mFooterDividerView.getLayoutParams();
            if (lp != null) {
                lp.height = 4;
                mFooterDividerView.setLayoutParams(lp);
            }
        }
    }

    /**
     * 功能描述:隐藏分割线
     * <p/>
     * <pre>
     *     yrguo:   2012-11-22      新建
     * </pre>
     */
    public void hideFooterDivider() {
        if (mFooterDividerView != null && mFooterDividerView.isShown()) {
            // mFooterDividerView.setVisibility(View.GONE);
            removeFooterView(mFooterDividerView);
        }
    }
}