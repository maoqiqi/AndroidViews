package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.views.R;

/**
 * 生日
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/27 21:01
 */
public class BirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.birthday_label);
        setSupportActionBar(toolbar);
    }
}
