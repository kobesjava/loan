package com.qtt.jinrong.ui.activity.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.app.UpgradeModel;
import com.qtt.jinrong.common.wrap.WrapHandler;
import com.qtt.jinrong.presenter.IUpgradePresenter;
import com.qtt.jinrong.presenter.impl.UpgradePresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IUpgradeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * App升级
 *
 * @author yanxin
 */
@EActivity(R.layout.activity_upgrade)
public class UpgradeActivity extends BaseActivity implements IUpgradeView{

    public static final String INTENT_UPGRADE = "INTENT_UPGRADE";

    public static final int RESULTCODE_FAIL = 1001;
    public static final int RESULTCODE_INSTALL = 1002;

    @ViewById(R.id.parentPanel)
    View mView;
    @ViewById(R.id.title)
    TextView titleText;
    @ViewById(R.id.desc)
    TextView desc;
    @ViewById(R.id.update_btn_cancel)
    View btnCancel;
    @ViewById(R.id.update_btn_ok)
    View btnUpgrade;
    @ViewById(R.id.line)
    View line;

    private Handler mHandler = new Myandler(this);
    /** 下载APK的进度条*/
    private ProgressDialog mProgress;
    /** 在不强制状态下 选择不更新*/
    private boolean mDoNotupgrade = true;
    private UpgradeModel upgradeModel;
    private IUpgradePresenter mPresenter;

    private static class Myandler extends WrapHandler<UpgradeActivity> {

        public Myandler(UpgradeActivity activity) {
            super(activity);
        }

        @Override
        protected void handlMsg(UpgradeActivity upgradeActivity, Message msg) {

        }
    }


    @Override
    public File getExternalCacheDir() {
        return super.getExternalCacheDir();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        upgradeModel = mIntent.getParcelableExtra(INTENT_UPGRADE);
        mPresenter = new UpgradePresenterImpl(this);
    }

    @Override
    public void onDestroy() {
        hideProgressDialog();
        super.onDestroy();
    }

    @AfterViews
    void initViews() {
        String title = "发现新版本:" + upgradeModel.getVersionName();
        titleText.setText(title);

        String msg = upgradeModel.getUpdateInfo();
        if (msg != null) msg = msg.replace("\\n", "\n");
        desc.setText(msg);

        if(upgradeModel.isMandatoryUpdate()) {
            btnCancel.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            btnCancel.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.update_btn_cancel)
    void clickCancel() {
        finish();
    }

    @Click(R.id.update_btn_ok)
    void clickUpgrade() {
        mView.setVisibility(View.INVISIBLE);
        mPresenter.download();
    }

    @Override
    public boolean isEnableBackKey() {
        return upgradeModel.isMandatoryUpdate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && isOutOfBounds(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 检测是否触碰到activity外面
     *
     * @param event
     * @return true or false
     */
    private boolean isOutOfBounds(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(this).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop)) || (y > (decorView.getHeight() + slop));
    }

    /** IUpgradeView **/
    @Override
    public ProgressDialog getProgressDialog() {
        if (mProgress == null) {
            mProgress = new ProgressDialog(this);
            mProgress.setCancelable(false);
            mProgress.setCanceledOnTouchOutside(false);

            mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgress.setMessage("正在下载");
        }
        return mProgress;
    }

    @Override
    public void hideProgressDialog() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mProgress != null && mProgress.isShowing()) mProgress.dismiss();
                mProgress = null;
            }
        });
    }

    @Override
    public void showLoading() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                UpgradeActivity.super.showLoading();
            }
        });
    }

    @Override
    public void hideLoading() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                UpgradeActivity.super.hideLoading();
            }
        });
    }

    @Override
    public void downloadFail() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show(UpgradeActivity.this, "下载过程中出现错误");
                setResult(RESULTCODE_FAIL);
                finish();
            }
        });
    }

    @Override
    public void onUpgradeFail() {
        setResult(RESULTCODE_FAIL);
        finish();
    }

    @Override
    public void onUpgradeSucess() {
        setResult(RESULTCODE_INSTALL);
        finish();
    }

    @Override
    public UpgradeModel getUpgradeModel() {
        return upgradeModel;
    }
    /** IUpgradeView **/

}
