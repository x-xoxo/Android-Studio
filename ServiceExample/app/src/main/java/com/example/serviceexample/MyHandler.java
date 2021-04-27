package com.example.serviceexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {
    private Messenger messenger;

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what)
        {
            case MyService.SEND_TO_ACTIVITY:        // Service -> Activity 에게 전달한 것
                messenger = msg.replyTo;
                Log.i("test", "SEND_TO_ACTIVITY");
                int data1 = msg.getData().getInt("data1");
                String data2 = msg.getData().getString("data2");
                Log.i("test", "data1 : " + data1 + ", data2 : " + data2);
                break;
            case MyService.SEND_TO_SERVICE:       // Activity -> Service 에게 전달한 것
                messenger = msg.replyTo;
                Log.i("test", "SEND_TO_SERVICE");
                Log.i("test", "Client message arg1 : " + msg.arg1);
                Log.i("test", "Client message obj string : " + msg.obj.toString());
                MyService.responseData(messenger, msg);
                break;
            default:
                super.handleMessage(msg);
        }
    }

}
