package io.example.toupper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_inputData, tv_outputData;
    EditText et_inputData;
    Button btn_submit;
    MyService myService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myService = myBinder.getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("UpperCase Converter");
        tv_inputData = (TextView)findViewById(R.id.tv_inputData);
        tv_outputData = (TextView)findViewById(R.id.tv_outputData);
        et_inputData = (EditText)findViewById(R.id.et_inputData);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_inputData.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "텍스트를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if (et_inputData.getText().toString().equals("hello")) {
                        startActivity(new Intent(getApplicationContext(), HiddenEvent.class));
                    } else {
                        Thread th = new Thread(myService);
                        th.start();
                        String oldStr = et_inputData.getText().toString();
                        String upperStr = myService.toUpper(oldStr);
                        tv_inputData.setText(oldStr);
                        tv_outputData.setText(upperStr);
                        et_inputData.setText("");
                    }
                }
            }
        });
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}