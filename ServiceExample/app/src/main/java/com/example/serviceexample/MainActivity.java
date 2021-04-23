package com.example.serviceexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btn1;
    Messenger serviceMessenger;
    boolean isBind;
    final Messenger activityMessenger = new Messenger(new ActivityHandler());

    class ActivityHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case MyService.SEND_TO_ACTIVITY:        // Service -> Activity 에게 전달한 것
                    Log.i("test", "SEND_TO_ACTIVITY");
                    int data1 = msg.getData().getInt("data1");
                    String data2 = msg.getData().getString("data2");
                    Log.i("test", "data1 : " + data1 + ", data2 : " + data2);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("test", "onServiceConnected");
            serviceMessenger = new Messenger(service);
            Message msg = Message.obtain(null, MyService.REGISTER_CLIENT);
            msg.replyTo = activityMessenger;
            try {
                serviceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    void requestData(String str) {
        if (isBind) {
            if (serviceMessenger != null) {
                Message msg = Message.obtain(null, MyService.SEND_TO_SERVICE, str);
                msg.arg1 = 100;
                msg.replyTo = activityMessenger;
                try {
                    serviceMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.tv_1);
        btn1 = (Button)findViewById(R.id.btn_1);

        Intent intent = new Intent(getApplicationContext(), MyService.class);

        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData("request data");
            }
        });
    }
}