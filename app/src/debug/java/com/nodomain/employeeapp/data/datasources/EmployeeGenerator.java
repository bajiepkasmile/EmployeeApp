package com.nodomain.employeeapp.data.datasources;


import android.util.Pair;

import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.model.Speciality;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
        List<Speciality> specialities = generateRandomSpecialities();
        return new Employee(firstName, lastName, birthdayDate, null, specialities);
    }

    private static List<Speciality> generateRandomSpecialities() {
        List<Speciality> specialities = new ArrayList<>();
        if (Math.random() < 0.4) {
            specialities.add(new Speciality(SPECIALITY_MANAGER_ID, SPECIALITY_MANAGER));
            specialities.add(new Speciality(SPECIALITY_MANAGER_ID, SPECIALITY_MANAGER));
        } else {
            Pair<Long, String > specialityPair = generateRandomSpecialityPair();
            specialities.add(new Speciality(specialityPair.first, specialityPair.second));
        }
        return specialities;
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
