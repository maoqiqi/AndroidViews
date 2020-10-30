package com.codearms.maoqiqi.views.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 注册页面
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText tieEmail;
    private TextInputEditText tiePassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.register);
        setSupportActionBar(toolbar);

        tieEmail = findViewById(R.id.tie_email);
        tiePassword = findViewById(R.id.tie_password);
        btnRegister = findViewById(R.id.btn_register);

        tieEmail.addTextChangedListener(textWatcher);
        tiePassword.addTextChangedListener(textWatcher);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, InformationActivity.class));
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            btnRegister.setEnabled(tieEmail.getText().length() != 0 && tiePassword.getText().length() != 0);
        }
    };
}
