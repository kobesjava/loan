package com.qtt.jinrong.common.share;

/**
 * Created by Ted on 2014/12/5.
 */
public class AgendaShareModule {
    private String Title_wx;
    private String Content_wx;
    private String Title_wxq;
    private String Content_wxq;
    private String mCopyContent;
    private String mUrl;
    private int mIconId;

    public String getTitle_wx() {
        return Title_wx;
    }

    public void setTitle_wx(String title_wx) {
        Title_wx = title_wx;
    }

    public String getContent_wx() {
        return Content_wx;
    }

    public void setContent_wx(String content_wx) {
        Content_wx = content_wx;
    }

    public String getTitle_wxq() {
        return Title_wxq;
    }

    public void setTitle_wxq(String title_wxq) {
        Title_wxq = title_wxq;
    }

    public String getContent_wxq() {
        return Content_wxq;
    }

    public void setContent_wxq(String content_wxq) {
        Content_wxq = content_wxq;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public String getCopyContent() {
        return mCopyContent;
    }

    public void setCopyContent(String mCopyContent) {
        this.mCopyContent = mCopyContent;
    }
}
