package com.nodomain.employeeapp.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateUtil {

    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final Locale LOCALE_RU = new Locale("ru","RU");
    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd.MM.yyyy", LOCALE_RU);
    private static final List<SimpleDateFormat> DATE_FORMATS = Arrays.asList(
            new SimpleDateFormat("yyyy-MM-dd", LOCALE_RU),
            new SimpleDateFormat("dd-MM-yyyy", LOCALE_RU));

    static {
        for (SimpleDateFormat dateFormat : DATE_FORMATS)
            dateFormat.setLenient(false);
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null)
            return null;

        for (SimpleDateFormat dateFormat : DATE_FORMATS)
            try {
                dateFormat.setLenient(false);
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        return null;
    }

    public static int dateToAge(Date date) {
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(date);
        int birthdayYear = birthday.get(Calendar.YEAR);
        return CURRENT_YEAR - birthdayYear;
    }

    public static String dateToBirthdayStr(Date date) {
        return BIRTHDAY_FORMAT.format(date);
    }
}
