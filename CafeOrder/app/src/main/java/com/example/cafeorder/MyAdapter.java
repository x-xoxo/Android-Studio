package com.example.cafeorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<OrderDTO> list;
    LayoutInflater inflater;

    public MyAdapter(ArrayList<OrderDTO> list, LayoutInflater inflater) {
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
        ViewHolder holder;
        if (convertView == null) {
            final Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder();
            holder.tv_num = (TextView)convertView.findViewById(R.id.tv_num);
            holder.tv_menu = (TextView)convertView.findViewById(R.id.tv_menu);
            holder.tv_amount = (TextView)convertView.findViewById(R.id.tv_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_num.setText(list.get(position).getPosition()+"번");
        holder.tv_menu.setText(list.get(position).getMenu());
        holder.tv_amount.setText(list.get(position).getAmount()+"개");
        return convertView;
    }
}
