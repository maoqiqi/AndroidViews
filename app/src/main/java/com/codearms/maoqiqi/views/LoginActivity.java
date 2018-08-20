package com.codearms.maoqiqi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录页面
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgetPassword;
    private TextView tvNewUserRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.login));
        setSupportActionBar(toolbar);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgetPassword = findViewById(R.id.tv_forget_password);
        tvNewUserRegister = findViewById(R.id.tv_new_user_register);

        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    if (email.equals("") || password.equals("")) {
                        Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    return true;
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvNewUserRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.tv_new_user_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
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
            btnLogin.setEnabled(etEmail.getText().length() != 0 && etPassword.getText().length() != 0);
        }
    };
}