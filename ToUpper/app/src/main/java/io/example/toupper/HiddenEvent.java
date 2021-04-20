package io.example.toupper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HiddenEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_event);
        setTitle("Hidden Page");
    }
}