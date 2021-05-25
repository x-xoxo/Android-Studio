package io.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "MainActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // --------------------
        // 입력버튼 동작 구현
        // --------------------
        ((Button)findViewById(R.id.mainActivity_btn_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug", "MainActivity.btn_submit.onClick()");
                registerBook();
                Toast.makeText(getApplicationContext(), "Register Complete", Toast.LENGTH_SHORT).show();
            }
        });
        // --------------------
        // 검색버튼 동작 구현
        // --------------------
        ((Button)findViewById(R.id.mainActivity_btn_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug", "MainActivity.btn_search.onClick()");
                startActivity(new Intent(getApplicationContext(), BookListActivity.class));
            }
        });
    }

    private void registerBook() {
        Log.d("debug", "MainActivity.registerBook()");
        String title = ((TextView)findViewById(R.id.mainActivity_et_title)).getText().toString();
        String author = ((TextView)findViewById(R.id.mainActivity_et_author)).getText().toString();
        String publisher = ((TextView)findViewById(R.id.mainActivity_et_publisher)).getText().toString();
        String summary = ((TextView)findViewById(R.id.mainActivity_et_summary)).getText().toString();
        ContentValues addValues = new ContentValues();
        addValues.put("Title", title);
        addValues.put("Author", author);
        addValues.put("Publisher", publisher);
        addValues.put("Summary", summary);
        getContentResolver().insert(MyContentProvider.URI, addValues);
        clearText();
    }

    private void clearText() {
        Log.d("debug", "MainActivity.clearText()");
        ((TextView)findViewById(R.id.mainActivity_et_title)).setText("");
        ((TextView)findViewById(R.id.mainActivity_et_author)).setText("");
        ((TextView)findViewById(R.id.mainActivity_et_publisher)).setText("");
        ((TextView)findViewById(R.id.mainActivity_et_summary)).setText("");
    }
}