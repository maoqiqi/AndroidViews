package com.codearms.maoqiqi.views.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtils {

    public static void setFullscreen(Activity activity) {
        Window window = activity.getWindow();
        // View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN:Activity全屏显示,但是状态栏不会被覆盖掉,而是正常显示,只是Activity顶端布局会被覆盖住
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        // 不设置会有半透明阴影
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置透明状态栏后,会有颜色,再次设置颜色
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}