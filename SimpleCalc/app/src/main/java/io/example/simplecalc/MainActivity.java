package io.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD = 0, SUB = 1, MUL = 2, DIV = 3;

    Button g_btnNum[] = new Button[10];
    Button g_btnOp[] = new Button[4];
    Button g_btnClear, g_btnClearEntry, g_btnBackspace, g_btnCalc, g_btnPosNeg, g_btnDot, g_btnHistory;
    TextView textView, sumTextView;
    boolean isResult;

    public void addOP(String op)
    {
        if (isResult) return; // 계산된 값이면 리턴
        String num1 = sumTextView.getText().toString();
        String num2 = getTextViewText();
        if (num1.equals(""))
        {
            sumTextView.setText(num2+op);
        }
        else
        {
            if (num1.contains("="))
            {
                sumTextView.setText(num2 + op);
            }
            else if (num1.contains("+")) {
                num1 = num1.substring(0, num1.length()-1);
                int no1 = Integer.parseInt(num1);
                int no2 = Integer.parseInt(num2);
                sumTextView.setText((no1 + no2) + op);
                setTextViewText(""+(no1 + no2));
            } else if (num1.contains("-")) {
                num1 = num1.substring(0, num1.length()-1);
                int no1 = Integer.parseInt(num1);
                int no2 = Integer.parseInt(num2);
                sumTextView.setText((no1 - no2) + op);
                setTextViewText(""+(no1 - no2));
            } else if (num1.contains("×")) {
                num1 = num1.substring(0, num1.length()-1);
                int no1 = Integer.parseInt(num1);
                int no2 = Integer.parseInt(num2);
                sumTextView.setText((no1 * no2) + op);
                setTextViewText(""+(no1 * no2));
            } else if (num1.contains("÷")) {
                num1 = num1.substring(0, num1.length()-1);
                int no1 = Integer.parseInt(num1);
                int no2 = Integer.parseInt(num2);
                sumTextView.setText((no1 / no2) + op);
                setTextViewText(""+(no1 / no2));
            } else {
                Log.w("addOP", "No operator found");
                return;
            }
        }
        isResult = true;
    }

    public void setTextViewText(String msg) {
        msg = msg.replace(",", "");
        StringBuffer sb = new StringBuffer();
        int n = msg.length();
        int cnt = 0;
        for (int i=n-1; i>=0; i--) {
            sb.append(msg.charAt(i));
            cnt++;
            if (n>3 && cnt%3==0 && i>0) {
                sb.append(',');
            }
        }
        String tmp = sb.reverse().toString();
        textView.setText(tmp);
    }
    public String getTextViewText() {
        String str = textView.getText().toString();
        str = str.replaceAll("[^0-9[-]]", "");
        return str;
    }
    public void Calc() {
        String num1 = sumTextView.getText().toString();
        String num2 = getTextViewText();
        if (num1.contains("=")) {
            String[] n = num1.split("=|\\+|-|×|÷");
            int len = n.length;
            if (num1.contains("+")) {
                sumTextView.setText(getTextViewText()+"+"+n[len-1]+"=");
                String[] n2 = sumTextView.getText().toString().split("=|\\+|-|×|÷");
                setTextViewText((Integer.parseInt(n2[0])+Integer.parseInt(n2[1]))+"");
            } else if (num1.contains("-")) {
                sumTextView.setText(getTextViewText()+"-"+n[len-1]+"=");
                String[] n2 = sumTextView.getText().toString().split("=|\\+|-|×|÷");
                setTextViewText((Integer.parseInt(n2[0])-Integer.parseInt(n2[1]))+"");
            } else if (num1.contains("×")) {
                sumTextView.setText(getTextViewText()+"×"+n[len-1]+"=");
                String[] n2 = sumTextView.getText().toString().split("=|\\+|-|×|÷");
                setTextViewText((Integer.parseInt(n2[0])*Integer.parseInt(n2[1]))+"");
            } else if (num1.contains("÷")) {
                sumTextView.setText(getTextViewText()+"÷"+n[len-1]+"=");
                String[] n2 = sumTextView.getText().toString().split("=|\\+|-|×|÷");
                setTextViewText((Integer.parseInt(n2[0])/Integer.parseInt(n2[1]))+"");
            } else {
                return;
            }
        } else if (num1.contains("+")) {
            num1 = num1.substring(0, num1.length()-1);
            int no1 = Integer.parseInt(num1);
            int no2 = Integer.parseInt(num2);
            sumTextView.setText(no1 + "+" + no2 + "=");
            setTextViewText(""+(no1 + no2));
        } else if (num1.contains("-")) {
            num1 = num1.substring(0, num1.length()-1);
            int no1 = Integer.parseInt(num1);
            int no2 = Integer.parseInt(num2);
            sumTextView.setText(no1 + "-" + no2 + "=");
            setTextViewText(""+(no1 - no2));
        } else if (num1.contains("×")) {
            num1 = num1.substring(0, num1.length()-1);
            int no1 = Integer.parseInt(num1);
            int no2 = Integer.parseInt(num2);
            sumTextView.setText(no1 + "×" + no2 + "=");
            setTextViewText(""+(no1 * no2));
        } else if (num1.contains("÷")) {
            num1 = num1.substring(0, num1.length()-1);
            int no1 = Integer.parseInt(num1);
            int no2 = Integer.parseInt(num2);
            sumTextView.setText(no1 + "÷" + no2 + "=");
            setTextViewText(""+(no1 / no2));
        } else {
            Log.w("Clac", "No operator found");
        }
    }

    public void clear() {
        setTextViewText("0");
        sumTextView.setText("");
    }

    @Override
    public void onClick(View v) {
        Log.i("Calculator", ((Button) findViewById(v.getId())).getText().toString());
        if (v.getId()==R.id.Clear||v.getId()==R.id.ClearEntry||
                v.getId()==R.id.BackSpace||v.getId()==R.id.Switch_Neg_Pos||
                v.getId()==R.id.Add||v.getId()==R.id.Sub||
                v.getId()==R.id.Mul||v.getId()==R.id.Div||v.getId()==R.id.Calc)
        {
            if (v.getId()==R.id.Calc) {

                Calc();

            } else if (v.getId()==R.id.Clear) {

                clear();

            } else if (v.getId()==R.id.ClearEntry) {

                if (sumTextView.getText().toString().contains("=")) {
                    clear();
                } else {
                    setTextViewText("0");
                }

            } else if (v.getId()==R.id.BackSpace) {

                int len = getTextViewText().length();
                if (len == 1) {
                    setTextViewText("0");
                } else {
                    setTextViewText(getTextViewText().substring(0,len-1));
                }

            } else if (v.getId()==R.id.Switch_Neg_Pos) {

                if (getTextViewText().equals("0")) return;

                if (getTextViewText().contains("-"))
                {
                    String tmp = getTextViewText().replace("-", "");
                    setTextViewText(tmp);
                } else {
                    setTextViewText("-"+getTextViewText());
                }

            } else if (v.getId()==R.id.Add) {

                addOP("+");

            } else if (v.getId()==R.id.Sub) {

                addOP("-");

            } else if (v.getId()==R.id.Mul) {

                addOP("×");

            } else if (v.getId()==R.id.Div) {

                addOP("÷");

            } else {
                Log.w("OnClick", "Unknown button");
            }
        }
        else
        {
            if (textView.getText().equals("0")) {
                if (v.getId()==R.id.Btn_Dot) {

                }
                else {
                    setTextViewText("");
                }
            }
            if (isResult) setTextViewText("");
            setTextViewText(textView.getText()+((Button) findViewById(v.getId())).getText().toString());
            isResult = false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        g_btnHistory = (Button) findViewById(R.id.Btn_history);
        /****************
         * Connect View *
         ****************/
        textView = (TextView) findViewById(R.id.Res_View);
        sumTextView = (TextView) findViewById(R.id.Sum_Res_View);
        /****************
         * Set Listener *
         ****************/
        for (int i=0; i<g_btnNum.length; i++)
        {
            g_btnNum[i].setOnClickListener(this);
        }
        for (int i=0; i<g_btnOp.length; i++)
        {
            g_btnOp[i].setOnClickListener(this);
        }
        g_btnClear.setOnClickListener(this);
        g_btnClearEntry.setOnClickListener(this);
        g_btnBackspace.setOnClickListener(this);
        g_btnPosNeg.setOnClickListener(this);
        g_btnDot.setOnClickListener(this);
        g_btnCalc.setOnClickListener(this);
        g_btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

}