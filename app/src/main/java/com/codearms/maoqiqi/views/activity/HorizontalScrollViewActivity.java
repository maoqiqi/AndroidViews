package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示HorizontalScrollView
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/1 17:00
 */
public class HorizontalScrollViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.horizontal_scroll_view);
        setSupportActionBar(toolbar);
    }
}