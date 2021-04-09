package io.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    TextView num_ex, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        num_ex = (TextView) findViewById(R.id.history_numerical_expression);
        result = (TextView) findViewById(R.id.history_result);

        Intent intent = getIntent();

        // 1st way
        //String data = intent.getStringExtra("historyList");
        //Log.i("data", data);

        // 2nd way
        // ArrayList<History> historyList = (ArrayList<History>)intent.getSerializableExtra("historyList");

        // 3rd way
        ArrayList<History> historyList = intent.getParcelableArrayListExtra("historyList");
        StringBuffer sb = new StringBuffer();
        for (History ch : historyList) {
            Log.i("historyList2", String.valueOf(ch));
            sb.append(ch +"\n");
        }
        result.setText(sb.toString());
        Log.i("historyList2", "***********************");
    }
}