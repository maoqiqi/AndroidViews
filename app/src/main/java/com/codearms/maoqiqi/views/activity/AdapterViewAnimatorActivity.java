package com.codearms.maoqiqi.views.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * StackView
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/4 14:42
 */
public class AdapterViewAnimatorActivity extends BaseActivity {

    private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5};

    private static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_animator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.adapter_view_animator);
        setSupportActionBar(toolbar);

        StackView stackView = findViewById(R.id.stack_view);
        AdapterViewFlipper adapterViewFlipper = findViewById(R.id.adapter_view_flipper);

        stackView.setAdapter(new MyAdapter(this));
        adapterViewFlipper.setAdapter(new MyAdapter(this));

        // 设置自动播放的时间间隔
        adapterViewFlipper.setFlipInterval(DELAY_MILLIS);
        // 设置组件显示时使用的动画
        // adapterViewFlipper.setInAnimation(this, R.anim.switcher_enter);
        // 设置组件隐藏时使用的动画
        // adapterViewFlipper.setOutAnimation(this, R.anim.switcher_exit);
        // 设置显示该组件的第一个View时是否使用动画
        adapterViewFlipper.setAnimateFirstView(false);
        // 设置显示该组件是否是自动播放
        adapterViewFlipper.setAutoStart(true);
    }

    private final class MyAdapter extends BaseAdapter {

        private Context context;

        MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return imageIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_image_switcher, null);
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setImageResource(imageIds[position]);
            return convertView;
        }
    }
}