package io.example.englishvoca;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ActivityStudy extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordstudy);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Sound ON");
        menu.add(0, 2, 0, "Sound OFF");
        menu.add(0, 3, 0, "Menu 3");
        menu.add(0, 4, 0, "Menu 4");
        return true;
    }

    //-------------------------------------
    //  onOptions ItemSelected
    //-------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                StudyView.soundOk=1;
                break;
            case 2:
                StudyView.soundOk=0;
                break;
            case 3:
            case 4:
                Toast.makeText(getApplicationContext(), item.getTitle().toString()+" Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode== KeyEvent.KEYCODE_BACK) {
            // System.exit(0);   //메인화면으로 돌아가기
            finish();
        }
        return false;
    }

}