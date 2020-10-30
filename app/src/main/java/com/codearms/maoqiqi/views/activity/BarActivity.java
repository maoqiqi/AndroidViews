package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示ProgressBar、RatingBar、SeekBar
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/26 22:06
 */
public class BarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.bar);
        setSupportActionBar(toolbar);
    }
}
