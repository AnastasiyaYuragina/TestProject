package com.anastasiyayuragina.sqlitetransaction;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private final String LOG_TAG = "myLogs";
    private DBHelper helper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "--- onCreate Activity ---");
        helper = new DBHelper(this);
        myAction();
    }

    private void myAction() {
        try {
            database = helper.getWritableDatabase();
            delete(database, "mytable");

            database.beginTransaction();
            insert(database, "mytable", "val1");

            database.setTransactionSuccessful();
            database.endTransaction();

            read(database, "mytable");
            database.close();

        } catch (Exception ex) {
            Log.d(LOG_TAG, ex.getClass() + " error: " + ex.getMessage());
        }
    }

    private void insert(SQLiteDatabase sqLiteDatabase, String table, String value) {
        Log.d(LOG_TAG, "Insert in table " + table + "value = " + value);
        ContentValues contentValues = new ContentValues();
        contentValues.put("val", value);
        sqLiteDatabase.insert(table, null, contentValues);
    }

    private void read(SQLiteDatabase sqLiteDatabase, String table) {
        Log.d(LOG_TAG, "Read table " + table);
        Cursor cursor = sqLiteDatabase.query(table, null, null, null, null, null, null);
        if (cursor != null) {
            Log.d(LOG_TAG, "Records count = " + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    Log.d(LOG_TAG, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    private void delete(SQLiteDatabase sqLiteDatabase, String table) {
        Log.d(LOG_TAG, "Delete all from table " + table);
        sqLiteDatabase.delete(table, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            sqLiteDatabase.execSQL("create table mytable (" + "id integer primary key autoincrement," + "val text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
