package com.bk.fuliboom.YoukuModule;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.bk.fuliboom.Repository.Beans.AccountInfo;
import com.bk.fuliboom.Repository.Beans.JsonResult;
import com.bk.fuliboom.Repository.DataBase.AccountDao;
import com.bk.fuliboom.Repository.DataBase.AccountsDBHelper;
import com.bk.fuliboom.Utils.DecodeUtil;
import com.bk.fuliboom.Utils.FuliApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Bk on 2016/8/29.
 */

public class YoukuModelImpl implements IYoukuModel {
    private AccountDao dao;
    private static final String BASE_URL = "http://free.45fan.com/";
    private IYoukuPresenter mPresenter;
    APIService service;
    private Context mContext;


    public YoukuModelImpl(IYoukuPresenter presenter) {
        mContext = FuliApplication.getInstance();
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
        mPresenter =presenter;
        dao = new AccountDao(AccountsDBHelper.TABLE_YOUKU);
    }

    @Override
    public void  getNewAccount(String Captchar) {
        service.getAccount(Captchar,"2")
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<JsonResult, Observable<AccountInfo>>() {
                    @Override
                    public Observable<AccountInfo> call(JsonResult result) {
                        Log.e("json result", result.getError());
                        if (!result.getError().equals("0")){
                            mPresenter.onError(result.getError());
                            return Observable.just(null);
                        } else {
                            AccountInfo account = new AccountInfo();
                            String data = result.getStr();
                            account.setmAccount(DecodeUtil.decodeAccountString(data));
                            account.setmPassword(DecodeUtil.decodePassString(data));
                            return Observable.just(account);
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccountInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("On new Account", "something went wrong here");
                    }

                    @Override
                    public void onNext(AccountInfo account) {
                        dao.insertOne(account);
                        mPresenter.setNewAccount(account);
                    }
                });

    }

    @Override
    public void getPreAccounts() {

        mPresenter.setPastAccount(dao.getAllAccounts());

//        service.getAccounts()
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Func1<Response<ResponseBody>, Observable<List<AccountInfo>>>() {
//                    @Override
//                    public Observable<List<AccountInfo>> call(Response<ResponseBody> responseBodyResponse) {
//                        try {
//                            return Observable.just(parseAccounts(responseBodyResponse.body().string()));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<AccountInfo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<AccountInfo> accountInfos) {
//                        mPresenter.setPastAccount(accountInfos);
//                    }
//                });

    }

    public void log(String tag, boolean state){
        Log.e(tag,""+state);
    }

    public List<AccountInfo> parseAccounts(String rawSource){
        List<AccountInfo> accountList = new ArrayList<>();

        return accountList;
    }

    @Override
    public void getCaptchar() {
        Log.e("get","running");
        service.getCaptcha()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Log.e("code","" + responseBodyResponse.code());
                        if (responseBodyResponse.code() ==200){
                            mPresenter.setCaptchar(responseBodyResponse.body().byteStream());
                        } else {
                            mPresenter.setCaptchar(null);
                        }
                    }
                });
    }


    public interface APIService{
        @GET("ajax.php")
        Observable<JsonResult> getAccount(@Query("code") String code, @Query("typename") String typename);


        @GET("youku")
        Observable<Response<ResponseBody>> getAccounts();


        @Headers("Referer:http://free.45fan.com/youku/")
        @GET("code.php?s=8733613082")
        Observable<Response<ResponseBody>> getCaptcha();


    }











}
