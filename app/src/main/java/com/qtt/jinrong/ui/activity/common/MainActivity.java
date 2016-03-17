package com.qtt.jinrong.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.app.MyApplication;
import com.qtt.jinrong.bean.app.UpgradeModel;
import com.qtt.jinrong.bean.event.LoginExpired;
import com.qtt.jinrong.bean.event.TabEvent;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.presenter.IMainPresenter;
import com.qtt.jinrong.presenter.impl.MainPresenterImpl;
import com.qtt.jinrong.ui.activity.user.UpgradeActivity;
import com.qtt.jinrong.ui.fragment.main.CreditFragment;
import com.qtt.jinrong.ui.fragment.main.LoanFragment;
import com.qtt.jinrong.ui.fragment.main.MineFragment;
import com.qtt.jinrong.ui.fragment.main.RecommendFragment;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.util.UserInfoUtil;
import com.qtt.jinrong.view.IMainView;

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
        iMainPresenter.checkUpgrade();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent != null && intent.getBooleanExtra("main", false)) {
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

    public void onEventMainThread(TabEvent event) {
        if(event.getTabIndex() == 1) {
            bundle.putSerializable("type", event.getLoanTypeEnum());
        } else if(event.getTabIndex() == 2) {
            bundle.putSerializable("level", event.getCreditLevelEnum());
        }
        mTabHost.setCurrentTab(event.getTabIndex());
    }


    /************* IMainView **********/
    @Override
    public void upgrade(UpgradeModel model) {
        Intent intent = new Intent(MyApplication.getInstance(), GeneratedClassUtils.get(UpgradeActivity.class));
        intent.putExtra(UpgradeActivity.INTENT_UPGRADE,model);
        startActivity(intent);
    }
    /************* IMainView **********/

}
