package io.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ListView calcHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Calculate History");
        calcHistory = (ListView) findViewById(R.id.calc_history);

        Intent intent = getIntent();

        // 1st way
        //String data = intent.getStringExtra("historyList");
        //Log.i("data", data);

        // 2nd way
        // ArrayList<History> historyList = (ArrayList<History>)intent.getSerializableExtra("historyList");

        // 3rd way
        ArrayList<History> historyList = intent.getParcelableArrayListExtra("historyList");
//        StringBuffer sb = new StringBuffer();
//        for (History ch : historyList) {
//            Log.i("historyList2", String.valueOf(ch));
//            sb.append(ch +"\n");
//        }
//        Log.i("historyList2", "***********************");
//        textView.setText(String.valueOf(sb));

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList);

        MyListAdapter myListAdapter = new MyListAdapter(historyList, this.getLayoutInflater());
        calcHistory.setAdapter(myListAdapter);

    }
}