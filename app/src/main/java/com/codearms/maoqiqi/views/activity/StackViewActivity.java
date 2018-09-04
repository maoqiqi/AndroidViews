package com.codearms.maoqiqi.views.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
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
public class StackViewActivity extends BaseActivity {

    private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.stack_view);
        setSupportActionBar(toolbar);

        StackView stackView = findViewById(R.id.stack_view);
        stackView.setAdapter(new MyAdapter(this));
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
