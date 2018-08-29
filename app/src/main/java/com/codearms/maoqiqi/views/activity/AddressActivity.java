package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.NumberPicker;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 地址
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/29 23:06
 */
public class AddressActivity extends BaseActivity {

    private NumberPicker npProvince;
    private NumberPicker npCity;
    private NumberPicker npDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.address_label);
        setSupportActionBar(toolbar);

        npProvince = findViewById(R.id.np_province);
        npCity = findViewById(R.id.np_city);
        npDistrict = findViewById(R.id.np_district);

        // 设置不可编辑
        npProvince.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        String[] provinces = getResources().getStringArray(R.array.provinces);
        npProvince.setDisplayedValues(provinces);
        // 这两行不能缺少,不然只能显示第一个
        npProvince.setMinValue(0);
        npProvince.setMaxValue(provinces.length - 1);
        npProvince.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        npCity.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        String[] cities = getResources().getStringArray(R.array.cities);
        npCity.setDisplayedValues(cities);
        npCity.setMinValue(0);
        npCity.setMaxValue(cities.length - 1);

        npDistrict.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        String[] districts = getResources().getStringArray(R.array.districts);
        npDistrict.setDisplayedValues(districts);
        npDistrict.setMinValue(0);
        npDistrict.setMaxValue(districts.length - 1);
    }
}
