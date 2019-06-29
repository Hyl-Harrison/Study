package com.hyl.camerademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hyl.camerademo.common.BaseActivity;

/**
 * @author yunlai.hao
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void init() {

    }

    /**
     * 处理网络状态结果
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);
    }
}
