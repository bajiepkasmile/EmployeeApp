package com.nodomain.employeeapp.data.datasources.local;


import android.database.sqlite.SQLiteDatabase;

import com.nodomain.employeeapp.data.datasources.local.impl.DbHelper;
import com.nodomain.employeeapp.data.datasources.local.impl.DboMapper;
import com.nodomain.employeeapp.data.datasources.local.impl.EmployeeDbo;
import com.nodomain.employeeapp.model.Employee;

import java.util.List;

import javax.inject.Inject;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class LocalStorageImpl implements LocalStorage {

    private final DbHelper dbHelper;

    @Inject
    public LocalStorageImpl(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Employee> getEmployees() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        List<EmployeeDbo> employeeDbos = cupboard().withDatabase(sqLiteDatabase).query(EmployeeDbo.class).list();
        return DboMapper.fromEmployeeDbos(employeeDbos);
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        cupboard().withDatabase(sqLiteDatabase).dropAllTables();

//        cupboard().withDatabase(sqLiteDatabase).delete(EmployeeDbo.class, null);
        cupboard().withDatabase(sqLiteDatabase).put(DboMapper.toEmployeeDbos(employees));
    }
}
