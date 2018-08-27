package com.codearms.maoqiqi.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.lang.reflect.Method;

/**
 * 显示网页
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 11:42
 */
public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar progressBar;

    public static void start(Context context, String title, String url) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("url", url);
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.loading);
        // toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_menu_more));
        setSupportActionBar(toolbar);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progress_bar);

        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("title", "");
            String url = bundle.getString("url", "");
            if (getSupportActionBar() != null && title != null && !title.equals(""))
                getSupportActionBar().setTitle(title);
            if (!url.equals("")) webView.loadUrl(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //  显示popup menu内的图片
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null && menu instanceof MenuBuilder) {
            try {
                Method m = MenuBuilder.class.getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (Exception e) {
                Log.e("WebViewActivity", "onMenuOpened...unable to set icons for overflow menu", e);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_web_share:
                return true;
            case R.id.menu_copy_url:
                return true;
            case R.id.menu_open_url:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    private final class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (progressBar == null) return;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
        }
    }
}
