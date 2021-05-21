package io.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<BookDTO> list;
    LayoutInflater inflater;

    public MyAdapter(ArrayList<BookDTO> list, LayoutInflater inflater) {
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
            convertView = inflater.inflate(R.layout.booklist_item_layout, parent, false);
            holder = new ViewHolder();
            holder.tv_author = (TextView) convertView.findViewById(R.id.item_tv_author);
            holder.tv_publisher = (TextView) convertView.findViewById(R.id.item_tv_publisher);
            holder.tv_title = (TextView) convertView.findViewById(R.id.item_tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_publisher.setText(list.get(position).getPublisher());
        return convertView;
    }
}
