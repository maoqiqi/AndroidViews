package com.codearms.maoqiqi.views.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
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

    // 上次切换的Fragment
    private Fragment fragment;

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
                switchFragment(fragment, homeFragment);
                return true;
            case R.id.nav_news:
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("News");
                if (newsFragment == null) newsFragment = NewsFragment.newInstance();
                switchFragment(fragment, newsFragment);
                return true;
            case R.id.nav_mine:
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Mine");
                if (mineFragment == null) mineFragment = MineFragment.newInstance();
                switchFragment(fragment, mineFragment);
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
            case R.id.nav_donate:
                startActivity(new Intent(this, DonateActivity.class));
                break;
        }
        return false;
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (to != null && from != to) {//from != to才切换
            fragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            // from隐藏
            if (from != null)
                ft.hide(from);

            if (!to.isAdded()) {
                // 没有被添加,添加to
                ft.add(R.id.fl_content, to).commit();
            } else {
                // 已经被添加,显示to
                ft.show(to).commit();
            }
        }
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
                startActivity(new Intent(this, SettingActivity.class));
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
