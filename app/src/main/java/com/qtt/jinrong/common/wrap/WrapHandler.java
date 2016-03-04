package com.qtt.jinrong.common.wrap;

import android.os.Handler;
import android.os.Message;


import com.qtt.framework.util.LogUtil;

import java.lang.ref.WeakReference;

/**
 * handler 弱引用外部对象 防止泄露
 * @author yanxin
 */
public abstract class WrapHandler<T> extends Handler {

    private String TAG = "WrapHandler";

    private WeakReference<T> mWeakReference;

    public WrapHandler(T t) {
        mWeakReference = new WeakReference(t);
    }

    /**
     * 清理所有的请求(message,runnable)
     */
    public void destory() {
        if(mWeakReference != null) mWeakReference.clear();
        mWeakReference = null;
        removeCallbacksAndMessages(null);
    }

    @Override
    public void handleMessage(Message msg) {
        if(mWeakReference == null || mWeakReference.get() == null) {
            LogUtil.d(TAG, "wraphandler T has recyled");
            return;
        }

        handlMsg(mWeakReference.get(),msg);
    }

    /**
     * 消息处理
     * @param t
     * @param msg
     */
    protected abstract void handlMsg(T t,Message msg);

}
