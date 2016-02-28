package com.android.finance.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.finance.R;


/**
 * 公共的头部 view
 * Created by yanxin on 2015/6/23.
 */
public class CommonTitleBar extends LinearLayout implements OnClickListener {


    private View custom_title_panel;
    private TextView title_text;
    private View leftButton;
    private TextView titleRight;

    private TitleBarListener titleBarListener;

    private Activity mActivity;

    public CommonTitleBar(Context context) {
        super(context);
        init(context);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        mActivity = null;
        super.onDetachedFromWindow();
    }


    /**
     * 设置iconfont 的按钮颜色 和按钮标识符
     *
     * @param iconfont
     * @param color
     */
    public void setLeftIconfontAndColor(String iconfont, int color) {
        //leftButton.setTextColor(color);
        //leftButton.setText(iconfont);
    }

    /**
     * 隐藏左边按钮
     */
    public void hideLeft() {
        leftButton.setVisibility(INVISIBLE);
    }

    public void setIconLeft(String leftTitle) {
        /*leftButton.setTypeface(null);
        leftButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_up_home), null, null, null);
        leftButton.setText(leftTitle);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) leftButton.getLayoutParams();
        layoutParams.height = LayoutParams.MATCH_PARENT;
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        leftButton.setLayoutParams(layoutParams);
        leftButton.setGravity(Gravity.CENTER_VERTICAL);*/
    }

    public void setIconLeft() {
        setIconLeft(getResources().getString(R.string.app_name));
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_title_bar, null);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                getContext().getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        addView(view, params);

        custom_title_panel = view.findViewById(R.id.title_bar);
        title_text = (TextView) view.findViewById(R.id.title_text);
        leftButton = view.findViewById(R.id.btn_back);
        titleRight = (TextView) view.findViewById(R.id.title_right);

        if (isInEditMode()) return;
        leftButton.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    public void setLeftBtnVisible(int visibility) {
        leftButton.setVisibility(visibility);
    }

    public void setRightViewVisible(int visibility, String rightContent) {
        titleRight.setVisibility(visibility);
        titleRight.setText(rightContent);
    }

    public void setRightTextColor(int mColor) {
        titleRight.setTextColor(mColor);
    }

    public void setLeftTextColor(int mColor) {
        //leftButton.setTextColor(mColor);
    }

    public void setTitle(CharSequence left) {
        title_text.setText(left);
    }

    /**
     * 设置背景色值
     *
     * @param resource
     */
    public void setBackground(int resource) {
        custom_title_panel.setBackgroundResource(resource);
    }

    /**
     * 切换主题
     *
     * @param theme
     */
    public void setTheme(THEME theme) {
        if (theme.equals(THEME.THEME_WHITE)) {
            setBackground(R.color.color_ffffff);
            setRightTextColor(getResources().getColor(R.color.color_0090ff));
            setLeftTextColor(getResources().getColor(R.color.color_747e89));
            title_text.setTextColor(getResources().getColor(R.color.color_666666));
        }
    }

    public interface TitleBarListener {
        public void leftOnClick();

        public void rightOnClick();
    }

    public static class SimpleTitleBarListener implements TitleBarListener {
        public void leftOnClick() {
        }

        public void rightOnClick() {
        }
    }

    public void setTitleBarListener(TitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            if (titleBarListener != null) {
                titleBarListener.leftOnClick();
            }
            if (mActivity != null) {
                mActivity.finish();
            }
        } else if (v.getId() == R.id.title_right) {
            if (titleBarListener != null) {
                titleBarListener.rightOnClick();
            }
        }
    }

    public static enum THEME {
        THEME_COMMON(),
        THEME_WHITE();
    }

}
