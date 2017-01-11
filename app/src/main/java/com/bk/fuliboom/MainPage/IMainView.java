package com.bk.fuliboom.MainPage;

import com.bk.fuliboom.Repository.Beans.Result;

import java.util.List;

/**
 * Created by Bk on 2016/9/17.
 */

public interface IMainView {
    void appendVideoList(List<Result> results);
    void getVideoList(int amount, int page);
    void showError(String msg);
    void showUpdate(List<Result> results);
}
