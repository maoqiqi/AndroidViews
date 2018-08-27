package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示ProgressBar、RatingBar、SeekBar
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/27 12:03
 */
public class SpinnerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.spinner);
        setSupportActionBar(toolbar);
    }
}
