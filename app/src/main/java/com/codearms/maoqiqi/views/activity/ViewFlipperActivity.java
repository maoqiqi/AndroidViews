package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * ViewFlipper
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/4 15:18
 */
public class ViewFlipperActivity extends BaseActivity {

    private int[] imageIds = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.view_flipper);
        setSupportActionBar(toolbar);

        ViewFlipper viewFlipper = findViewById(R.id.view_flipper);

        for (int imageId : imageIds) {
            ImageView imageView = (ImageView) View.inflate(this, R.layout.item_image_switcher, null);
            imageView.setImageResource(imageId);
            viewFlipper.addView(imageView);
        }
    }
}