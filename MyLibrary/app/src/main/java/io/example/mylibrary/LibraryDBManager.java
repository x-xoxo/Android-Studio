package io.example.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LibraryDBManager extends SQLiteOpenHelper {

    static final String LIB_DB = "LibraryDataBase.db";
    static final String LIB_TABLE = "Books";

    private static LibraryDBManager dbManager;

    static final String CREATE_TABLE =
            "CREATE TABLE " + LIB_TABLE
            + "(_No INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " Title TEXT NOT NULL,"
            + " Author TEXT NOT NULL,"
            + " Publisher TEXT NOT NULL,"
            + " Summary TEXT,"
            + " Rental INTEGER DEFAULT 0);";

    public static LibraryDBManager getInstance(Context context) {
        Log.d("debug", "LibraryDBManager.getInstance()");
        if (dbManager == null) {
            dbManager = new LibraryDBManager(context, LIB_DB, null, 1);
        }
        return dbManager;
    }

    public LibraryDBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("debug", "LibraryDBManager()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("debug", "LibraryDBManager.onCreate()");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("debug", "LibraryDBManager.onUpgrade()");
        db.execSQL("DROP TABLE IF EXISTS " + LIB_TABLE);
        onCreate(db);
    }

    public long insert(ContentValues addValue) {
        Log.d("debug", "LibraryDBManager.insert()");
        return getWritableDatabase().insert(LIB_TABLE, null, addValue);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy,
                        String having, String sortOrder) {
        Log.d("debug", "LibraryDBManager.query()");
        return getReadableDatabase().query(LIB_TABLE, columns, selection, selectionArgs, groupBy, having, sortOrder);
    }

    public int delete(String whereClause, String[] whereArgs) {
        Log.d("debug", "LibraryDBManager.delete()");
        return getWritableDatabase().delete(LIB_TABLE, whereClause, whereArgs);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        Log.d("debug", "LibraryDBManager.update()");
        return getWritableDatabase().update(LIB_TABLE, values, whereClause, whereArgs);
    }
}
