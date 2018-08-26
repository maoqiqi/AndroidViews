package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示ImageButton
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/26 22:06
 */
public class GridLayoutImageButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_image_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.grid_Layout_image_button);
        setSupportActionBar(toolbar);
    }
}
