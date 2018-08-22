package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 项目介绍
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 15:32
 */
public class ProjectIntroductionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_introduction);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.project_introduction);
        setSupportActionBar(toolbar);
    }
}
