package com.qtt.jinrong.ui.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qtt.jinrong.util.DialogBuilder;
import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.event.LoginEvent;
import com.qtt.jinrong.bean.event.LoginExpired;
import com.qtt.jinrong.common.share.ShareUtil;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.ui.activity.credit.CreditApplyListActivity;
import com.qtt.jinrong.ui.activity.loan.LoanApplyListActivity;
import com.qtt.jinrong.ui.activity.user.AboutUsActivity;
import com.qtt.jinrong.ui.activity.user.FinancingNeedsActivity;
import com.qtt.jinrong.ui.activity.user.LoginActivity;
import com.qtt.jinrong.ui.activity.user.SetupActivity;
import com.qtt.jinrong.ui.activity.user.VipActivity;
import com.qtt.jinrong.ui.fragment.common.BaseFragment;
import com.qtt.jinrong.ui.widget.dialog.AlertDialogUtils;
import com.qtt.jinrong.util.SystemUtil;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.util.UserInfoUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {

    View mView;

    @ViewById(R.id.notLogin)
    View notLoginText;
    @ViewById(R.id.nickname)
    TextView nicknameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_mine, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @AfterViews
    public void initView() {
        if(isInit) return;
        super.initView();
        setUpView();
    }

    private void setUpView() {
        if(mUserInfo != null) {
            notLoginText.setVisibility(View.GONE);
            nicknameText.setVisibility(View.VISIBLE);
            nicknameText.setText(mUserInfo.getUsername());
        } else {
            notLoginText.setVisibility(View.VISIBLE);
            nicknameText.setVisibility(View.GONE);
        }
    }

    @Click(R.id.btnLogin)
    void clickLogin() {
        if(mUserInfo != null) {
            Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
            startActivity(intent);
        } else {
            login();
        }
    }

    @Click(R.id.btnSetUp)
    void clickSetUp() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(SetupActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanApply)
    void clickMyLoanApply() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(LoanApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnCreditApply)
    void clickMyCreditApply() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(CreditApplyListActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnLoanMaterial)
    void clickLoanMaterial() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(FinancingNeedsActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnMember)
    void clickbtnMember() {
        if(mUserInfo == null) {
            login();
            return;
        }
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(VipActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnAboutUs)
    void clickAboutUs() {
        Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(AboutUsActivity.class));
        startActivity(intent);
    }

    @Click(R.id.btnCall)
    void clickCall() {
        AlertDialogUtils.showdDoublelickPrompt(getActivity(), Constants.CUSTOMER_SERVICE_PHONE, "呼叫", "取消", new AlertDialogUtils.ImplAlertDialog() {
            @Override
            public void Confirm(Object object) {
                SystemUtil.callPhone(getActivity(), Constants.CUSTOMER_SERVICE_PHONE.replaceAll("-", ""));
            }
        });
    }

    @Click(R.id.btnShare)
    void clickbtnShare() {
//        UMImage image = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
//        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ)
//                .withText("来自友盟分享面板")
//                .withMedia(image)
//                .setCallback(umShareListener)
//                .open();
        showHouseShareDialog();
        //shareByWeChat(mShareCallBackListener);
        //shareByWeChatCircle(mShareCallBackListener);
    }

    public void onEventMainThread(LoginEvent event) {
        mUserInfo = UserInfoUtil.getUserInfo();
        setUpView();
    }

    public void onEventMainThread(LoginExpired event) {
        mUserInfo = null;
        setUpView();
    }


    /**
     * show share dialog
     */
    private void showHouseShareDialog() {
        View contentView = View.inflate(getActivity(), R.layout.house_detail_share_board_layout2, null);
        final AlertDialog shareAlertDialog = DialogBuilder.getAlertDialog(getActivity()).setTitle("分享钱太太给好友").setView(contentView).show();
        shareAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        contentView.findViewById(R.id.house_share_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareByWeChat(mShareCallBackListener);
                shareAlertDialog.dismiss();
            }
        });
        contentView.findViewById(R.id.house_share_wechat_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareByWeChatCircle(mShareCallBackListener);
                shareAlertDialog.dismiss();
            }
        });
    }

    /**
     * 分享结果回调接口
     */
    private SocializeListeners.SnsPostListener mShareCallBackListener = new SocializeListeners.SnsPostListener() {
        @Override
        public void onStart() {
            if (getActivity() != null) {
                ToastUtil.show(getActivity(),getResources().getString(R.string.house_share_start));
            }
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int eCode, SocializeEntity socializeEntity) {
            if (getActivity() != null) {
                if (eCode == StatusCode.ST_CODE_SUCCESSED) {
                    ToastUtil.show(getActivity(),getResources().getString(R.string.house_share_sucess));
                } else {
                    ToastUtil.show(getActivity(),getResources().getString(R.string.house_share_fail));
                }
            }
        }
    };


    /**
     * 分享至微信
     */
    public void shareByWeChat(SocializeListeners.SnsPostListener shareCallBackListener) {
        UMImage shareImage = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
        WeiXinShareContent content = new WeiXinShareContent();
            content.setShareContent("钱太太金融");
            content.setTitle("钱太太金融超市");
            content.setTargetUrl("http://www.qttjinrong.com");
            content.setShareMedia(shareImage);

        ShareUtil.getInstance(getActivity()).shareToWeiXin(content, shareCallBackListener);
    }

    /**
     * 分享至微信朋友圈
     */
    public void shareByWeChatCircle(SocializeListeners.SnsPostListener shareCallBackListener) {
        UMImage shareImage = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
        CircleShareContent content = new CircleShareContent();
        content.setShareContent("钱太太金融");
        content.setTitle("钱太太金融超市");
        content.setTargetUrl("http://www.qttjinrong.com");
        content.setShareMedia(shareImage);

        ShareUtil.getInstance(getActivity()).shareToWeiXinCircle(content, shareCallBackListener);
    }


}
