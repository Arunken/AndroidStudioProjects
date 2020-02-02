package com.example.kenrig.a7_contentproviderresolverextended;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.HashMap;


public class ContactProvider extends ContentProvider {

    static final String PROVIDER_NAME="com.example.kenrig.a7_contentproviderresolverextended.ContactProvider";
    static final String URL = "content://"+PROVIDER_NAME+"/cpcontacts";
    static final Uri CONTENT_URL = Uri.parse(URL);

    static final  String id = "id";
    static final String name = "name";
    static final int uriCode = 1;

    private static HashMap<String,String> values;

    static  final UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"cpcontacts",uriCode);
    }

    private SQLiteDatabase sqldb;
    static final String DATABASE_NAME = "myContacts";
    static final String TABLE_NAME = "names";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = "CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)";
    @Override
    public boolean onCreate() {

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        sqldb = dbHelper.getWritableDatabase();

        if(sqldb !=null)
        {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        switch(uriMatcher.match(uri))
        {
            case uriCode:
                queryBuilder.setProjectionMap(values);
                break;
            default:
                throw  new IllegalArgumentException("Unknown URI "+uri);
        }

        Cursor cursor = queryBuilder.query(sqldb,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri))
        {
            case uriCode:
                return  "vnd.android.cursor.dir/cpcontacts";


                default:
                    throw  new IllegalArgumentException("Unsupported URI "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowID = sqldb.insert(TABLE_NAME,null,values);

        if(rowID>0)
        {
            Uri nuri = ContentUris.withAppendedId(CONTENT_URL,rowID);
            getContext().getContentResolver().notifyChange(nuri,null);
            return nuri;
        }
        else
            {
                Toast.makeText(getContext(),"Row Insert Failed",Toast.LENGTH_SHORT).show();
                return null;
            }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted = 0;

        switch (uriMatcher.match(uri))
        {
            case uriCode:
                rowsDeleted = sqldb.delete(TABLE_NAME,selection,selectionArgs);
                break;

            default:
                throw  new IllegalArgumentException("Unsupported URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;

        switch (uriMatcher.match(uri))
        {
            case uriCode:
                rowsUpdated= sqldb.update(TABLE_NAME,values,selection,selectionArgs);
                break;

            default:
                throw  new IllegalArgumentException("Unsupported URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        DatabaseHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
             db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
}
