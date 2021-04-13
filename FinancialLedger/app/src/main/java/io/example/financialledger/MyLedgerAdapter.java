package io.example.financialledger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyLedgerAdapter extends BaseAdapter {

    ArrayList<TransactionalInfo> list;
    LayoutInflater inflater = null;

    public MyLedgerAdapter(ArrayList<TransactionalInfo> list, LayoutInflater inflater) {
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
        TextViewHolder holder;
        if (convertView == null) {
            final Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.item_transactionalinfo, parent, false);
            holder = new TextViewHolder();
            holder.date = (TextView)convertView.findViewById(R.id.item_date);
            holder.desc = (TextView)convertView.findViewById(R.id.item_desc);
            holder.incomeExpenditure = (TextView)convertView.findViewById(R.id.item_incomeExpenditure);
            holder.money = (TextView)convertView.findViewById(R.id.item_money);
            convertView.setTag(holder);
        } else {
            holder = (TextViewHolder) convertView.getTag();
        }
        holder.date.setText(list.get(position).getDate());
        holder.desc.setText(list.get(position).getDesc());
        holder.incomeExpenditure.setText(list.get(position).getIncomeExpenditure());
        holder.money.setText(list.get(position).getMoney());
        return convertView;
    }
}
