package com.nodomain.employeeapp.data.datasources;


import android.util.Pair;

import com.nodomain.employeeapp.model.Employee;

import java.util.Calendar;
import java.util.Date;


public class EmployeeGenerator {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final String SPECIALITY_MANAGER = "Менеджер";
    private static final String SPECIALITY_DEVELOPER = "Разработчик";
    private static final long SPECIALITY_MANAGER_ID = 101;
    private static final long SPECIALITY_DEVELOPER_ID = 102;

    public static Employee generateRandomEmployee(int index) {
        String firstName = "Fname" + index;
        String lastName = "Lname" + index;
        Date birthdayDate = generateRandomNullableAgeDate();
        Pair<Long, String > specialityPair = generateRandomSpecialityPair();
        long specialityId = specialityPair.first;
        String speciality = specialityPair.second;

        return new Employee(firstName, lastName, birthdayDate, null, specialityId, speciality);
    }

    private static Pair<Long, String> generateRandomSpecialityPair() {
        if (Math.random() < 0.4)
            return new Pair<>(SPECIALITY_MANAGER_ID, SPECIALITY_MANAGER);
        else
            return new Pair<>(SPECIALITY_DEVELOPER_ID, SPECIALITY_DEVELOPER);
    }

    private static Date generateRandomNullableAgeDate() {
        if (Math.random() > 0.5)
            return generateRandomAgeDate();
        else
            return null;
    }

    private static Date generateRandomAgeDate() {
        Calendar calendar = Calendar.getInstance();
        int randomAge = (int) (Math.random() * 40);
        calendar.set(Calendar.YEAR, CURRENT_YEAR - randomAge);
        return calendar.getTime();
    }
}
