package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 职业
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/27 21:07
 */
public class OccupationalActivity extends BaseActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupational);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.occupational_label);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list_view);
        String[] data = getResources().getStringArray(R.array.occupational);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_occupational, R.id.tv, data);
        listView.setAdapter(adapter);
    }
}
