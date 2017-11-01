package com.nodomain.employeeapp.data.datasources.local.impl;


public interface DbConstants {

    String TYPE_TEXT = " TEXT";
    String TYPE_INT = " INTEGER";
    String COMMA_SEP = ", ";

    String DB_NAME = "EmployeeApp.db";
    int DB_VERSION = 1;

    interface Tables {

        interface Employees {
            String NAME = "employees";

            interface Columns {
                String FIRST_NAME = "first_name";
                String LAST_NAME = "last_name";
                String BIRTHDAY_TIME = "birthday_time";
                String AVATAR_URL = "avatar_url";
                String SPECIALITY_ID = "speciality_id";
                String SPECIALITY = "speciality";
            }
        }
    }
}
