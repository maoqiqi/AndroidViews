package com.codearms.maoqiqi.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * 过渡页面
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 21:10
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private int count = 3;
    private boolean isFirstStart = false;

    private ImageView ivSplash;
    private TextView tvJump;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivSplash = findViewById(R.id.iv_splash);
        tvJump = findViewById(R.id.tv_jump);

        tvJump.setOnClickListener(this);

        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    public void onClick(View v) {
        handler.removeMessages(1);
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
                case 0:
                    if (weakReference.get() != null) {
                        SplashActivity activity = weakReference.get();
                        activity.tvJump.setVisibility(View.VISIBLE);
                        activity.tvJump.setText(activity.getString(R.string.jump, activity.count));
                        activity.ivSplash.setImageResource(R.drawable.bg_splash);
                        activity.handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case 1:
                    if (weakReference.get() != null) {
                        SplashActivity activity = weakReference.get();
                        activity.count--;
                        if (activity.count == 0) {
                            activity.tvJump.setVisibility(View.GONE);
                            activity.startActivity();
                        } else {
                            activity.tvJump.setText(activity.getString(R.string.jump, activity.count));
                            activity.handler.sendEmptyMessageDelayed(1, 1000);
                        }
                    }
                    break;
            }
        }
    }
}