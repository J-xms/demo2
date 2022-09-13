package com.example.tvremote;


import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class ControlManger {
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
}
