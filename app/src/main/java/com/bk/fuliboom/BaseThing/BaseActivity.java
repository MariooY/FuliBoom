package com.bk.fuliboom.BaseThing;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bk.fuliboom.About.AboutActivity;
import com.bk.fuliboom.FlappyFrogModule.FlappyFrogActivity;
import com.bk.fuliboom.IqiyiModule.IqiyiActivity;
import com.bk.fuliboom.MainPage.MainActivity;
import com.bk.fuliboom.R;
import com.bk.fuliboom.YoukuModule.YoukuActivity;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout contentView = null;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    protected String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(layoutResID);
        if (layoutResID == R.layout.activity_base){
            super.setContentView(R.layout.activity_base);
            contentView = (FrameLayout) findViewById(R.id.layout_content);
            contentView.removeAllViews();
        } else {
            View addView = LayoutInflater.from(this).inflate(layoutResID,null);
            contentView.addView(addView);
        }
        initView();


    }





    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        configureToolbar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.about:
                        Intent intent = new Intent(BaseActivity.this, AboutActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
        mNavigationView = (NavigationView)findViewById(R.id.navigation);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        configureDrawer(mDrawer);


        mNavigationView.setItemIconTintList(getResources().getColorStateList(R.color.colorGrey));
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main:
                        Intent intentMain = new Intent(BaseActivity.this, MainActivity.class);
                        intentMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intentMain);
                        break;
                    case R.id.iqiyi:
                        Intent intentIqiyi = new Intent(BaseActivity.this, IqiyiActivity.class);
                        intentIqiyi.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intentIqiyi);
                        break;
                    case R.id.youku:
                        Intent intent = new Intent(BaseActivity.this, YoukuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    case R.id.play_frog:
                        Intent frogIntent = new Intent(BaseActivity.this, FlappyFrogActivity.class);
                        frogIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(frogIntent);
                        break;
                    default:
                        break;

                }
                return false;
            }
        });

    }

    protected abstract void configureToolbar(Toolbar toolbar);

    protected abstract void configureDrawer(DrawerLayout drawer);


    protected void setToggle(){
        mToggle = new ActionBarDrawerToggle(this, mDrawer,mToolbar,R.string.state_open,R.string.state_close);
        mToggle.syncState();
        mDrawer.addDrawerListener(mToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
