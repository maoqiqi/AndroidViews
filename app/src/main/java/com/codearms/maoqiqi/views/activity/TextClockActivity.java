package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示TextClock
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/1 16:18
 */
public class TextClockActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_clock);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.text_clock);
        setSupportActionBar(toolbar);
    }
}
