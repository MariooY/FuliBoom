package com.bk.fuliboom.MainPage;

import android.util.Log;

import com.bk.fuliboom.Repository.Beans.Data;
import com.bk.fuliboom.Utils.GankApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Bk on 2016/9/17.
 */

public class MainModelImpl implements IMainModel {
    private IMainPresenter mPresenter;
    private GankApi gankApi;

    public MainModelImpl(MainPresenterImpl presenter){
        mPresenter = presenter;
        gankApi = new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GankApi.class);
    }

    @Override
    public void getVideoList(int amount, int page) {
        gankApi.getVideo(amount,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Data data) {
                        Log.e("data",data.getError() + "");
                        mPresenter.transVideoData(data);
                    }
                });
    }

    @Override
    public void getFuliList(int amount, int page) {

    }

    @Override
    public void getRecommentList(int amount, int page) {

    }

    @Override
    public void getAndroidList(int amount, int page) {

    }

    @Override
    public void getIosList(int amount, int page) {

    }
}
