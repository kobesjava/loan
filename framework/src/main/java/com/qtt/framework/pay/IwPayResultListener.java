package com.qtt.framework.pay;

/**
 * Created by Jer on 2015/9/18.
 */
public interface  IwPayResultListener<T> {

    /**
     * 成功
     *
     * @param object
     */
    void onPayComplete(T object);

    /**
     * 意外情况状态延迟的
     *
     * @param msgInfo
     * @param object
     */
    void onPayStateDelay(String msgInfo, T object);

    /**
     * 失败
     *
     * @param msgInfo
     * @param errorCode
     */
    void onPayFailInfo(String msgInfo, String errorCode, T object);


}
