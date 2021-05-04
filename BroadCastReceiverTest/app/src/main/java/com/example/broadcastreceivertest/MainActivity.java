package com.example.broadcastreceivertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button
        button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test", "onClick");
                Intent intent = new Intent();
                intent.putExtra("data1", 100);
                intent.putExtra("data2", "hello world!");
                intent.setAction("com.example.broadcastreceivertest.abc");
                sendBroadcast(intent);
            }
        });
    }
}