package com.example.cafeorder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    IBinder iBinder = new MyBinder();

    public MyService() {
    }

    public class MyBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return iBinder;
    }
    public int getTotalPrice(int price, int amount) {
        return price*amount;
    }
}