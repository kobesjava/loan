package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/4/16.
 */
public class DataModel {

    private Integer imgType;
    private String  filePath;//服务器地址
    private String  path; //本地地址
    private boolean isUpload;

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }
}
