package io.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookDetailInfoActivity extends AppCompatActivity {

    TextView tv_title, tv_author, tv_publisher, tv_rental, tv_summary;
    Button btn_borrow, btn_return;

    int rental, num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_info);
        setTitle(getResources().getString(R.string.book_detail_info));

        tv_title = (TextView)findViewById(R.id.bookDetailInfoActivity_tv_title);
        tv_author = (TextView)findViewById(R.id.bookDetailInfoActivity_tv_author);
        tv_publisher = (TextView)findViewById(R.id.bookDetailInfoActivity_tv_publisher);
        tv_rental = (TextView)findViewById(R.id.bookDetailInfoActivity_tv_rental);
        tv_summary = (TextView)findViewById(R.id.bookDetailInfoActivity_tv_summary);

        btn_borrow = (Button)findViewById(R.id.bookDetailInfoActivity_btn_borrow);
        btn_return = (Button)findViewById(R.id.bookDetailInfoActivity_btn_return);

        Intent intent = getIntent();
        BookDTO dto = (BookDTO) intent.getParcelableExtra("item");
        num = dto.get_No();
        String title = dto.getTitle();
        String author = dto.getAuthor();
        String publisher = dto.getPublisher();
        rental = dto.getRental();
        String summary = dto.getSummary();

        tv_title.setText(title);
        tv_author.setText(author);
        tv_publisher.setText(publisher);
        tv_summary.setText(summary);
        tv_rental.setText((rental == 0) ? getResources().getString(R.string.rent_yes) : getResources().getString(R.string.rent_no));
        if (rental != 0)
        {
            btn_borrow.setEnabled(false);
            btn_return.setEnabled(true);
        } else {
            btn_borrow.setEnabled(true);
            btn_return.setEnabled(false);
        }
        btn_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rental != 0)
                    return;
                toggleRental();
                syncDB(getBookList());
                toggleButton();
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rental == 0)
                    return;
                toggleRental();
                syncDB(getBookList());
                toggleButton();
            }
        });
    }

    private void toggleButton()
    {
        btn_borrow.setEnabled(!btn_borrow.isEnabled());
        btn_return.setEnabled(!btn_return.isEnabled());
    }

    private void syncDB(Cursor c) {
        Log.d("debug", "BookListActivity.syncList()");
        if (c != null) {
            while(c.moveToNext()) {
                int no = c.getInt(0);
                String title = c.getString(1);
                String author = c.getString(2);
                String publisher = c.getString(3);
                String summary = c.getString(4);
                int rental = c.getInt(5);
                if (no == num)
                {
                    tv_title.setText(title);
                    tv_author.setText(author);
                    tv_publisher.setText(publisher);
                    tv_summary.setText(summary);
                    tv_rental.setText((rental == 0) ? getResources().getString(R.string.rent_yes) : getResources().getString(R.string.rent_no));
                    break;
                }
            }
        }
    }

    private Cursor getBookList() {
        Log.d("debug", "BookListActivity.getBookList()");
        String[] columns = new String[] {"_No", "Title", "Author", "Publisher", "Summary", "Rental"};
        return getContentResolver().query(MyContentProvider.URI, columns, null, null, null);
    }

    private void toggleRental() {
        if (rental == 0) rental = 1;
        else rental = 0;
        ContentValues values = new ContentValues();
        values.put("Rental", rental);
        String selection = "_No = ?";
        String[] selectionArgs = new String[] {String.valueOf(num)};
        getContentResolver().update(MyContentProvider.URI, values, selection, selectionArgs);
    }
}