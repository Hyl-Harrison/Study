package com.hyl.camerademo.common.util;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity工具类
 *
 * @author yunlai.hao
 * @date 2019/6/29
 */
public class ActivityUtil {
    private List<Activity> activityList = new ArrayList<>();
    private static ActivityUtil INSTANCE = null;

    /**
     * 单例模式中获取唯一的ExitApplication实例
     *
     * @return ActivityUtil实例
     */
    public static synchronized ActivityUtil getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ActivityUtil();
        }
        return INSTANCE;
    }

    /**
     * 添加Activity到容器中
     *
     * @param activity 需要添加的Activity
     */
    public void addActivity(Activity activity){
        if (activityList == null){
            activityList = new ArrayList<>();
        }
        activityList.add(activity);
    }

    /**
     * 移除Activity
     *
     * @param activity 需要移除的Activity
     */
    public void removeActivity(Activity activity){
        if (activityList != null){
            activityList.remove(activity);
        }
    }

    /**
     * 遍历所有Activity并finish
     */
    public void exitSystem(){
        for (Activity activity : activityList) {
            if (activity != null){
                activity.finish();
            }
        }

        // 退出进程
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
