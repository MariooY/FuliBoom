package com.bk.fuliboom.MainPage;

import com.bk.fuliboom.Repository.Beans.Data;

import java.util.List;

/**
 * Created by Bk on 2016/9/17.
 */

public interface IMainPresenter {
    void transVideoData(Data data);
    void getVideoList(int amount, int page, boolean update);
}
