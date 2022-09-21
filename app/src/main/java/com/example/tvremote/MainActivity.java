package com.example.tvremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 此部分调试用
 * 因为调试过程中，可能需要申请一些权限，只能手动点击弹窗确认
 * 所以需要一个可视化的用户界面来完成权限申请
 * 比如打开/关闭扬声器的功能的接口的权限
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sbt = findViewById(R.id.start_service);
        sbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 模拟其它应用或者系统拉起 ControlService
                Intent intent = new Intent(MainActivity.this , ControlService.class);
                startService(intent);
                Log.d("MainActivity" , "start ControlService");
            }
        });

    }

    public void t(){
        // 发一个自定义广播
        Intent intent = new Intent();
        // intent 就是一个消息载体，包含很多属性信息。我们现在主要用到action 和 extra 这两个属性
        intent.setAction("MY.ACTION_SCREEN_ON"); // 设置发送的是什么广播，action就是广播的标记ID
        intent.putExtra("keyCode", KeyCode.GET_SCREEN_BRIGHTNESS); // key-value 设置广播携带的内容
        intent.putExtra("key2",KeyCode.GET_SCREEN_BS );
        sendBroadcast(intent);
    }
}