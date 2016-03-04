package com.qtt.jinrong.config;


import com.qtt.framework.config.AppConfig;

/**
 * 获取其他服务接口api url
 * Created by yanxin on 2016/1/25.
 */
public class ServerApi {

    /**
     * 获取视频soa 上传视频ID接口url
     * @return
     */
    public static String getVideoSoaUploadUrl() {
        StringBuilder url = new StringBuilder("http://");
        url.append(AppConfig.getHost_video_soa());
        /*if(!NetworkUtil.build().isProd()) {
            url.append("/notifyQCloudUploadSuccess.action");
        } else {
            url.append("/notify.do");
        }*/
        url.append("/notifyQCloudUploadSuccess.action");
        return url.toString();
    }

}
