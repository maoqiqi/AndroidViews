package com.codearms.maoqiqi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 12:28
 */
public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView civProfile;
    private EditText etNickName;
    private LinearLayout llBirthday;

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

        etNickName.setSelection(etNickName.getText().length());

        civProfile.setOnClickListener(this);
        llBirthday.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
