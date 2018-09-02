package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.lang.ref.WeakReference;

/**
 * 演示TextSwitcher
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/22 22:06
 */
public class ViewSwitcherActivity extends BaseActivity implements ViewSwitcher.ViewFactory {

    private static final int WHAT = 0;
    private static final int DELAY_MILLIS = 2000;

    private TextSwitcher textSwitcher;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_switcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.view_switcher);
        setSupportActionBar(toolbar);

        textSwitcher = findViewById(R.id.text_switcher);
        textSwitcher.setFactory(this);

        handler = new MyHandler(this);
        handler.sendEmptyMessage(WHAT);
    }

    @Override
    public View makeView() {
        TextView textView = (TextView) View.inflate(this, R.layout.item_text_switcher, null);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return textView;
    }

    private static final class MyHandler extends Handler {

        private WeakReference<ViewSwitcherActivity> weakReference;
        private boolean first = true;
        private String[] data;
        private int index;

        MyHandler(ViewSwitcherActivity activity) {
            this.weakReference = new WeakReference<>(activity);
            this.data = activity.getResources().getStringArray(R.array.text_switcher);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT:
                    if (weakReference.get() != null) {
                        ViewSwitcherActivity activity = weakReference.get();
                        activity.textSwitcher.setText(data[index]);
                        if (first) {
                            first = false;
                            activity.textSwitcher.setInAnimation(activity, R.anim.switcher_enter);
                            activity.textSwitcher.setOutAnimation(activity, R.anim.switcher_exit);
                        }
                        index++;
                        if (index == data.length) {
                            index = 0;
                        }
                        activity.handler.sendEmptyMessageDelayed(WHAT, DELAY_MILLIS);
                    }
                    break;
            }
        }
    }
}