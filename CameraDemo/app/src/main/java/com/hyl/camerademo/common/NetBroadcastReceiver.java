package com.hyl.camerademo.common;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.hyl.camerademo.common.util.NetworkUtil;

/**
 * @author yunlai.hao
 * @date 2019/6/29
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        // 网络状态发生了变化
        boolean netWorkState = NetworkUtil.isNetworkConnected(context);
        // 接口回调传过去状态的类型
        if (BaseActivity.netEvent != null) {
            BaseActivity.netEvent.onNetChange(netWorkState);
        }
    }

    // 网络状态变化接口
    public interface NetChangeListener {
        void onNetChange(boolean netWorkState);
    }
}
