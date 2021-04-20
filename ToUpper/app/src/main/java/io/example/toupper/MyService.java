package io.example.toupper;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service implements Runnable {

    IBinder iBinder = new MyBinder();

    @Override
    public void run() {
        for (int i=0; i<10; i++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }

    public String toUpper(String str) {
        return str.toUpperCase();
    }

    public String toLower(String str) {
        return str.toLowerCase();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return iBinder;
    }
}