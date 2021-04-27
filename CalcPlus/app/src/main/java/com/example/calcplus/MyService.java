package com.example.calcplus;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    IBinder iBinder = new MyBinder();
    CalcPlus plus = new CalcPlus();

    public class MyBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }

    public MyService() {
    }

    public int getPlusResult(int x, int y)
    {
        return plus.add(x, y);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }
}