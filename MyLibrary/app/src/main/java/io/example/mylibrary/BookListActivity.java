package io.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    ArrayList<BookDTO> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "BookListActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        setTitle(getResources().getString(R.string.book_search_result));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list, getLayoutInflater());
        ((ListView)findViewById(R.id.bookListActivity_lv_ListView)).setAdapter(myAdapter);
        // --------------------
        // 아이템 클릭 시
        // --------------------
        ((ListView)findViewById(R.id.bookListActivity_lv_ListView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("debug", "BookListActivity.ListView.onItemClick()\n position : " + position);
                ListView listView = (ListView) parent;
                BookDTO dto = (BookDTO) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), BookDetailInfoActivity.class);
                intent.putExtra("item", dto);
                startActivity(intent);
            }
        });
        refreshAll();
    }

    @Override
    protected void onResume() {
        Log.d("debug", "BookListActivity.onResume()");
        refreshAll();
        super.onResume();
    }

    private void refreshAll() {
        Log.d("debug", "BookListActivity.refreshAll()");
        syncList(getBookList());
        myAdapter.notifyDataSetChanged();
    }

    private Cursor getBookList() {
        Log.d("debug", "BookListActivity.getBookList()");
        String[] columns = new String[] {"_No", "Title", "Author", "Publisher", "Summary", "Rental"};
        return getContentResolver().query(MyContentProvider.URI, columns, null, null, null);
    }

    private void syncList(Cursor c) {
        Log.d("debug", "BookListActivity.syncList()");
        if (c != null) {
            list.clear();
            while(c.moveToNext()) {
                int no = c.getInt(0);
                String title = c.getString(1);
                String author = c.getString(2);
                String publisher = c.getString(3);
                String summary = c.getString(4);
                int rental = c.getInt(5);
                list.add(new BookDTO(no, title, author, publisher, summary, rental));
            }
        }
    }
}