package com.example.calcplus;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_num1, et_num2;
    TextView tv_result;
    Button btn_calc, btn_history;
    MyService myService;
    ArrayList<CalcDTO> historyList;
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
        setTitle("더하기 앱");
        historyList = new ArrayList<>();
        et_num1 = (EditText)findViewById(R.id.et_num1);
        et_num2 = (EditText)findViewById(R.id.et_num2);
        tv_result = (TextView)findViewById(R.id.tv_result);
        btn_calc = (Button)findViewById(R.id.btn_calc);
        btn_history = (Button)findViewById(R.id.btn_history);
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService == null)
                    return;
                if (et_num1.getText().toString().equals("")||
                        et_num2.getText().toString().equals(""))
                    return;
                int x = Integer.parseInt(et_num1.getText().toString());
                int y = Integer.parseInt(et_num2.getText().toString());
                int result = myService.getPlusResult(x, y);
                et_num1.setText("");
                et_num2.setText("");
                tv_result.setText(Integer.toString(result));
                historyList.add(new CalcDTO(x, y, result));
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), History.class);
                intent1.putParcelableArrayListExtra("historyList", historyList);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}