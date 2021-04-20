package io.example.financialledger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView tView_date;
    TextView tView_desc;
    TextView tView_incomeExpenditure;
    TextView tView_money;

    Button btn_exit;
    Button btn_svc_start;
    Button btn_svc_stop;
    Button btn_getData;

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
        setContentView(R.layout.activity_main2);
        tView_date = (TextView)findViewById(R.id.date2);
        tView_desc = (TextView)findViewById(R.id.desc2);
        tView_incomeExpenditure = (TextView)findViewById(R.id.incomeExpenditure2);
        tView_money = (TextView)findViewById(R.id.money2);

        btn_exit = (Button)findViewById(R.id.exit2);
        btn_svc_start = (Button)findViewById(R.id.btn_svc_start);
        btn_svc_stop = (Button)findViewById(R.id.btn_svc_stop);
        btn_getData = (Button)findViewById(R.id.btn_getData);

        Intent intent = getIntent();
        TransactionalInfo dto = (TransactionalInfo) intent.getSerializableExtra("item");
        tView_date.setText(dto.getDate());
        tView_desc.setText(dto.getDesc());
        tView_incomeExpenditure.setText(dto.getIncomeExpenditure());
        tView_money.setText(dto.getMoney());

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Hello";
                Intent intent = new Intent();
                intent.putExtra("result", msg);
                setResult(CodeHolder.RES_CODE, intent);
                finish();
            }
        });
        btn_svc_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MyService.class);
                // startService(intent1);
                bindService(intent1, conn, Context.BIND_AUTO_CREATE);
            }
        });
        btn_svc_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent1 = new Intent(getApplicationContext(), MyService.class);
                // stopService(intent1);
                unbindService(conn);
                myService = null;
            }
        });
        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService != null) {
                    int data = myService.getData();
                    Log.i("MyService", "getData : "+data);
                    Toast.makeText(getApplicationContext(), "getData : "+data, Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("MyService", "No Connection Detected.");
                    Toast.makeText(getApplicationContext(), "No Connection Detected.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}