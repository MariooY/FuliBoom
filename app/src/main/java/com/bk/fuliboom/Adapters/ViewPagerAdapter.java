package com.bk.fuliboom.Adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.bk.fuliboom.Repository.Beans.Data;
import com.bk.fuliboom.Repository.Beans.Result;

import java.util.List;

/**
 * Created by Bk on 2016/9/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<Result> mData;
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
