package io.example.mylibrary;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "io.example.mylibrary.MyContentProvider";
    static final String URL = "content://"+PROVIDER_NAME+"/Books";
    static final Uri URI = Uri.parse(URL);

    public LibraryDBManager dbManager;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d("debug", "MyContentProvider.delete()");
        return dbManager.delete(selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        // throw new UnsupportedOperationException("Not yet implemented");
        long rowID = dbManager.insert(values);
        Log.d("debug", "MyContentProvider.insert() : " + rowID);
        return uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Log.d("debug", "MyContentProvider.onCreate()");
        dbManager = LibraryDBManager.getInstance(getContext());
        return dbManager != null ? true : false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        // throw new UnsupportedOperationException("Not yet implemented");
        Log.d("debug", "MyContentProvider.query()");
        return dbManager.query(projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        // throw new UnsupportedOperationException("Not yet implemented");
        Log.d("debug", "MyContentProvider.update()");
        return dbManager.update(values, selection, selectionArgs);
    }
}