package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

/**
 * 捐赠
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/23 15:50
 */
public class DonateActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private ImageView ivPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.donate);
        setSupportActionBar(toolbar);

        radioGroup = findViewById(R.id.radio_group);
        ivPay = findViewById(R.id.iv_pay);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_alipay) {
            ivPay.setImageResource(R.drawable.ic_alipay);
        } else {
            ivPay.setImageResource(R.drawable.ic_wxpay);
        }
    }
}
