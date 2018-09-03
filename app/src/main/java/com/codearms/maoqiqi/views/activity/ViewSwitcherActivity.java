package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.lang.ref.WeakReference;

/**
 * 演示ImageSwitcher、TextSwitcher、ViewSwitcher
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/22 22:06
 */
public class ViewSwitcherActivity extends BaseActivity {

    private static final int WHAT_IMAGE = 1;
    private static final int WHAT_TEXT = 2;
    private static final int WHAT_VIEW = 3;
    private static final int DELAY_MILLIS = 2000;

    private ImageSwitcher imageSwitcher;
    private TextSwitcher textSwitcher;
    private ViewSwitcher viewSwitcher;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_switcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.view_switcher);
        setSupportActionBar(toolbar);

        imageSwitcher = findViewById(R.id.image_switcher);
        textSwitcher = findViewById(R.id.text_switcher);
        viewSwitcher = findViewById(R.id.view_switcher);

        textSwitcher.setFactory(textFactory);
        imageSwitcher.setFactory(imageFactory);
        viewSwitcher.setFactory(viewFactory);

        handler = new MyHandler(this);
        handler.sendEmptyMessage(WHAT_IMAGE);
        handler.sendEmptyMessage(WHAT_TEXT);
        handler.sendEmptyMessage(WHAT_VIEW);
    }

    private ViewSwitcher.ViewFactory imageFactory = new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {
            ImageView imageView = (ImageView) View.inflate(ViewSwitcherActivity.this,
                    R.layout.item_image_switcher, null);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return imageView;
        }
    };

    private ViewSwitcher.ViewFactory textFactory = new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {
            TextView textView = (TextView) View.inflate(ViewSwitcherActivity.this,
                    R.layout.item_text_switcher, null);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return textView;
        }
    };

    private ViewSwitcher.ViewFactory viewFactory = new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {
            View view = View.inflate(ViewSwitcherActivity.this,
                    R.layout.item_view_switcher, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return view;
        }
    };

    private static final class MyHandler extends Handler {

        private WeakReference<ViewSwitcherActivity> weakReference;

        private boolean loadImageFirst = true;
        private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5};
        private int imageIndex;

        private boolean loadTextFirst = true;
        private String[] texts;
        private int textIndex;

        private boolean loadViewFirst = true;
        private int viewIndex;

        MyHandler(ViewSwitcherActivity activity) {
            this.weakReference = new WeakReference<>(activity);
            this.texts = activity.getResources().getStringArray(R.array.text_switcher);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_IMAGE:
                    if (weakReference.get() != null) {
                        ViewSwitcherActivity activity = weakReference.get();
                        activity.imageSwitcher.setImageResource(imageIds[imageIndex]);
                        if (loadImageFirst) {
                            loadImageFirst = false;
                            activity.imageSwitcher.setInAnimation(activity, android.R.anim.fade_in);
                            activity.imageSwitcher.setOutAnimation(activity, android.R.anim.fade_out);
                        }
                        imageIndex++;
                        if (imageIndex == imageIds.length) {
                            imageIndex = 0;
                        }
                        activity.handler.sendEmptyMessageDelayed(WHAT_IMAGE, DELAY_MILLIS);
                    }
                    break;
                case WHAT_TEXT:
                    if (weakReference.get() != null) {
                        ViewSwitcherActivity activity = weakReference.get();
                        activity.textSwitcher.setText(texts[textIndex]);
                        if (loadTextFirst) {
                            loadTextFirst = false;
                            activity.textSwitcher.setInAnimation(activity, R.anim.switcher_enter);
                            activity.textSwitcher.setOutAnimation(activity, R.anim.switcher_exit);
                        }
                        textIndex++;
                        if (textIndex == texts.length) {
                            textIndex = 0;
                        }
                        activity.handler.sendEmptyMessageDelayed(WHAT_TEXT, DELAY_MILLIS);
                    }
                    break;
                case WHAT_VIEW:
                    if (weakReference.get() != null) {
                        ViewSwitcherActivity activity = weakReference.get();
                        View view = activity.viewSwitcher.getNextView();
                        TextView tv = view.findViewById(R.id.tv);
                        tv.setText(texts[viewIndex]);
                        activity.viewSwitcher.showNext();
                        if (loadViewFirst) {
                            loadViewFirst = false;
                            activity.viewSwitcher.setInAnimation(activity, R.anim.switcher_enter);
                            activity.viewSwitcher.setOutAnimation(activity, R.anim.switcher_exit);
                        }
                        viewIndex++;
                        if (viewIndex == texts.length) {
                            viewIndex = 0;
                        }
                        activity.handler.sendEmptyMessageDelayed(WHAT_VIEW, DELAY_MILLIS);
                    }
                    break;
            }
        }
    }
}