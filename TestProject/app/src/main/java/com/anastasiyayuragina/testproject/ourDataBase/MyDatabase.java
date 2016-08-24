package com.anastasiyayuragina.testproject.ourDataBase;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by anastasiyayuragina on 8/18/16.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";
    public static final int VERSION = 1;
}

