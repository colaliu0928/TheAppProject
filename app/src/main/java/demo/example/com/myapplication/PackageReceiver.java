package demo.example.com.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

/**
 * 类的作用：自定义广播监听APP的卸载动作
 * lenovo 刘珂珂
 * 2018/7/21
 * 15:40
 */

public class PackageReceiver  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // 监测到应用被安装
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            //发送消息
            EventBus.getDefault().post("App is already added...");
        }

        //监测到应用被移除
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            //发送消息
            EventBus.getDefault().post("App is already removed...");
        }

    }

}
