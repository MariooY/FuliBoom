package com.bk.fuliboom.MainPage;

/**
 * Created by Bk on 2016/9/17.
 */

public interface IMainModel {
    void getVideoList(int amount, int page);
    void getFuliList(int amount, int page);
    void getRecommentList(int amount, int page);
    void getAndroidList(int amount, int page);
    void getIosList(int amount, int page);

}
