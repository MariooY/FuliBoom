package com.bk.fuliboom.YoukuModule;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.bk.fuliboom.Repository.Beans.AccountInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Bk on 2016/8/29.
 */

public class YoukuPresenterImpl implements IYoukuPresenter {
    private IYoukuModel mModel;
    private IYouKuView mView;

    public YoukuPresenterImpl(IYouKuView mView) {
        this.mView = mView;
        mModel = new YoukuModelImpl(this);
    }

    @Override
    public void setNewAccount(AccountInfo account) {
        mView.showNewAccount(account);
    }

    @Override
    public void getNewAccount(String captchar) {
        mModel.getNewAccount(captchar);
    }

    @Override
    public void getPastAccount() {
        mModel.getPreAccounts();
    }

    @Override
    public void setPastAccount(List<AccountInfo> list) {
        Log.e("sql size", "" + list.get(0).getmAccount() +" " + list.get(0).getmPassword());
        mView.showAccountList(list);
    }

    @Override
    public void getCaptchar() {
        mModel.getCaptchar();
    }

    @Override
    public void setCaptchar(InputStream inputStream) {
        if (inputStream != null){
            mView.showCaptchar(BitmapFactory.decodeStream(inputStream));
        }else {
            mView.showCaptchar(null);
        }

    }

    @Override
    public void onError(String err) {
        switch (err){
            case "1":
                err = "验证码错误,请重试";
                getCaptchar();
                break;
            case "2":
                err = "今日获取账号已达上限！请明天再试";
                break;
            default:
                break;
        }
        mView.showErrMsg(err);
    }
}
