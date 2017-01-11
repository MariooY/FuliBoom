package com.bk.fuliboom.About;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bk.fuliboom.BaseThing.BaseActivity;
import com.bk.fuliboom.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void configureToolbar(Toolbar toolbar) {

    }

    @Override
    protected void configureDrawer(DrawerLayout drawer) {

    }
}
