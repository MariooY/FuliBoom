package com.bk.fuliboom.MainPage;

import android.util.Log;

import com.bk.fuliboom.Repository.Beans.Data;

/**
 * Created by Bk on 2016/9/17.
 */

public class MainPresenterImpl implements IMainPresenter {
    private IMainView mView;
    private IMainModel mModel;

    public MainPresenterImpl(IMainView mView) {
        this.mView = mView;
        mModel = new MainModelImpl(this);
    }

    @Override
    public void transVideoData(Data data) {
        Log.e("trans", "running");
        if (data != null
                && !data.getError()
                && data.getResults()!=null){
            mView.appendVideoList(data.getResults());
        } else {
            mView.showError("更新休息视频列表失败");
        }

    }

    @Override
    public void getVideoList(int amount, int page) {
        if (amount<=0  || page <= 0){
            mView.showError("");
            return;
        }
        mModel.getVideoList(amount,page);
    }
}
