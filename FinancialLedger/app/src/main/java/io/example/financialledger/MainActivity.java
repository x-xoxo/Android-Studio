package io.example.financialledger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
    }
}