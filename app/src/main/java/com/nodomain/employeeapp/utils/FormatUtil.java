package com.nodomain.employeeapp.utils;


import android.content.Context;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.model.Speciality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;


public class FormatUtil {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final Locale LOCALE_RU = new Locale("ru","RU");
    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd.MM.yyyy", LOCALE_RU);

    private final Context context;

    @Inject
    public FormatUtil(Context context) {
        this.context = context;
    }

    public String specialitiesToStr(List<Speciality> specialities) {
        if (specialities == null || specialities.size() == 0)
            return context.getString(R.string.dash);

        StringBuilder sbSpecialities = new StringBuilder();
        sbSpecialities.append(specialities.get(0));
        if (specialities.size() > 1)
            for(int i = 1; i < specialities.size(); i++) {
                sbSpecialities.append(", ");
                sbSpecialities.append(specialities.get(i));
            }

        return sbSpecialities.toString();
    }

    public String dateToBirthdayStr(Date date) {
        if (date == null)
            return context.getString(R.string.dash);
        else
            return BIRTHDAY_FORMAT.format(date);
    }

    public String dateToAgeStr(Date birthdayDate) {
        int age = dateToAge(birthdayDate);
        if (age == 0)
            return context.getString(R.string.dash);
        else
            return context.getResources().getQuantityString(R.plurals.age, age, age);
    }

    private int dateToAge(Date date) {
        if (date == null)
            return 0;

        Calendar birthday = Calendar.getInstance();
        birthday.setTime(date);
        int birthdayYear = birthday.get(Calendar.YEAR);
        return CURRENT_YEAR - birthdayYear;
    }
}
