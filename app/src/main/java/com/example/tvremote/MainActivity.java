package com.example.tvremote;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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
}