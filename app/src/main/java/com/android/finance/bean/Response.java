package com.android.finance.bean;

/**
 * server response
 */
public class Response {

    private int errorCode;
    private String message;

    private int bizCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        return errorCode == 0;
    }



}
