package com.example.cafeorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class OrderDBManager extends SQLiteOpenHelper {

    static final String ORDER_DB = "OrderList.db";
    static final String ORDER_TABLE = "OrderList";

    private static OrderDBManager dbManager;
    Context context;
    static final String CREATE_TABLE =
            "create table " + ORDER_TABLE
            + "(num integer primary key autoincrement,"
            + " menu text not null,"
            + " amount integer not null);";

    public static OrderDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new OrderDBManager(context, ORDER_DB, null, 1);
        }
        return dbManager;
    }

    public OrderDBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insert(ContentValues addValue) {
        Log.i("debug", "OrderDBManager.insert()");
        return getWritableDatabase().insert(ORDER_TABLE, null, addValue);
    }
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy,
                        String having, String sortOrder) {
        Log.i("debug", "OrderDBManager.query()");
        return getReadableDatabase().query(ORDER_TABLE, columns, selection, selectionArgs, groupBy, having, sortOrder);
    }
}
