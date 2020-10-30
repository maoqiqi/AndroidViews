package com.codearms.maoqiqi.views;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * AppCompatActivity基类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}