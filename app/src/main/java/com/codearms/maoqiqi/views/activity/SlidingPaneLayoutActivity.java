package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示SlidingPaneLayout
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/27 14:14
 */
public class SlidingPaneLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_pane_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sliding_pane_layout);
        setSupportActionBar(toolbar);
    }
}
