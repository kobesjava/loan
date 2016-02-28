package com.android.finance.common.wrap;

import android.os.CountDownTimer;

import java.lang.ref.WeakReference;

/**
 * Created by yanxin on 16/2/24.
 */
public abstract class WrapCountDownTimer<T> extends CountDownTimer {

    private WeakReference<T> mWeakReference;

    public WrapCountDownTimer(long millisInFuture, long countDownInterval,T t) {
        super(millisInFuture,countDownInterval);
        mWeakReference = new WeakReference(t);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(mWeakReference != null || mWeakReference.get() != null) {
            onTick(mWeakReference.get(),millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if(mWeakReference != null || mWeakReference.get() != null) {
            onFinish(mWeakReference.get());
        }
    }

    /**
     * 取消倒计时
     */
    public void stop() {
        mWeakReference.clear();
        super.cancel();
    }

    protected abstract void onFinish(T t);
    protected abstract void onTick(T t,long millisUntilFinished);
}
