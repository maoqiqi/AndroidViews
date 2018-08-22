package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 问题反馈
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/22 21:19
 */
public class ProblemFeedbackActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvIssues;
    private TextView tvProblems;
    private TextView tvQq;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_feedback);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.problem_feedback);
        setSupportActionBar(toolbar);

        tvIssues = findViewById(R.id.tv_issues);
        tvProblems = findViewById(R.id.tv_problems);
        tvQq = findViewById(R.id.tv_qq);
        tvEmail = findViewById(R.id.tv_email);

        tvIssues.setOnClickListener(this);
        tvProblems.setOnClickListener(this);
        tvQq.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
