package com.example.storeservice;

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
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    TextView tv_detailAmountLeft, tv_detailDesc;
    EditText et_detailAmount;
    Button btn_detailSell;
    MyService myService;
    Intent svcIntent;
    Intent resIntent;
    ItemDTO dto;
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
        setContentView(R.layout.activity_detail);
        tv_detailAmountLeft = (TextView)findViewById(R.id.tv_detailAmountLeft);
        tv_detailDesc = (TextView)findViewById(R.id.tv_detailDesc);
        et_detailAmount = (EditText)findViewById(R.id.et_detailAmount);
        btn_detailSell = (Button)findViewById(R.id.btn_detailSell);
        Intent intent = getIntent();
        dto = intent.getParcelableExtra("item");
        setTitle(dto.getName());
        tv_detailAmountLeft.setText(dto.getAmount()+"개");
        svcIntent = new Intent(getApplicationContext(), MyService.class);
        bindService(svcIntent, conn, Context.BIND_AUTO_CREATE);
        resIntent = new Intent();
        btn_detailSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService == null) {
                    Toast.makeText(getApplicationContext(), "No Service Connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                String inputAmount = et_detailAmount.getText().toString();
                if (inputAmount.isEmpty()||!inputAmount.matches("^[0-9]*$")) {
                    Toast.makeText(getApplicationContext(), "Integer Number Only", Toast.LENGTH_SHORT).show();
                    return;
                }
                int x = Integer.parseInt(tv_detailAmountLeft.getText().toString().replaceAll("개", ""));
                if (x <= 0) {
                    Toast.makeText(getApplicationContext(), "매진된 상품입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int y = Integer.parseInt(inputAmount);
                int result = myService.getSub(x, y);
                if (result >= 0)
                {
                    et_detailAmount.setText("");
                    tv_detailAmountLeft.setText(result+"개");
                    dto.setAmount(result);
                    tv_detailDesc.setText(y + "개 판매 완료");
                    resIntent.putExtra("result", dto);
                    setResult(CodeHolder.RES_CODE, resIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "잘못된 수량입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}