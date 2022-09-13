package com.example.tvremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ControlHandler {

    static class xReceiver extends BroadcastReceiver {
        private String TAG = "ControlReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            // 实现接收到系统广播时，启动相应业务功能的逻辑
            String action = intent.getAction();
            if(Intent.ACTION_SCREEN_ON.equals(action)){
                // 屏幕被打开时，要启动的功能点
                ControlHandler.screenOn(context, intent);
                Log.d(TAG, action);
            }else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                // 屏幕被关闭时，要启动的功能点
                Log.d(TAG, action);
            }else if("Intent.ACTION_SCREEN_ON".equals(action)){
                // 屏幕被打开时，要启动的功能点
                ControlHandler.screenOn(context, intent);
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
    public static void screenOn(Context context, Intent intent){
        String code = intent.getStringExtra("keyCode");
        switch (code){
            case KeyCode.GET_SCREEN_BRIGHTNESS:
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
