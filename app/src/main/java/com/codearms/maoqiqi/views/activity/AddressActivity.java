package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.NumberPicker;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.bean.ProvinceBean;
import com.codearms.maoqiqi.views.utils.AssetsUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 地址
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/29 23:06
 */
public class AddressActivity extends BaseActivity {

    private NumberPicker npProvince;
    private NumberPicker npCity;
    private NumberPicker npDistrict;

    private List<ProvinceBean> provinceBeanList;

    private int provincePosition = 0;
    private int cityPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.address_label);
        setSupportActionBar(toolbar);

        String json = AssetsUtils.getJson(this, "address.json");
        provinceBeanList = new Gson().fromJson(json, new TypeToken<List<ProvinceBean>>() {
        }.getType());

        npProvince = findViewById(R.id.np_province);
        npCity = findViewById(R.id.np_city);
        npDistrict = findViewById(R.id.np_district);

        // 设置不可编辑
        npProvince.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setProvince();
        npProvince.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                provincePosition = newVal;
                cityPosition = 0;
                List<ProvinceBean.CityBean> cityBeanList = provinceBeanList.get(provincePosition).getCityBeanList();
                List<String> districtList = cityBeanList.get(cityPosition).getDistrictList();
                setCity(cityBeanList);
                setDistrict(districtList);
            }
        });

        npCity.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setCity(provinceBeanList.get(provincePosition).getCityBeanList());
        npCity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cityPosition = newVal;
                List<String> districtList = provinceBeanList.get(provincePosition).getCityBeanList().get(cityPosition).getDistrictList();
                setDistrict(districtList);
            }
        });

        npDistrict.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setDistrict(provinceBeanList.get(provincePosition).getCityBeanList().get(cityPosition).getDistrictList());
        npDistrict.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
    }

    private void setProvince() {
        String[] provinces = new String[provinceBeanList.size()];
        for (int i = 0; i < provinceBeanList.size(); i++) {
            provinces[i] = provinceBeanList.get(i).getName();
        }
        npProvince.setDisplayedValues(provinces);
        // 这两行不能缺少,不然只能显示第一个
        npProvince.setMinValue(0);
        npProvince.setMaxValue(provinces.length - 1);
        // 不循环
        npProvince.setWrapSelectorWheel(false);
    }

    private void setCity(List<ProvinceBean.CityBean> cityBeanList) {
        String[] cities = new String[cityBeanList.size()];
        for (int i = 0; i < cityBeanList.size(); i++) {
            cities[i] = cityBeanList.get(i).getName();
        }
        npCity.setDisplayedValues(null);
        npCity.setMinValue(0);
        npCity.setMaxValue(cities.length - 1);
        npCity.setDisplayedValues(cities);
    }

    private void setDistrict(List<String> districtList) {
        String[] districts = new String[districtList.size()];
        for (int i = 0; i < districtList.size(); i++) {
            districts[i] = districtList.get(i);
        }
        npDistrict.setDisplayedValues(null);
        npDistrict.setMinValue(0);
        npDistrict.setMaxValue(districts.length - 1);
        npDistrict.setDisplayedValues(districts);
    }
}
