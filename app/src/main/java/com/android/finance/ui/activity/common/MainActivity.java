package com.android.finance.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.app.MyApplication;
import com.android.finance.bean.event.LoanTypeEvent;
import com.android.finance.bean.event.LoginExpired;
import com.android.finance.bean.event.LoginOutEvent;
import com.android.finance.bean.event.TabEvent;
import com.android.finance.config.Constants;
import com.android.finance.enums.CreditLevelEnum;
import com.android.finance.presenter.IMainPresenter;
import com.android.finance.presenter.impl.MainPresenterImpl;
import com.android.finance.ui.fragment.main.CreditFragment;
import com.android.finance.ui.fragment.main.LoanFragment;
import com.android.finance.ui.fragment.main.MineFragment;
import com.android.finance.ui.fragment.main.RecommendFragment;
import com.android.finance.util.ToastUtil;
import com.android.finance.util.UserInfoUtil;
import com.android.finance.view.IMainView;
import com.finance.framework.util.GeneratedClassUtils;
import com.finance.framework.util.LogUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * author yanxin
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainView {

    private final String TAG = "MainActivity";

    private final static String TAB_RECOMMEND = "tab_recommend";
    private final static String TAB_LOAN = "tab_loan";
    private final static String TAB_CREDIT = "tab_credit";
    private final static String TAB_MINE = "tab_mine";

    @ViewById(android.R.id.tabhost)
    public FragmentTabHost mTabHost;

    private long backKeyFirstTimestamp;

    private IMainPresenter iMainPresenter;

    Bundle bundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iMainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getBooleanExtra("login", false)) {
            //loginOut();
        } else if(intent != null && intent.getBooleanExtra("main", false)) {
            mTabHost.setCurrentTab(0);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @AfterViews
    public void init() {
        initEventBus();
        initView();
    }

    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setBackgroundResource(R.drawable.card_shadow_up);
        mTabHost.addTab(createSpec(TAB_RECOMMEND, getString(R.string.main_recommend), R.drawable.main_tab1_selector), GeneratedClassUtils.get(RecommendFragment.class), null);
        mTabHost.addTab(createSpec(TAB_LOAN, getString(R.string.main_loan), R.drawable.main_tab2_selector), GeneratedClassUtils.get(LoanFragment.class), bundle);
        mTabHost.addTab(createSpec(TAB_CREDIT, getString(R.string.main_credit), R.drawable.main_tab3_selector), GeneratedClassUtils.get(CreditFragment.class), bundle);
        mTabHost.addTab(createSpec(TAB_MINE, getString(R.string.main_mine), R.drawable.main_tab4_selector), GeneratedClassUtils.get(MineFragment.class), null);
    }

    /**
     * TabHost lable显示
     *
     * @param tag
     * @param label
     * @param iconRes
     * @return
     */
    private TabSpec createSpec(String tag, String label, int iconRes) {
        View spec = View.inflate(this, R.layout.main_tab_item, null);
        TextView title = (TextView) spec.findViewById(R.id.title);
        title.setText(label);
        ImageView title_icon = (ImageView) spec.findViewById(R.id.title_icon);
        title_icon.setBackgroundResource(iconRes);
        return mTabHost.newTabSpec(tag).setIndicator(spec);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if(mTabHost.getCurrentTab() != 0) {
                    mTabHost.setCurrentTab(0);
                    backKeyFirstTimestamp = 0;
                    return true;
                }
                long timeDuration = System.currentTimeMillis() - backKeyFirstTimestamp;
                if (timeDuration <= Constants.EXIT_INTERVAL) {
                    exit();
                } else {
                    backKeyFirstTimestamp = System.currentTimeMillis();
                    ToastUtil.showShortToast("再点一次退出程序");
                }
            }
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    /**
     * 退出app
     */
    private void exit() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 退出登录
     */
    private void loginOut() {
        UserInfoUtil.cleanUserInfo(getApplicationContext());
    }

    /**
     * 登录失效
     *
     * @param event
     */
    public void onEventMainThread(LoginExpired event) {
        loginOut();
        if (mTopActivity == null) {
            LogUtil.d(TAG, "顶部没有activity");
            return;
        }
        if (mTopActivity != this) {
            Intent intent = new Intent(MyApplication.getInstance(), GeneratedClassUtils.get(MainActivity.class));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("main", true);
            mTopActivity.startActivity(intent);
        } else {
            mTabHost.setCurrentTab(0);
        }
    }

    /**
     * 退出登录 事件
     *
     * @param event
     */
    public void onEventMainThread(LoginOutEvent event) {
        loginOut();
    }

    public void onEventMainThread(TabEvent event) {
        if(event.getTabIndex() == 1) {
            bundle.putSerializable("type", event.getLoanTypeEnum());
        } else if(event.getTabIndex() == 2) {
            bundle.putSerializable("level", event.getCreditLevelEnum());
        }
        mTabHost.setCurrentTab(event.getTabIndex());
    }


    /************* IMainView **********/
    /************* IMainView **********/

}
