package io.example.financialledger;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private Thread t;   // thread 를 참조할 변수
    private int data;
    IBinder iBinder = new MyBinder();
    public class MyBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }
    public int getData() {
        return data;
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", "onBind Method Called.");
        if (t == null) { // thread 가 생성되어있지 않을 시 새로운 thread 생성
            t = new Thread("My Thread") {
                @Override
                public void run() {
                    for (data=1; data<=10;) {
                        try {
                            Thread.sleep(1000);
                            Log.i("Thread", ""+(data++));
                            if (data == 11)
                            {
                                data = 10;
                                break;
                            }
                        } catch (InterruptedException e) {
                            Log.e("InterruptedException", e.getMessage());
                        }
                    }
                    super.run();
                }
            };
            t.start(); // thread 실행 명령
        }
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService", "onUnbind Method Called.");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("MyService", "onRebind Method Called.");
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        Log.i("MyService", "onCreate Method Called.");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("MyService", "onStart Method Called.");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "onStartCommand Method Called.");
        if (t == null) { // thread 가 생성되어있지 않을 시 새로운 thread 생성
            t = new Thread("My Thread") {
                @Override
                public void run() {
                    for (int i=0; i<10; i++) {
                        try {
                            Thread.sleep(1000);
                            Log.i("Thread", (i+1)+"");
                        } catch (InterruptedException e) {
                            Log.e("InterruptedException", e.getMessage());
                        }
                    }
                    super.run();
                }
            };
            t.start(); // thread 실행 명령
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("MyService", "onDestroy Method Called.");
        if (t != null) {
            t.interrupt();
            t = null;
        }
        super.onDestroy();
    }

}