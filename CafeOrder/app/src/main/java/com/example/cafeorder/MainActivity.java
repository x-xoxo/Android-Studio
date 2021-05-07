package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText
        et_menu,
        et_amount;
    Button
        btn_submit;
    ListView
        lv_order_list;
    ArrayList<OrderDTO> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cafe 주문");
        et_menu = (EditText) findViewById(R.id.et_menu);
        et_amount = (EditText) findViewById(R.id.et_amount);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        lv_order_list = (ListView) findViewById(R.id.lv_order_list);
        list = new ArrayList<>();
        syncList(getOrderList());
        myAdapter = new MyAdapter(list, getLayoutInflater());
        lv_order_list.setAdapter(myAdapter);
        lv_order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                OrderDTO dto = (OrderDTO) listView.getItemAtPosition(position);
                dto.setPosition(position);
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("item", dto);
                startActivity(intent);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_amount.getText().toString().isEmpty()||!et_amount.getText().toString().matches("^[0-9]*$")) {
                    Toast.makeText(getApplicationContext(), "Integer Number Only", Toast.LENGTH_SHORT).show();
                    return;
                }
                String menu = et_menu.getText().toString();
                if (menu.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "잘못된 입력입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int amount = Integer.parseInt(et_amount.getText().toString());
                addOrder(menu, amount);
                syncList(getOrderList());
                clearText();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        syncList(getOrderList());
        clearText();
        myAdapter.notifyDataSetChanged();
        super.onResume();
    }

    private void addOrder(String menu, int amount) {
        ContentValues addValue = new ContentValues();
        addValue.put("menu", menu);
        addValue.put("amount", amount);
        getContentResolver().insert(MyContentProvider.URI, addValue);
    }
    private Cursor getOrderList() {
        String[] columns = new String[] {"num", "menu", "amount"};
        return getContentResolver().query(MyContentProvider.URI, columns, null, null, null);
    }
    private void syncList(Cursor c) {
        if (c != null) {
            list.clear();
            while(c.moveToNext()) {
                int num = c.getInt(0);
                String menu = c.getString(1);
                int amount = c.getInt(2);
                list.add(new OrderDTO(num, menu, amount));
            }
        }
    }
    private void clearText() {
        et_menu.setText("");
        et_amount.setText("");
    }
}