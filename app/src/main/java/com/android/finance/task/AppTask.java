package com.android.finance.task;

/**
 * @author yanxin
 */
public interface AppTask {

    /**
     * 不要做耗时的操作
     */
    void start();

    void stop();

}
