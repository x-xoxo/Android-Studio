package com.example.calcplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ListView lv_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("더하기 앱 계산기록");
        lv_history = (ListView)findViewById(R.id.lv_history);
        Intent intent = getIntent();
        ArrayList<CalcDTO> historyList = intent.getParcelableArrayListExtra("historyList");
        MyListAdapter myListAdapter = new MyListAdapter(historyList, getLayoutInflater());
        lv_history.setAdapter(myListAdapter);
    }
}