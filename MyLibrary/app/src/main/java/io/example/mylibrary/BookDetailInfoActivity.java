package io.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
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
                ContentValues values = new ContentValues();
                rental = 1;
                values.put("Rental", rental);
                String selection = "_No = ?";
                String[] selectionArgs = new String[] {String.valueOf(num)};
                getContentResolver().update(MyContentProvider.URI, values, selection, selectionArgs);
                tv_rental.setText(getResources().getString(R.string.rent_no));
                btn_borrow.setEnabled(false);
                btn_return.setEnabled(true);
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rental == 0)
                    return;
                ContentValues values = new ContentValues();
                rental = 0;
                values.put("Rental", rental);
                String selection = "_No = ?";
                String[] selectionArgs = new String[] {String.valueOf(num)};
                getContentResolver().update(MyContentProvider.URI, values, selection, selectionArgs);
                tv_rental.setText(getResources().getString(R.string.rent_yes));
                btn_borrow.setEnabled(true);
                btn_return.setEnabled(false);
            }
        });
    }
}