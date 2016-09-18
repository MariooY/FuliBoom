package com.bk.fuliboom.YoukuModule;

import android.graphics.Bitmap;


import com.bk.fuliboom.Repository.Beans.AccountInfo;

import java.util.List;

/**
 * Created by Bk on 2016/8/29.
 */

public interface IYouKuView {
    void showCaptchar(Bitmap bitmap);
    void showNewAccount(AccountInfo accountInfo);
    void showAccountList(List<AccountInfo> accounts);
    void showErrMsg(String msg);
}
