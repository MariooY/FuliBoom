package com.bk.fuliboom.MainPage;

import android.util.Log;

import com.bk.fuliboom.Repository.Beans.Data;

/**
 * Created by Bk on 2016/9/17.
 */

public class MainPresenterImpl implements IMainPresenter {
    private IMainView mView;
    private IMainModel mModel;
    private boolean mUpdate;

    public MainPresenterImpl(IMainView mView) {
        this.mView = mView;
        mModel = new MainModelImpl(this);
    }

    @Override
    public void transVideoData(Data data) {
        if (data != null && !data.getError() && data.getResults()!=null){
            if (mUpdate){
                mView.showUpdate(data.getResults());
            } else {
                mView.appendVideoList(data.getResults());
            }

        } else {
            mView.showError("更新休息视频列表失败");
        }

    }

    @Override
    public void getVideoList(int amount, int page, boolean update) {
        if (amount<=0  || page <= 0){
            mView.showError("");
            return;
        }
        mUpdate = update;
        mModel.getVideoList(amount,page);
    }
}
