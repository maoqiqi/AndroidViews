package com.codearms.maoqiqi.views.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 关于
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/22 22:06
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvVersionName;
    private TextView tvProjectIntroduction;
    private TextView tvCheckUpdate;
    private TextView tvUpdateDescription;
    private TextView tvStar;
    private TextView tvDonate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.about);
        setSupportActionBar(toolbar);

        tvVersionName = findViewById(R.id.tv_version_name);
        tvProjectIntroduction = findViewById(R.id.tv_project_introduction);
        tvCheckUpdate = findViewById(R.id.tv_check_update);
        tvUpdateDescription = findViewById(R.id.tv_update_description);
        tvStar = findViewById(R.id.tv_star);
        tvDonate = findViewById(R.id.tv_donate);

        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            tvVersionName.setText(getString(R.string.current_version, versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvProjectIntroduction.setOnClickListener(this);
        tvCheckUpdate.setOnClickListener(this);
        tvUpdateDescription.setOnClickListener(this);
        tvStar.setOnClickListener(this);
        tvDonate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}