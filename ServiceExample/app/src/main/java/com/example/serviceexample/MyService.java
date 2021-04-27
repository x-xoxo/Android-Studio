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
    public static final int SEND_TO_SERVICE = 1;    // 요청
    public static final int SEND_TO_ACTIVITY = 2;   // 응답
    final Messenger serviceMessenger = new Messenger(new MyHandler());

    public MyService() {
    }

    static void responseData(Messenger messenger, Message msg) {
        Bundle bundle = new Bundle();
        bundle.putInt("data1", 1234);
        bundle.putString("data2", "Hello World!");
        msg = Message.obtain(null, MyService.SEND_TO_ACTIVITY);
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