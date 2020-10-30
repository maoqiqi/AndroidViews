package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 生日
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/27 21:01
 */
public class BirthdayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.birthday_label);
        setSupportActionBar(toolbar);
    }
}
