package com.qtt.jinrong.task;

import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.util.FileUtil;

/**
 * Created by yanxin on 16/4/5.
 */
public class CleanTask extends AbstractTask{

    @Override
    public void start() {
        //清理照片
        FileUtil.delete(Constants.DATAINFO_DIR);
    }
}
