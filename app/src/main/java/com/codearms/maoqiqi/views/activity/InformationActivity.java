package com.codearms.maoqiqi.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 12:28
 */
public class InformationActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView civProfile;
    private EditText etNickName;
    private LinearLayout llBirthday;
    private LinearLayout llOccupational;
    private LinearLayout llSchool;
    private LinearLayout llSpecialty;
    private LinearLayout llAddress;
    private LinearLayout llHomePage;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.information);
        setSupportActionBar(toolbar);

        civProfile = findViewById(R.id.civ_profile);
        etNickName = findViewById(R.id.et_nick_name);
        llBirthday = findViewById(R.id.ll_birthday);
        llOccupational = findViewById(R.id.ll_occupational);
        llSchool = findViewById(R.id.ll_school);
        llSpecialty = findViewById(R.id.ll_specialty);
        llAddress = findViewById(R.id.ll_address);
        llHomePage = findViewById(R.id.ll_home_page);
        btnFinish = findViewById(R.id.btn_finish);

        etNickName.setSelection(etNickName.getText().length());

        civProfile.setOnClickListener(this);
        llBirthday.setOnClickListener(this);
        llOccupational.setOnClickListener(this);
        llSchool.setOnClickListener(this);
        llSpecialty.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        llHomePage.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_profile:
                break;
            case R.id.ll_birthday:
                startActivity(new Intent(this, BirthdayActivity.class));
                break;
            case R.id.ll_occupational:
                startActivity(new Intent(this, OccupationalActivity.class));
                break;
            case R.id.ll_school:
                startActivity(new Intent(this, SchoolActivity.class));
                break;
            case R.id.ll_specialty:
                startActivity(new Intent(this, SchoolActivity.class));
                break;
            case R.id.ll_address:
                break;
            case R.id.ll_home_page:
                break;
            case R.id.btn_finish:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
