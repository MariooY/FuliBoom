package com.bk.fuliboom.Utils;

import android.widget.Toast;

/**
 * Created by Bk on 2016/9/17.
 */

public class Hints {
    public static void showTost(String msg){
        Toast.makeText(FuliApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }

}
