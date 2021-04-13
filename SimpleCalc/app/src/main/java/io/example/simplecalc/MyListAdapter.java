package io.example.simplecalc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    ArrayList<History> list;
    LayoutInflater inflater = null;

    public MyListAdapter(ArrayList<History> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            final Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.layout_list, parent, false);
        }
        TextView textView1 = (TextView)convertView.findViewById(R.id.text1);
        TextView textView2 = (TextView)convertView.findViewById(R.id.text2);
        textView1.setText(list.get(position).getNumericalExpression());
        textView2.setText(list.get(position).getResult());
        return convertView;
    }
}
