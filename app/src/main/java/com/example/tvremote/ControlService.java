package com.example.tvremote;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * 用于注册动态广播
 * 设置要接收的广播列表
 * 用到终端的哪些系统广播，就在这里注册
 * 由于有些系统广播需要权限（或不方便触发）
 * 所以我们选择使用自定义广播的方式调试功能
 */

public class ControlService extends Service {
    private ControlHandler.xReceiver xReceiver;
    public ControlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 动态广播在onCreate里面进行注册，service的启动逻辑里面，只有该service没有被销毁，onCreate只执行一次。
        // 因此比较满足我们的需求，同一个动态广播，只注册一次。

        IntentFilter intentFilter = new IntentFilter();
        // 设置系统广播action，所谓注册系统广播接收器，就是将action设置为系统广播的action即可
        // 可以同时设置多个action
        intentFilter.addAction(Intent.ACTION_SCREEN_ON); // 屏幕被打开时，系统会发送该广播
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF); // 屏幕被关闭时，系统会发送该广播
        intentFilter.addAction("MY.ACTION_SCREEN_ON"); // 模拟屏幕被打开时，系统会发送该广播（自定义广播）
//        controlReceiver = new ControlReceiver();
        xReceiver = new ControlHandler.xReceiver();
        registerReceiver(xReceiver, intentFilter);

        Log.d("ControlService", "registerReceiver ControlReceiver");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //避免oom等问题，动态广播使用完毕之后，需要销毁。这里使用service启动动态广播，所以将动态广播的生命周期和service一致
        unregisterReceiver(xReceiver);
    }
}