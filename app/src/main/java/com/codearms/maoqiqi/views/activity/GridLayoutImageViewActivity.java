package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 演示GridLayout、TableLayout、TableRow、ImageView、ImageButton
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/26 22:06
 */
public class GridLayoutImageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_image_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.grid_Layout_image_view);
        setSupportActionBar(toolbar);
    }
}
