package com.example.tvremote;


import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class ControlManger { // 实现具体的功能逻辑

    /**
     * 获取屏幕亮度
     * @param context
     */
    public static int getScreenBrightness(Context context){
        int value =0;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            value = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
            Log.d("ControlManger",String.valueOf(value));
            Toast.makeText(context,"屏幕亮度："+value,Toast.LENGTH_LONG).show();
        } catch (Settings.SettingNotFoundException e) {
            Log.e("ControlManger",e.toString());
        }
        return value;
    }

    /**
     * 获取音量
     * @param context
     * @return
     */
    public static int getVolume(Context context) {
        AudioManager audioManager = (android.media.AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 设置静音
     * @param context
     * @return
     */
    public static void setMute(Context context) {
        Log.d("ControlManger", "设置静音");
    }

    /**
     * 关闭麦克风
     * @param context
     * @return
     */
    public static void setMikeOff(Context context) {
        Log.d("ControlManger", "关闭麦克风");
    }

    /**
     * 打开麦克风
     * @param context
     * @return
     */
    public static void setMikeOpen(Context context) {
        Log.d("ControlManger", "打开麦克风");
    }
}
