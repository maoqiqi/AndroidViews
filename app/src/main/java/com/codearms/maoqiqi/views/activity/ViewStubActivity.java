package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewStub;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.util.Random;

/**
 * ViewStub
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/6 15:40
 */
public class ViewStubActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.view_stub);
        setSupportActionBar(toolbar);

        ViewStub viewStub1 = findViewById(R.id.view_stub_1);
        ViewStub viewStub2 = findViewById(R.id.view_stub_2);

        if (new Random().nextInt() % 2 == 0) {
            viewStub1.inflate();
        } else {
            viewStub2.inflate();
        }
    }
}