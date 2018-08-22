package com.codearms.maoqiqi.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.fragment.HomeFragment;
import com.codearms.maoqiqi.views.fragment.MineFragment;
import com.codearms.maoqiqi.views.fragment.NewsFragment;
import com.codearms.maoqiqi.views.utils.StatusBarUtils;

/**
 * 主页
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private TextView tvSetting;
    private TextView tvMode;

    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setFullscreen(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        navigationView = findViewById(R.id.navigation_view);
        tvSetting = findViewById(R.id.tv_setting);
        tvMode = findViewById(R.id.tv_mode);

        View navigationHeader = navigationView.getHeaderView(0);
        navigationHeader.findViewById(R.id.iv_scan_code).setOnClickListener(this);
        navigationHeader.findViewById(R.id.iv_project).setOnClickListener(this);

        // 定义drawerArrowDrawable
        DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(this);
        drawerArrowDrawable.setColor(ContextCompat.getColor(this, R.color.text_color_title));

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        // 设置drawerArrowDrawable
        toggle.setDrawerArrowDrawable(drawerArrowDrawable);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);

        tvSetting.setOnClickListener(this);
        tvMode.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Home");
                if (homeFragment == null) homeFragment = HomeFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, homeFragment).commit();
                return true;
            case R.id.nav_news:
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("News");
                if (newsFragment == null) newsFragment = NewsFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, newsFragment).commit();
                return true;
            case R.id.nav_mine:
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Mine");
                if (mineFragment == null) mineFragment = MineFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, mineFragment).commit();
                return true;

            case R.id.nav_project_introduction:
                startActivity(new Intent(this, ProjectIntroductionActivity.class));
                return true;
            case R.id.nav_update_description:
                startActivity(new Intent(this, UpdateDescriptionActivity.class));
                return true;
            case R.id.nav_scan_code:
                startActivity(new Intent(this, ScanCodeActivity.class));
                return true;
            case R.id.nav_problem_feedback:
                startActivity(new Intent(this, ProblemFeedbackActivity.class));
                return true;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_code:
                startActivity(new Intent(this, ScanCodeActivity.class));
                break;
            case R.id.iv_project:
                WebViewActivity.start(this, "", getString(R.string.git));
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_mode:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
