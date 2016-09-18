package com.bk.fuliboom.Utils;

import com.bk.fuliboom.Repository.Beans.Data;
import com.bk.fuliboom.Repository.Beans.Result;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Bk on 2016/9/17.
 */

public interface GankApi {
     String BASE_URL = "http://www.gank.io/api/";

    @GET("data/福利/{count}/{page}")
    Observable<Data> getFuliPic(@Path("count")int count, @Path("page") int page);


    @GET("")
    Observable<Data> getAndroid();


    @GET("")
    Observable<Data> getIOS();


    @GET("data/休息视频/{count}/{page}")
    Observable<Data> getVideo(@Path("count")int count, @Path("page") int page);
}
