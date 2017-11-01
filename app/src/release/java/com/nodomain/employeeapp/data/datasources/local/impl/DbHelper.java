package com.nodomain.employeeapp.data.datasources.local.impl;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import javax.inject.Inject;

import nl.littlerobots.cupboard.tools.gson.GsonListFieldConverterFactory;
import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "EmployeeApp.db";
    private static final int DB_VERSION = 1;

    static {
        setUpCupboard();
        cupboard().register(EmployeeDbo.class);
    }

    @Inject
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static void setUpCupboard() {
        Cupboard cupboard = new CupboardBuilder()
                .registerFieldConverterFactory(new GsonListFieldConverterFactory(new Gson()))
                .build();
        CupboardFactory.setCupboard(cupboard);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }
}
