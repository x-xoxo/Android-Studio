package io.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD = 0, SUB = 1, MUL = 2, DIV = 3;

    Button g_btnNum[] = new Button[10];
    Button g_btnOp[] = new Button[4];
    Button g_btnClear, g_btnClearEntry, g_btnBackspace, g_btnCalc, g_btnPosNeg, g_btnDot;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Calculator", ((Button) findViewById(v.getId())).getText().toString());
            }
        };
        /******************
        * Connect Buttons *
        ******************/
        g_btnNum[0] = (Button) findViewById(R.id.Btn_Zero);
        g_btnNum[1] = (Button) findViewById(R.id.Btn_One);
        g_btnNum[2] = (Button) findViewById(R.id.Btn_Two);
        g_btnNum[3] = (Button) findViewById(R.id.Btn_Three);
        g_btnNum[4] = (Button) findViewById(R.id.Btn_Four);
        g_btnNum[5] = (Button) findViewById(R.id.Btn_Five);
        g_btnNum[6] = (Button) findViewById(R.id.Btn_Six);
        g_btnNum[7] = (Button) findViewById(R.id.Btn_Seven);
        g_btnNum[8] = (Button) findViewById(R.id.Btn_Eight);
        g_btnNum[9] = (Button) findViewById(R.id.Btn_Nine);

        g_btnOp[ADD] = (Button) findViewById(R.id.Add);
        g_btnOp[SUB] = (Button) findViewById(R.id.Sub);
        g_btnOp[MUL] = (Button) findViewById(R.id.Mul);
        g_btnOp[DIV] = (Button) findViewById(R.id.Div);

        g_btnClearEntry = (Button) findViewById(R.id.ClearEntry);
        g_btnClear = (Button) findViewById(R.id.Clear);
        g_btnBackspace = (Button) findViewById(R.id.BackSpace);
        g_btnPosNeg = (Button) findViewById(R.id.Switch_Neg_Pos);
        g_btnDot = (Button) findViewById(R.id.Btn_Dot);

        g_btnCalc = (Button) findViewById(R.id.Calc);
        /****************
         * Connect View *
         ****************/
        textView = (TextView) findViewById(R.id.Res_View);
        /****************
         * Set Listener *
         ****************/
        for (int i=0; i<g_btnNum.length; i++)
        {
            g_btnNum[i].setOnClickListener(btnListener);
        }
        for (int i=0; i<g_btnOp.length; i++)
        {
            g_btnOp[i].setOnClickListener(btnListener);
        }
        g_btnClear.setOnClickListener(btnListener);
        g_btnClearEntry.setOnClickListener(btnListener);
        g_btnBackspace.setOnClickListener(btnListener);
        g_btnPosNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Calculator", ((Button) findViewById(v.getId())).getText().toString());
            }
        });
        g_btnDot.setOnClickListener(btnListener);
        g_btnCalc.setOnClickListener(btnListener);
    }

    @Override
    public void onClick(View v) {
        Log.i("Calculator", ((Button) findViewById(v.getId())).getText().toString());
    }
}