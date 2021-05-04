package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText
        et_name,
        et_phone,
        et_address;
    Button
        btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address = (EditText) findViewById(R.id.et_address);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String phone = et_phone.getText().toString();
                String address = et_address.getText().toString();
                clearText();
                Log.i("debug", "Name : "+name+", Phone : "+phone+", Address : "+address);
                addTelephoneBook(name, phone, address);
            }
        });
        ((Button)findViewById(R.id.btn_select)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTelephoneBook();
            }
        });
    }

    public void addTelephoneBook(String name, String phone, String address) {
        ContentValues addValue = new ContentValues();
        addValue.put("name", name);
        addValue.put("phone", phone);
        addValue.put("address", address);
        getContentResolver().insert(MyContentProvider.URI, addValue);
    }

    public void getTelephoneBook() {
        String[] columns = new String[] {"num", "name", "phone", "address"};
        Cursor c = getContentResolver().query(MyContentProvider.URI, columns, null, null, null);
        if (c != null) {
            StringBuffer sb = new StringBuffer();
            while(c.moveToNext()) {
                int num = c.getInt(0);
                String name = c.getString(1);
                String phone = c.getString(2);
                String address = c.getString(3);
                sb.append(num+" : "+name+" : "+phone+" : "+address+"\n");
            }
            Log.i("debug", sb.toString());
        }
    }

    private void clearText() {
        et_name.setText("");
        et_phone.setText("");
        et_address.setText("");
    }
}