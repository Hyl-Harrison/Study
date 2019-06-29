package com.hyl.camerademo.common;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hyl.camerademo.common.util.ActivityUtil;

/**
 * @author yunlai.hao
 * @date 2019/6/29
 */
public abstract class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetChangeListener {

    /**
     * 网络状态改变监听事件
     */
    public static NetBroadcastReceiver.NetChangeListener netEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        //沉浸效果
        //1、透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //2、透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        // 添加到Activity工具类
        ActivityUtil.getInstance().addActivity(this);

        // 初始化netEvent
        netEvent = this;

        // 执行初始化方法
        init();

    }

    /**
     * 抽象 - 初始化方法，可以对数据进行初始化
     */
    protected abstract void init();

    @Override
    protected void onResume() {
        super.onResume();
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        netEvent = null;
        // 移除Activity
        ActivityUtil.getInstance().removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityUtil.getInstance().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
