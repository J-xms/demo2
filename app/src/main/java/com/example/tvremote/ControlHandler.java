package com.example.tvremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ControlHandler {
    /**
     * 作用1：实现广播接收器，因为每个广播都有独立功能版块，处理广播传递的消息，调用广播对应的功能版块
     * 作用2：处理每个广播内容，调用改功能版块的具体功能点
     *
     *
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
            }else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 判断接收到的广播是否是屏幕被关闭的系统广播
                // 屏幕被关闭时，要启动的功能点
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

    /**
     * 当接收到屏幕被打开的广播时，执行的业务逻辑集合
     * @param context
     * @param intent
     */
    public static void screenOn(Context context, Intent intent){ // 处理广播携带的具体内容，调用具体的功能点
        String code = intent.getStringExtra("keyCode"); // 获取广播携带的Extra信息（key-value形式），这里就是获取keyCode的值
        int a = intent.getIntExtra("key2",0);
        // 根据keyCode值，执行具体的功能点
        switch (code){
            case KeyCode.GET_SCREEN_BRIGHTNESS: // 判断keyCode值是否等于 GET_SCREEN_BRIGHTNESS，若等于就执行获取屏幕亮度功能
                Log.d("ControlHandler", "启动screenOn里面的获取屏幕亮度功能 ");
                 int value = ControlManger.getScreenBrightness(context);
                 // 根据value 做一些事情
                break;
            default:
                Log.d("ControlHandler", "错误的keyCode");
                break;
        }
    }

}
