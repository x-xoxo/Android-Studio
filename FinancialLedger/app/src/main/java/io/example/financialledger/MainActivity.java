package io.example.financialledger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView ledger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Financial Ledger");
        ledger = (ListView) findViewById(R.id.listView);
        ArrayList<TransactionalInfo> financialLedger;
        String[] date = {"2021.04.10","2021.04.11","2021.04.12","2021.04.13","2021.04.14"};
        String[] desc = {"용돈","식사","교통비","월급","통신비"};
        String[] incomeExpenditure = {"수입","지출"};
        String[] money = {"20000KRW","8000KRW","84000KRW","2800000KRW","50000KRW"};
        Random r = new Random();
        financialLedger = new ArrayList<>();
        for (int i=0; i<50; ++i)
        {
            financialLedger.add(new TransactionalInfo(date[r.nextInt(5)],desc[r.nextInt(5)],incomeExpenditure[r.nextInt(2)],money[r.nextInt(5)]));
        }
        MyLedgerAdapter myLedgerAdapter = new MyLedgerAdapter(financialLedger, getLayoutInflater());
        ledger.setAdapter(myLedgerAdapter);
        ledger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("onItemClick", position+":"+financialLedger.get(position).getDesc());
                ListView listView = (ListView) parent;
                TransactionalInfo dto = (TransactionalInfo)listView.getItemAtPosition(position);
                String desc = dto.getDesc();
                String date = dto.getDate();
                String incomeExpenditure = dto.getIncomeExpenditure();
                String money = dto.getMoney();
                Log.i("onItemClick", date+", "+desc+", "+incomeExpenditure+", "+money);
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("item", dto);
                // startActivity(intent); // result 를 받지 않는 호출
                startActivityForResult(intent, CodeHolder.REQ_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeHolder.REQ_CODE) {
            if (resultCode == CodeHolder.RES_CODE) {
                String result = data.getStringExtra("result");
                Log.i("onActivityResult", result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}