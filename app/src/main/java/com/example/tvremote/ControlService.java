package com.example.tvremote;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
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
    /**
     * 作用1：实现广播接收器，因为每个广播都有独立功能版块，处理广播传递的消息，调用广播对应的功能版块
     * 作用2：处理每个广播内容，调用改功能版块的具体功能点
     */
    static class xReceiver extends BroadcastReceiver {
        private String TAG = "ControlReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            // 实现接收到系统广播时，启动相应业务功能的逻辑
            String action = intent.getAction(); // 获取接收到的广播ID
            if(Intent.ACTION_SCREEN_ON.equals(action)){ // 判断接收到的广播是否是屏幕被打开的系统广播
                // 屏幕被打开时，要启动的功能点
                ControlHandler.screenOn(context, intent);
                Log.d(TAG, action);
            }else if ("MY.ACTION_AUDIO".equals(action)) { // 判断接收到的广播是否是 模拟媒体相关的广播
                // 启动媒体相关的功能集接口
                ControlHandler.mediaManger(context, intent);
                Log.d(TAG, action);
            }else if("MY.ACTION_SCREEN_ON".equals(action)){ // 判断接收到的广播是否是自定义的广播
                // 屏幕被打开时，要启动的功能点
                ControlHandler.screenOn(context, intent); // 调用自定义广播关联的功能版块（即和自定义广播有关的一堆功能集合）
                Log.d(TAG, action);
            }else {
                // 无效action
                Log.d(TAG, action);
            }
        }
    }

    private xReceiver xReceiver;
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
//        intentFilter.addAction(Intent.ACTION_SCREEN_OFF); // 屏幕被关闭时，系统会发送该广播
        intentFilter.addAction("MY.ACTION_SCREEN_ON"); // 模拟屏幕被打开的广播
        intentFilter.addAction("MY.ACTION_AUDIO"); // 模拟媒体相关的广播，
//        controlReceiver = new ControlReceiver();
        xReceiver = new xReceiver();
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