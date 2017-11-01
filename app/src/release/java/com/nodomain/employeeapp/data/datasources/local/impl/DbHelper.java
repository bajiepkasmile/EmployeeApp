package com.nodomain.employeeapp.data.datasources.local.impl;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;

import static com.nodomain.employeeapp.data.datasources.local.impl.DbConstants.*;


public class DbHelper extends SQLiteOpenHelper {

    @Inject
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Tables.Employees.NAME + "("
                + Tables.Employees.Columns.FIRST_NAME + TYPE_TEXT + COMMA_SEP
                + Tables.Employees.Columns.LAST_NAME + TYPE_TEXT + COMMA_SEP
                + Tables.Employees.Columns.BIRTHDAY_TIME + TYPE_INT + COMMA_SEP
                + Tables.Employees.Columns.AVATAR_URL + TYPE_TEXT + COMMA_SEP
                + Tables.Employees.Columns.SPECIALITY_ID + TYPE_INT + COMMA_SEP
                + Tables.Employees.Columns.SPECIALITY + TYPE_TEXT
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tables.Employees.NAME);
        onCreate(sqLiteDatabase);
    }
}
