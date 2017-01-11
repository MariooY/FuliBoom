package com.bk.fuliboom.FlappyFrogModule;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bk.fuliboom.BaseThing.BaseActivity;
import com.bk.fuliboom.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlappyFrogActivity extends BaseActivity {

    @BindView(R.id.flappy_frog_app)
    WebView flappyFrog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flappy_frog);
        ButterKnife.bind(this);
        flappyFrog.getSettings().setJavaScriptEnabled(true);
        flappyFrog.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("https://tusenpo.github.io/FlappyFrog/");
                return true;
            }
        });
        flappyFrog.loadUrl("https://tusenpo.github.io/FlappyFrog/");
    }

    @Override
    protected void configureToolbar(Toolbar toolbar) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("FlappyFrog");
    }

    @Override
    protected void configureDrawer(DrawerLayout drawer) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                flappyFrog.destroy();
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        flappyFrog.destroy();
        finish();
    }
}
