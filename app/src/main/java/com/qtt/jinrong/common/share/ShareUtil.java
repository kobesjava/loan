package com.qtt.jinrong.common.share;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.qtt.framework.util.DeviceUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.util.BitmapUtil;
import com.qtt.jinrong.util.ToastUtil;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtil {
    private static ShareUtil sInstance;
    private Context mContext;

    /**
     * 微信的相关信息
     */
//    public static final String WEIXIN_APP_ID = "wxb4ebbcc69ee4ba81";
//    public static final String WEIXIN_APP_SECRET = "b46b888a8197aebba04a5d7c9cc33f23";
    public static final String WEIXIN_APP_ID = "wx7a714590392841c7";
    public static final String WEIXIN_APP_SECRET = "ec5e7473f526b7974fcdd266b4511eb3";
    private static final int SUPPORT_FRIEND_LINE_LEVEL = 0x21020001;
    private IWXAPI mApi;
    /**
     * 微信
     */
    private UMWXHandler wxHandler;
    /**
     * 支持微信朋友圈
     */
    private UMWXHandler wxCircleHandler;
    private UMSocialService mController;


    private static final int THUMB_SIZE = 100;


    public static class ShareType {
        public static final int WEIXIN_FRIEND = 0; // 微信
        public static final int WEIXIN_FRIEND_LINE = 1; // 微信朋友圈
        public static final int WEIBO = 2; // 新浪微博
        public static final int SMS = 3; // 短信
        public static final int EMAIL = 4; // Email
        public static final int COPY = 5; // 复制
    }

    private ShareUtil(Context context) {
        mContext = context;
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    }

    public static ShareUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ShareUtil(context);
        }
        if (!sInstance.mContext.equals(context)) {
            sInstance.mContext = context;
        }
        return sInstance;
    }


    /**
     * 是否已经安装微信
     *
     * @return boolean
     */
    public boolean isWXAppInstalled() {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        return mApi.isWXAppInstalled();
    }


    /**
     * 分享到微信
     *
     * @param content
     */
    public void shareToWeiXin(WeiXinShareContent content, SocializeListeners.SnsPostListener snsPostListener) {
        if (!isWXAppInstalled()) {
            ToastUtil.show(mContext, "您还未安装微信哦~");
            return;
        }
        wxHandler = new UMWXHandler(mContext, WEIXIN_APP_ID, WEIXIN_APP_SECRET);
        wxHandler.addToSocialSDK();
        wxHandler.showCompressToast(false);
        mController.setShareMedia(content);
        mController.getConfig().closeToast();
        mController.postShare(mContext, SHARE_MEDIA.WEIXIN, snsPostListener);
    }

    /**
     * 分享到微信
     *
     * @param content
     */
    public void shareToWeiXinCircle(CircleShareContent content, final SocializeListeners.SnsPostListener snsPostListener) {
        if (!isWXAppInstalled()) {
            ToastUtil.show(mContext, "您还未安装微信哦~");
            return;
        }
        wxCircleHandler = new UMWXHandler(mContext, WEIXIN_APP_ID, WEIXIN_APP_SECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        wxCircleHandler.showCompressToast(false);
        mController.setShareMedia(content);
        mController.getConfig().closeToast();
        mController.postShare(mContext, SHARE_MEDIA.WEIXIN_CIRCLE, snsPostListener);
    }

    /**
     * 分享到短信
     *
     * @param smsContent
     * @param snsPostListener
     */
    public void shareToSMS(String smsContent, final SocializeListeners.SnsPostListener snsPostListener) {
        if (!isSMSIntentAvailable()) {
            ToastUtil.show(mContext, "您的手机好像不支持短信功能哦~");
            return;
        }
        mController.setShareContent(smsContent);
        mController.postShare(mContext, SHARE_MEDIA.SMS, snsPostListener);
    }


    /**
     * 短信分享功能是否可用
     *
     * @return boolean
     */
    public boolean isSMSIntentAvailable() {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (DeviceUtil.getSDKVersionInt() < 19) {
            intent.putExtra("sms_body", "");
            intent.setType("vnd.android-dir/mms-sms");
        } else {
            Uri uri = Uri.parse("smsto:");
            intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "");
        }
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    /**
     * 分享到短信
     *
     * @param content 短信内容文本
     */
    public void shareInfo2SMS(String content) {
        if (!isSMSIntentAvailable()) {
            ToastUtil.show(mContext, "您的手机好像不支持短信功能哦~");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (DeviceUtil.getSDKVersionInt() < 19) {
            intent.setType("vnd.android-dir/mms-sms");
        } else {
            Uri uri = Uri.parse("smsto:");
            intent = new Intent(Intent.ACTION_SENDTO, uri);
        }
        intent.putExtra("sms_body", content);
        mContext.startActivity(intent);
    }

    /**
     * 方法描述 isEmailIntentAvailable:邮件分享是否能用
     *
     * @return
     * @author w_xiong 2013-8-13 For V5.0
     * @Modify By
     */
    public boolean isEmailIntentAvailable() {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.setType("plain/text");
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 分享到邮件(只支持文本)
     *
     * @param title   标题
     * @param content 分享内容
     * @author zhiwen.nan
     */
    public void shareInfo2Email(String title, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        List<ResolveInfo> resInfo = mContext.getPackageManager().queryIntentActivities(intent, 0);
        if (!resInfo.isEmpty()) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo info : resInfo) {
                Intent targetedIntent = new Intent(Intent.ACTION_SEND);
                targetedIntent.setType("text/plain");
                ActivityInfo activityInfo = info.activityInfo;
                if (activityInfo.packageName.contains("gm") || activityInfo.name.contains("mail")) {
                    targetedIntent.putExtra(Intent.EXTRA_TEXT, content);
                    targetedIntent.setPackage(activityInfo.packageName);
                    targetedShareIntents.add(targetedIntent);
                    break;
                }
            }
            Intent chooserIntent = null;
            if (!targetedShareIntents.isEmpty()) {
                chooserIntent = Intent.createChooser(targetedShareIntents.get(0), "Select app to share");
            }
            if (chooserIntent == null) {
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
                intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
                mContext.startActivity(Intent.createChooser(intent, "请选择邮件发送系统"));
            } else {
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
                try {
                    mContext.startActivity(chooserIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(mContext, "Can't find share component to share", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    /**
     * @param title     标题
     * @param imageUris 多附件的UriArrayList,如：
     *                  ArrayList<Uri> imageUris = new ArrayList<uri>();
     *                  imageUris.add(Uri.parse("file:///sdcard/test1.jpg"));
     *                  imageUris.add(Uri.parse("file:///sdcard/test2.jpg"));
     * @param content   发送的内容
     * @author zhiwen.nan
     * 邮件支持多附件发送
     */
    public void shareMuiltipleFile2Email(String title, ArrayList<Uri> imageUris, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent.setType("image/*");
        intent.setType("message/rfc882");
        mContext.startActivity(Intent.createChooser(intent, "请选择邮件发送系统"));
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * ----------------------------------------工具类方法------------------------------------------------
     */
    private byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 75, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 大于size 则加...
     *
     * @param size
     * @return
     */
    public static String ellipsizeTextBySize(String text, int size) {
        if (text == null || text.length() <= size) {
            return text;
        }
        return text.substring(0, size) + "...";
    }


    /**
     * 分享到邮件，支持文本和附件
     *
     * @param title   标题
     * @param content 发送内容
     * @param path    文件附件路径(sdcard路径)
     */
    public void shareInfo2Email(String title, String content, String path) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        }
        intent.setType("image/*");
        intent.setType("message/rfc882");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent, "请选择邮件发送系统"));
    }

    /**
     * 弹出系统分享对话框
     *
     * @param title   标题
     * @param content 内容
     * @author zhiwen.nan
     */
    public void shareInfoToMore(String title, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!TextUtils.isEmpty(title)) {
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        }
        intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent, null));
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text
     */
    public void ShareInfo2Copy(String text) {
        if (DeviceUtil.getSDKVersionInt() < 11) {
            try {
                android.text.ClipboardManager cbm = (android.text.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                if (cbm != null) {
                    cbm.setText(text);
                    ToastUtil.show(mContext, "内容已复制");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                android.content.ClipboardManager cbm = (android.content.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                if (cbm != null) {
                    ClipData clipData = ClipData.newPlainText("iwjw", text);
                    cbm.setPrimaryClip(clipData);
                    ToastUtil.show(mContext, "内容已复制");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 剪贴板读取文本
     */
    public String ReadCopyInfo() {
        String copy = "";
        try {
            android.text.ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            copy = cmb.getText().toString();
        } catch (Exception e) {
        }
        return copy;
    }


    /**********************以下方法不一定有用**************************/

    /**
     * 分享到微信，普通的文件url用此方法
     *
     * @param picUrl
     * @param webpageUrl
     * @param title
     * @param description
     */
    public void shareInfo2WeiXin(String picUrl, String webpageUrl, String title, String description, boolean isToFriendLine) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(picUrl);
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.app_icon_for_share);
            }
            shareInfo2WeiXin(bitmap, webpageUrl, title, description, isToFriendLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分享到微信，图片传入Bitmap
     *
     * @param bitmap
     * @param webpageUrl
     * @param title
     * @param description
     * @param isToFriendLine 是否分享到朋友
     */
    public void shareInfo2WeiXin(Bitmap bitmap, String webpageUrl, String title, String description, boolean isToFriendLine) {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        mApi.registerApp(WEIXIN_APP_ID);
        if (!mApi.isWXAppInstalled()) {
        } else {
            WXMediaMessage msg = new WXMediaMessage();
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.app_icon_for_share);
            }

            if (bitmap != null) {
                Bitmap thumbBmp = null;
                if (bitmap.getHeight() > THUMB_SIZE || bitmap.getWidth() > THUMB_SIZE) {
                    thumbBmp = BitmapUtil.resizeBitmap(bitmap, THUMB_SIZE, THUMB_SIZE);
                } else {
                    thumbBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                }
                msg.thumbData = bmpToByteArray(thumbBmp, true);
            }

            msg.title = title;
            msg.description = description;
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = webpageUrl;
            msg.mediaObject = webpage;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            if (isToFriendLine) {
                if (mApi.getWXAppSupportAPI() >= SUPPORT_FRIEND_LINE_LEVEL) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    return;
                }
            }
            mApi.sendReq(req);

        }
    }

    /**
     * 分享到微信朋友或者朋友圈，图片传入Bitmap
     *
     * @param bitmap
     * @param webpageUrl
     * @param title
     * @param description
     * @param isToFriendLine true 是代表分享到朋友圈，否则分享到对应朋友
     */
    public void shareInfo2WeiXinFriendsCircle(Bitmap bitmap, String webpageUrl, String title, String description, boolean isToFriendLine) {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        mApi.registerApp(WEIXIN_APP_ID);
        if (!mApi.isWXAppInstalled()) {
        } else {
            WXMediaMessage msg = new WXMediaMessage();
            if (bitmap != null) {
                Bitmap thumbBmp;
                if (bitmap.getHeight() > THUMB_SIZE || bitmap.getWidth() > THUMB_SIZE) {
                    thumbBmp = BitmapUtil.resizeBitmap(bitmap, THUMB_SIZE, THUMB_SIZE);
                } else {
                    thumbBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                }
                msg.thumbData = bmpToByteArray(thumbBmp, true);
            }
            msg.title = title;
            msg.description = description;

            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = webpageUrl;
            msg.mediaObject = webpage;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            if (mApi.getWXAppSupportAPI() >= SUPPORT_FRIEND_LINE_LEVEL) {
                req.scene = isToFriendLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
            } else {
            }
            mApi.sendReq(req);
        }
    }

    /**
     * 分享到朋友圈,图片作为正文
     *
     * @param bitmap
     */
    public void shareInfo2WeiXinFriendsCircleWithBigImg(Bitmap bitmap) {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        mApi.registerApp(WEIXIN_APP_ID);
        if (!mApi.isWXAppInstalled()) {
        } else {
            WXMediaMessage msg = new WXMediaMessage();
            WXImageObject obj = new WXImageObject(bitmap);
            msg.mediaObject = obj;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("img");
            req.message = msg;
            if (mApi.getWXAppSupportAPI() >= SUPPORT_FRIEND_LINE_LEVEL) {
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            } else {
            }
            mApi.sendReq(req);
        }
    }

    /**
     * 分享到微信，只分享文字
     *
     * @param content
     */
    public void shareInfo2WeiXin(String content, boolean isToFriendLine) {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        mApi.registerApp(WEIXIN_APP_ID);
        if (!mApi.isWXAppInstalled()) {
        } else {
            WXTextObject textObj = new WXTextObject();
            textObj.text = content;
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;
            msg.description = content;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("text");
            req.message = msg;

            if (isToFriendLine) {
                if (mApi.getWXAppSupportAPI() >= SUPPORT_FRIEND_LINE_LEVEL) {
                    req.scene = isToFriendLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                } else {
                    return;
                }
            }
            mApi.sendReq(req);
        }
    }

    /**
     * 打开微信
     */
    public void openWeiXin() {
        if (mApi == null) {
            mApi = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID);
        }
        mApi.openWXApp();
    }
}
