package com.example.tvremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ControlHandler {

    /**
     * 当接收到屏幕被打开的广播时，执行的业务逻辑集合
     * @param context
     * @param intent
     */
    public static void screenOn(Context context, Intent intent){ // 处理广播携带的具体内容，调用具体的功能点
        String code = intent.getStringExtra("keyCode"); // 获取广播携带的Extra信息（key-value形式），这里就是获取keyCode的值
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


    public static void mediaManger(Context context, Intent intent){
        String code = intent.getStringExtra("keyCode"); // 获取广播携带的Extra信息（key-value形式），这里就是获取keyCode的值
        // 根据keyCode值，执行具体的功能点
        switch (code){
            case KeyCode.GET_VOLUME: // 判断keyCode值是否等于 GET_VOLUME，若等于就执行获取音量的功能
                Log.d("ControlHandler", "启动获取音量的功能 ");
                int value = ControlManger.getVolume(context);
                // 根据value 做一些事情
                break;
            case KeyCode.SET_MIKE_OFF:
                Log.d("ControlHandler", "启动 关闭麦克风功能 ");
                ControlManger.setMikeOff(context);
                break;
            case KeyCode.SET_MIKE_OPEN:
                Log.d("ControlHandler", "启动 打开麦克风功能 ");
                ControlManger.setMikeOpen(context);
                break;
            case KeyCode.SET_MUTE:
                Log.d("ControlHandler", "启动 静音功能 ");
                ControlManger.setMute(context);
                break;
            default:
                Log.d("ControlHandler", "错误的keyCode");
                break;
        }
    }
}