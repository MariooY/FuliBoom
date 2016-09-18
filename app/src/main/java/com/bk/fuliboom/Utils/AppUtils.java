package com.bk.fuliboom.Utils;

import com.bk.fuliboom.R;

/**
 * Created by Bk on 2016/9/17.
 */

public class AppUtils {
    public static int getResourseIDByUrl(String url) {
        int resId = R.drawable.web;
        if (url.contains("csdn.net")) {
            resId = R.drawable.csdn;
        } else if (url.contains("github.com")) {
            resId = R.drawable.github;
        } else if (url.contains("jianshu.com")) {
            resId = R.drawable.jianshu;
        } else if (url.contains("miaopai.com")) {
            resId = R.drawable.miaopai;
        } else if (url.contains("acfun.tv")) {
            resId = R.drawable.acfun;
        } else if (url.contains("bilibili.com")) {
            resId = R.drawable.bilibili;
        } else if (url.contains("youku.com")) {
            resId = R.drawable.youku;
        } else if (url.contains("weibo.com")) {
            resId = R.drawable.weibo;
        } else if (url.contains("weixin.qq")) {
            resId = R.drawable.weixin;
        }


        return resId;
    }
}
