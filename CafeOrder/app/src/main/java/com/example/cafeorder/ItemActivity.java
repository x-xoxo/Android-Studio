package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    TextView
            tv_itemMenuName,
            tv_itemAmount,
            tv_receipt;
    Button
            btn_checkReceipt;
    MyService
            myService;
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
        setContentView(R.layout.activity_item);

        bindService(new Intent(getApplicationContext(), MyService.class), conn, Context.BIND_AUTO_CREATE);
        tv_itemMenuName = (TextView)findViewById(R.id.tv_itemMenuName);
        tv_itemAmount = (TextView)findViewById(R.id.tv_itemAmount);
        tv_receipt = (TextView)findViewById(R.id.tv_receipt);
        btn_checkReceipt = (Button)findViewById(R.id.btn_checkReceipt);

        Intent intent = getIntent();
        OrderDTO dto = (OrderDTO) intent.getParcelableExtra("item");
        String menuName = dto.getMenu();
        int amount = dto.getAmount();
        tv_itemMenuName.setText(menuName);
        tv_itemAmount.setText(amount+"개");

        btn_checkReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price;
                if (menuName.equalsIgnoreCase("americano")) {
                    price = 4500;
                } else if (menuName.equalsIgnoreCase("cafe latte")||menuName.equalsIgnoreCase("caffe latte")) {
                    price = 5500;
                } else if (menuName.equalsIgnoreCase("cappuccino")) {
                    price = 6000;
                } else {
                    price = 4000;
                }
                int totalPrice = myService.getTotalPrice(price, amount);
                tv_receipt.setText("가격 : " + price + "\n\n"
                + "수량 : " + amount + "\n\n" + "총 가격 : " + totalPrice);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}