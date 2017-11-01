package com.nodomain.employeeapp.data.datasources.remote.impl;


import com.nodomain.employeeapp.data.datasources.remote.impl.dtos.EmployeeDto;
import com.nodomain.employeeapp.data.datasources.remote.impl.dtos.SpecialityDto;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.model.Speciality;
import com.nodomain.employeeapp.utils.collection.CollectionUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DtoMapper {

    private static final Locale LOCALE_RU = new Locale("ru","RU");
    private static final List<SimpleDateFormat> DATE_FORMATS = Arrays.asList(
            new SimpleDateFormat("yyyy-MM-dd", LOCALE_RU),
            new SimpleDateFormat("dd-MM-yyyy", LOCALE_RU));

    static {
        //That will make the parse method throw ParseException
        //when the given input string is not in the specified format.
        for (SimpleDateFormat dateFormat : DATE_FORMATS)
            dateFormat.setLenient(false);
    }

    public static List<Employee> fromEmployeeDtos(List<EmployeeDto> employeeDtos) {
        return CollectionUtil.mapCollection(employeeDtos, DtoMapper::fromEmployeeDto);
    }

    private static Employee fromEmployeeDto(EmployeeDto employeeDto) {
        String firstName = processName(employeeDto.firstName);
        String lastName = processName(employeeDto.lastName);
        Date birthdayDate = processBirthday(employeeDto.birthday);
        String avatarUrl = processAvatarUrl(employeeDto.avatarUrl);
        List<Speciality> specialities = fromSpecialityDtos(employeeDto.specialityDtos);

        return new Employee(
                firstName,
                lastName,
                birthdayDate,
                avatarUrl,
                specialities);
    }

    private static String processName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private static Date processBirthday(String birthday) {
        if (birthday == null)
            return null;

        for (SimpleDateFormat dateFormat : DATE_FORMATS)
            try {
                dateFormat.setLenient(false);
                return dateFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        return null;
    }

    private static String processAvatarUrl(String avatarUrl) {
        if (avatarUrl == null || avatarUrl.length() == 0)
            return null;
        else
            return avatarUrl;
    }

    private static List<Speciality> fromSpecialityDtos(List<SpecialityDto> specialityDtos) {
        return CollectionUtil.mapCollection(specialityDtos, DtoMapper::fromSpecialityDto);
    }

    private static Speciality fromSpecialityDto(SpecialityDto specialityDto) {
        return new Speciality(specialityDto.specialityId, specialityDto.name);
    }
}
