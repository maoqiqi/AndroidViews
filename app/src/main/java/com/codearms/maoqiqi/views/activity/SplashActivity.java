package com.codearms.maoqiqi.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.codearms.maoqiqi.views.R;

import java.lang.ref.WeakReference;

/**
 * 过渡页面
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int WHAT_COUNT = 0;
    private static final int WHAT_START = 1;
    private static final int DELAY_MILLIS = 1000;

    private int count = 3;
    private boolean isFirstStart = false;

    private ImageView ivSplash;
    private Button btnJump;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivSplash = findViewById(R.id.iv_splash);
        btnJump = findViewById(R.id.btn_jump);

        btnJump.setOnClickListener(this);

        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(WHAT_COUNT, DELAY_MILLIS);
    }

    @Override
    public void onClick(View v) {
        handler.removeMessages(WHAT_START);
        startActivity();
    }

    // 跳转到主页面,并且把当前页面关闭掉
    private void startActivity() {
        if (!isFirstStart) {
            isFirstStart = true;
            // 解决启动页全屏进入下一个界面(非全屏)出现下移的情况
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 禁止返回
        // super.onBackPressed();
    }

    private static final class MyHandler extends Handler {

        private WeakReference<SplashActivity> weakReference;

        MyHandler(SplashActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_COUNT:
                    if (weakReference.get() != null) {
                        SplashActivity activity = weakReference.get();
                        activity.btnJump.setVisibility(View.VISIBLE);
                        activity.btnJump.setText(activity.getString(R.string.jump, activity.count));
                        activity.ivSplash.setImageResource(R.drawable.bg_splash);
                        activity.handler.sendEmptyMessageDelayed(WHAT_START, DELAY_MILLIS);
                    }
                    break;
                case WHAT_START:
                    if (weakReference.get() != null) {
                        SplashActivity activity = weakReference.get();
                        activity.count--;
                        if (activity.count == 0) {
                            activity.btnJump.setVisibility(View.GONE);
                            activity.startActivity();
                        } else {
                            activity.btnJump.setText(activity.getString(R.string.jump, activity.count));
                            activity.handler.sendEmptyMessageDelayed(WHAT_START, DELAY_MILLIS);
                        }
                    }
                    break;
            }
        }
    }
}