package com.example.contentprovidertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class TelephoneBookDBManager extends SQLiteOpenHelper {

    static final String TELEPHONEBOOK_DB = "TelephoneBook.db";
    static final String TELEPHONEBOOK_TABLE = "TelephoneBook";

    private static TelephoneBookDBManager dbManager = null;
    Context context = null;
    static final String CREATE_TABLE =
            "create table " + TELEPHONEBOOK_TABLE
            + "(num integer primary key autoincrement,"
            + " name text not null,"
            + " phone text not null,"
            + " address text not null);";

    public static TelephoneBookDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new TelephoneBookDBManager(context, TELEPHONEBOOK_DB, null, 1);
        }
        Log.i("debug", "TelephoneBookDBManager.getInstance()");
        return dbManager;
    }

    public TelephoneBookDBManager(@Nullable Context context, @Nullable String name,
                                  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i("debug", "TelephoneBookDBManager.onOpen()");
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("debug", "TelephoneBookDBManager.onCreate()");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("debug", "TelephoneBookDBManager.onUpgrade()");
    }

    public long insert(ContentValues addValue) {
        Log.i("debug", "TelephoneBookDBManager.insert()");
        return getWritableDatabase().insert(TELEPHONEBOOK_TABLE, null, addValue);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy,
                        String having, String sortOrder) {
        return getReadableDatabase().query(TELEPHONEBOOK_TABLE, columns, selection, selectionArgs, groupBy, having, sortOrder);
    }
}
