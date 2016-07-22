package com.anastasiyayuragina.sqliteonupgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String TAG = "myLogs";

    final String DB_NAME = "staff";
    final int DB_VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        Log.d(TAG, "--- Staff db v." + database.getVersion() + " ---");

        writeStaff(database);
        helper.close();
    }

    private void writeStaff(SQLiteDatabase liteDatabase) {
        Cursor cursor = liteDatabase.rawQuery("select * from people", null);
        logCursor(cursor, "Table people");
        cursor.close();

        cursor = liteDatabase.rawQuery("select * from position", null);
        logCursor(cursor, "Table position");
        cursor.close();

        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary " + "from people as PL " + "inner join position as PS " + "on PL.posid = PS.id ";
        cursor = liteDatabase.rawQuery(sqlQuery, null);
        logCursor(cursor, "inner join");
        cursor.close();
    }

    private void logCursor(Cursor cursor, String title){
        if (cursor != null) {
            if (cursor.moveToFirst()){
                Log.d(TAG, title + ". " + cursor.getCount() + " rows");
                StringBuilder builder = new StringBuilder();
                do {
                    builder.setLength(0);
                    for (String cn : cursor.getColumnNames()) {
                        builder.append(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(TAG, builder.toString());
                } while (cursor.moveToNext());
            }
        } else {
            Log.d(TAG, title +  ". Cursor is null");
        }
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "--- onCreate database ---");

            String [] peopleName = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь"};
            int [] peoplePosid = {2, 3, 2, 2, 3, 1, 2, 4};
            int [] positionId = {1, 2, 3, 4};
            String [] positionName = {"Директор", "Программер", "Бухгалтер", "Охранник" };

            int [] positionSalary = {15000, 13000, 10000, 8000};
            ContentValues contentValues = new ContentValues();

            sqLiteDatabase.execSQL("create table position (id integer primary key, name text, salary integer);");

            for (int i = 0; i < positionId.length; i++) {
                contentValues.clear();
                contentValues.put("id", positionId[i]);
                contentValues.put("name", positionName[i]);
                contentValues.put("salary", positionSalary[i]);
                sqLiteDatabase.insert("position", null, contentValues);
            }

            sqLiteDatabase.execSQL("create table people (" + "id integer primary key autoincrement," + "name text, posid integer);");

            for (int i = 0; i < peopleName.length; i++) {
                contentValues.clear();
                contentValues.put("name", peopleName[i]);
                contentValues.put("position", peoplePosid[i]);
                sqLiteDatabase.insert("people", null, contentValues);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.d(TAG, "--- onUpgrade database from " + oldVersion + " to " + newVersion + " version ---");

            if (oldVersion == 1 && newVersion == 2) {
                ContentValues contentValues = new ContentValues();

                int [] positionID = {1, 2, 3, 4};
                String [] positionName = {"Директор", "Программер", "Бухгалтер", "Охранник"};
                int [] positionSalary = {15000, 13000, 10000, 8000};

                sqLiteDatabase.beginTransaction();
                try {
                    sqLiteDatabase.execSQL("create table popsition (id integer primary key, name text, salary integer);");
                    for (int i = 0; i < positionID.length; i++) {
                        contentValues.clear();
                        contentValues.put("id", positionID[i]);
                        contentValues.put("name", positionName[i]);
                        contentValues.put("salary", positionSalary[i]);
                        sqLiteDatabase.insert("position", null, contentValues);
                    }

                    sqLiteDatabase.execSQL("alert table people add column posid integer;");

                    for (int i = 0; i < positionID.length; i++) {
                        contentValues.clear();
                        contentValues.put("posid", positionID[i]);
                        sqLiteDatabase.update("people", contentValues, "position = ?", new String [] {positionName[i]});
                    }

                    sqLiteDatabase.execSQL("create temporary table people_tmp (id integer, name text, position text, posid integer);");
                    sqLiteDatabase.execSQL("integer into people_tmp select id, name, position, posid from people;");
                    sqLiteDatabase.execSQL("drop table people;");
                    sqLiteDatabase.execSQL("create table people (id integer primary key autoincrement, name text, posid integer);");
                    sqLiteDatabase.execSQL("insert into people select id, name, posid from people_tmp;");
                    sqLiteDatabase.execSQL("drop table people_tmp;");

                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
            }
        }
    }
}
