package com.example.storeservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView lv_mainActivity;
    ArrayList<ItemDTO> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("과일 가게");
        lv_mainActivity = (ListView) findViewById(R.id.lv_mainActivity);
        list = new ArrayList<>();
        String[] name = {"사과", "배", "딸기", "수박", "멜론", "키위"};
        int[] amount = {10, 17, 20, 30, 35, 100};
        Random r = new Random();
        for (int i=0; i<12; i++) {
            list.add(new ItemDTO(name[r.nextInt(6)], amount[r.nextInt(6)]));
        }
        myAdapter = new MyAdapter(list, getLayoutInflater());
        lv_mainActivity.setAdapter(myAdapter);
        lv_mainActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ItemDTO dto = (ItemDTO) listView.getItemAtPosition(position);
                dto.setPosition(position);
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("item", dto);
                startActivityForResult(intent, CodeHolder.REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeHolder.REQ_CODE) {
            if (resultCode == CodeHolder.RES_CODE) {
                ItemDTO dto = (ItemDTO)data.getParcelableExtra("result");
                int position = dto.getPosition();
                list.get(position).setAmount(dto.getAmount());
                myAdapter.notifyDataSetChanged();
            }
        }
    }
}