package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyService extends Service {
    public static final int REGISTER_CLIENT = 1;
    public static final int SEND_TO_SERVICE = 2;    // 요청
    public static final int SEND_TO_ACTIVITY = 3;   // 응답
    private Messenger messenger;
    final Messenger serviceMessenger = new Messenger(new ServiceHandler());

    public MyService() {
    }

    class ServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case REGISTER_CLIENT:       // Activity 와 Service 가 바인딩했을 때
                    Log.i("test", "REGISTER_CLIENT");
                    Log.i("test", "Client Connected.");
                    messenger = msg.replyTo;
                    break;
                case SEND_TO_SERVICE:       // Activity -> Service 에게 전달한 것
                    Log.i("test", "SEND_TO_SERVICE");
                    Log.i("test", "Client message arg1 : " + msg.arg1);
                    Log.i("test", "Client message obj string : " + msg.obj.toString());
                    responseData(msg);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    void responseData(Message msg) {
        Bundle bundle = new Bundle();
        bundle.putInt("data1", 1234);
        bundle.putString("data2", "Hello World!");
        msg = Message.obtain(null, SEND_TO_ACTIVITY);
        msg.setData(bundle);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serviceMessenger.getBinder();
    }
}