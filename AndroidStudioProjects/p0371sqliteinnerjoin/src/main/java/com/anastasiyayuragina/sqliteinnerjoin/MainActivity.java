package com.anastasiyayuragina.sqliteinnerjoin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private final String LOG_TAG = "myLogs";

    private int[] position_id = { 1, 2, 3, 4 };
    private String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    private int[] position_salary = { 15000, 13000, 10000, 8000 };

    private String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    private int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor;

        Log.d(LOG_TAG, "--- Table position ---");
        cursor = database.query("position", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- Table people ---");
        cursor = database.query("people", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- INNER JOIN with rawQuery---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        cursor = database.rawQuery(sqlQuery, new String[] {"12000"});
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "--- INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String columns[] = {"PL.name as Name", "PS.name as Position", "salary as Salary"};
        String selection = "salary < ?";
        String [] selectionArgs = {"12000"};
        cursor = database.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        helper.close();
    }

    private void logCursor(Cursor cursor) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String colName : cursor.getColumnNames()) {
                        str = str.concat(colName + " = " + cursor.getString(cursor.getColumnIndex(colName)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (cursor.moveToNext());
            }
        } else {
            Log.d(LOG_TAG, "Cursor is null");
        }
    }

    private class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            ContentValues contentValues = new ContentValues();
            sqLiteDatabase.execSQL("create table position (" + "id integer primary key," + "name text," + "salary integer" + ");");

            for (int i = 0; i < position_id.length; i++) {
                contentValues.clear();
                contentValues.put("id", position_id[i]);
                contentValues.put("name", position_name[i]);
                contentValues.put("salary", position_salary[i]);
                sqLiteDatabase.insert("position", null, contentValues);
            }

            sqLiteDatabase.execSQL("create table people (" + "id integer primary key," + "name text," + "posid integer" + ");");

            for (int i = 0; i < people_name.length; i++) {
                contentValues.clear();
                contentValues.put("name", people_name[i]);
                contentValues.put("posid", people_posid[i]);
                sqLiteDatabase.insert("people", null, contentValues);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
