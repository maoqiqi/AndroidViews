package com.codearms.maoqiqi.views.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * ZoomControls
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/9/6 17:12
 */
public class ZoomControlsActivity extends BaseActivity {

    private Bitmap bitmap;
    private float scaleWidth = 1;
    private float scaleHeight = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_controls);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.zoom_controls);
        setSupportActionBar(toolbar);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banner_1);

        final ImageView imageView = findViewById(R.id.iv_image);
        ZoomControls zoomControls = findViewById(R.id.zoom_controls);
        // 是否允许放大
        zoomControls.setIsZoomInEnabled(true);
        // 是否允许缩小
        zoomControls.setIsZoomOutEnabled(true);
        // 注册放大监听器
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmpWidth = bitmap.getWidth();
                int bmpHeight = bitmap.getHeight();
                // 设置图片放大但比例
                double scale = 1.25;
                // 计算这次要放大的比例
                scaleWidth = (float) (scaleWidth * scale);
                scaleHeight = (float) (scaleHeight * scale);
                // 产生新的大小但Bitmap对象
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
                imageView.setImageBitmap(resizeBmp);
            }
        });
        // 注册缩小监听器
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bmpWidth = bitmap.getWidth();
                int bmpHeight = bitmap.getHeight();
                // 设置图片放大但比例
                double scale = 0.8;
                // 计算这次要放大的比例
                scaleWidth = (float) (scaleWidth * scale);
                scaleHeight = (float) (scaleHeight * scale);
                // 产生新的大小但Bitmap对象
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
                imageView.setImageBitmap(resizeBmp);
            }
        });
    }
}