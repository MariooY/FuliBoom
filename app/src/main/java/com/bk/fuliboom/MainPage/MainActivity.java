package com.bk.fuliboom.MainPage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bk.fuliboom.BaseThing.BaseActivity;
import com.bk.fuliboom.R;
import com.bk.fuliboom.Utils.Api;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

public class MainActivity extends BaseActivity {
    private long lastBackKeyDownTick = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1500;


    @Override
    public void onBackPressed() {
        beforeOnBackPressed();
        long currentTick = System.currentTimeMillis();
        if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            lastBackKeyDownTick = currentTick;
        } else {
            finish();
            System.exit(0);
        }
    }

    protected void beforeOnBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, Api.BMOB_APP_KEY);
        BmobUpdateAgent.setUpdateOnlyWifi(true);
        BmobUpdateAgent.update(this);
        if (savedInstanceState == null){
            MainFragment fragment = new MainFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.content_main,fragment);
            transaction.commit();
        }

    }




    @Override
    protected void configureToolbar(Toolbar toolbar) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("一起去爆炸(～￣▽￣)～");

    }



    @Override
    protected void configureDrawer(DrawerLayout drawer) {
       setToggle();
    }


}
