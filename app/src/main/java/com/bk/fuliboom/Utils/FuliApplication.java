package com.bk.fuliboom.Utils;

import android.app.Application;

/**
 * Created by Bk on 2016/9/16.
 */

public class FuliApplication extends Application {
    private static FuliApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static FuliApplication getInstance(){
        return mInstance;
    }
}
