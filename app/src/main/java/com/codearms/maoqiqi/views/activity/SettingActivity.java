package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 设置
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/23 17:50
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlSystemAnimation;
    private TextView tvSystemAnimationTitle;
    private TextView tvSystemAnimationContent;
    private SwitchCompat scSystemAnimation;
    private RelativeLayout rlLauncherPage;
    private TextView tvLauncherPageTitle;
    private TextView tvLauncherPageContent;
    private SwitchCompat scLauncherPage;
    private RelativeLayout rlLauncherPageRandom;
    private TextView tvLauncherPageRandomTitle;
    private TextView tvLauncherPageRandomContent;
    private SwitchCompat scLauncherPageRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.setting);
        setSupportActionBar(toolbar);

        rlSystemAnimation = findViewById(R.id.rl_system_animation);
        tvSystemAnimationTitle = findViewById(R.id.tv_system_animation_title);
        tvSystemAnimationContent = findViewById(R.id.tv_system_animation_content);
        scSystemAnimation = findViewById(R.id.sc_system_animation);
        rlLauncherPage = findViewById(R.id.rl_launcher_page);
        tvLauncherPageTitle = findViewById(R.id.tv_launcher_page_title);
        tvLauncherPageContent = findViewById(R.id.tv_launcher_page_content);
        scLauncherPage = findViewById(R.id.sc_launcher_page);
        rlLauncherPageRandom = findViewById(R.id.rl_launcher_page_random);
        tvLauncherPageRandomTitle = findViewById(R.id.tv_launcher_page_random_title);
        tvLauncherPageRandomContent = findViewById(R.id.tv_launcher_page_random_content);
        scLauncherPageRandom = findViewById(R.id.sc_launcher_page_random);

        rlSystemAnimation.setOnClickListener(this);
        rlLauncherPage.setOnClickListener(this);
        rlLauncherPageRandom.setOnClickListener(this);

        scSystemAnimation.setChecked(true);
        scLauncherPage.setChecked(true);
        setLauncherPageRandomEnable();
        scLauncherPageRandom.setChecked(true);
        setLauncherPageRandomContent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_system_animation:
                scSystemAnimation.toggle();
                break;
            case R.id.rl_launcher_page:
                scLauncherPage.toggle();
                setLauncherPageRandomEnable();
                break;
            case R.id.rl_launcher_page_random:
                scLauncherPageRandom.toggle();
                setLauncherPageRandomContent();
                break;
        }
    }

    private void setLauncherPageRandomEnable() {
        if (scLauncherPage.isChecked()) {
            tvLauncherPageContent.setText(R.string.launcher_page_content_1);
            rlLauncherPageRandom.setClickable(true);
            tvLauncherPageRandomTitle.setTextColor(ContextCompat.getColor(this, R.color.color_primary));
        } else {
            tvLauncherPageContent.setText(R.string.launcher_page_content_2);
            rlLauncherPageRandom.setClickable(false);
            tvLauncherPageRandomTitle.setTextColor(ContextCompat.getColor(this, R.color.text_color_gray));
        }
    }

    private void setLauncherPageRandomContent() {
        if (scLauncherPageRandom.isChecked()) {
            tvLauncherPageRandomContent.setText(R.string.launcher_page_random_content_1);
        } else {
            tvLauncherPageRandomContent.setText(R.string.launcher_page_random_content_2);
        }
    }
}
