package com.bk.fuliboom.IqiyiModule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bk.fuliboom.BaseThing.BaseActivity;
import com.bk.fuliboom.R;
import com.bk.fuliboom.YoukuModule.YoukuFragment;


public class IqiyiActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youku);
        if (savedInstanceState == null){
            IqiyiFragment iqiyiFragment = new IqiyiFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.content_layout,iqiyiFragment);
            transaction.commit();
        }


    }

    @Override
    protected void configureToolbar(Toolbar toolbar) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("爱奇艺");
    }

    @Override
    protected void configureDrawer(DrawerLayout drawer) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
