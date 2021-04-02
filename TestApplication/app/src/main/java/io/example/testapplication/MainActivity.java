package io.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CallBack", "------ onCreate Method has called");
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CallBack", "------ onStart Method has called");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CallBack", "------ onReStart Method has called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CallBack", "------ onResume Method has called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CallBack", "------ onPause Method has called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CallBack", "------ onStop Method has called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CallBack", "------ onDestroy Method has called");
    }
}