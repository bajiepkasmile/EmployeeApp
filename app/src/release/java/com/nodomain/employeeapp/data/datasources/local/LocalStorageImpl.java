package com.nodomain.employeeapp.data.datasources.local;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nodomain.employeeapp.data.datasources.local.impl.DbConstants.*;
import com.nodomain.employeeapp.data.datasources.local.impl.DbHelper;
import com.nodomain.employeeapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class LocalStorageImpl implements LocalStorage {

    private final DbHelper dbHelper;

    @Inject
    public LocalStorageImpl(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Employee> getEmployees() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Tables.Employees.NAME, null, null, null, null, null, null);
        cursor.close();
        dbHelper.close();
        return getEmployeesFromCursor(cursor);
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (Employee employee : employees) {
            putEmployeeIntoContentValues(employee, cv);
            db.insert(Tables.Employees.NAME, null, cv);
        }
    }

    private List<Employee> getEmployeesFromCursor(Cursor employeeCursor) {
        int firstNameColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.FIRST_NAME);
        int lastNameColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.LAST_NAME);
        int birthdayTimeColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.BIRTHDAY_TIME);
        int avatarUrlColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.AVATAR_URL);
        int specialityIdColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.SPECIALITY_ID);
        int specialityColumnIndex = employeeCursor.getColumnIndex(Tables.Employees.Columns.SPECIALITY);

        List<Employee> employees = new ArrayList<>(employeeCursor.getCount());

        employeeCursor.moveToFirst();
        while (!employeeCursor.isAfterLast()) {
            String firstName = employeeCursor.getString(firstNameColumnIndex);
            String lastName = employeeCursor.getString(lastNameColumnIndex);
            long birthdayTime = employeeCursor.getLong(birthdayTimeColumnIndex);
            String avatarUrl = employeeCursor.getString(avatarUrlColumnIndex);
            long specialityId = employeeCursor.getLong(specialityIdColumnIndex);
            String speciality = employeeCursor.getString(specialityColumnIndex);

            Employee employee =
                    new Employee(firstName, lastName, birthdayTime, avatarUrl, specialityId, speciality);
            employees.add(employee);

            employeeCursor.moveToNext();
        }

        return employees;
    }

    private void putEmployeeIntoContentValues(Employee employee, ContentValues cv) {
        cv.put(Tables.Employees.Columns.FIRST_NAME, employee.getFirstName());
        cv.put(Tables.Employees.Columns.LAST_NAME, employee.getLastName());
        cv.put(Tables.Employees.Columns.BIRTHDAY_TIME, employee.getBirthdayTime());
        cv.put(Tables.Employees.Columns.AVATAR_URL, employee.getAvatarUrl());
        cv.put(Tables.Employees.Columns.SPECIALITY_ID, employee.getSpecialityId());
        cv.put(Tables.Employees.Columns.SPECIALITY, employee.getSpeciality());
    }
}
