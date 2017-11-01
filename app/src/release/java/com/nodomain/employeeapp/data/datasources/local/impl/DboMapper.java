package com.nodomain.employeeapp.data.datasources.local.impl;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.utils.collection.CollectionUtil;

import java.util.List;


public class DboMapper {

    public static List<Employee> fromEmployeeDbos(List<EmployeeDbo> employeeDbos) {
        return CollectionUtil.mapCollection(employeeDbos, DboMapper::fromEmployeeDbo);
    }

    public static List<EmployeeDbo> toEmployeeDbos(List<Employee> employees) {
        return CollectionUtil.mapCollection(employees, DboMapper::toEmployeeDbo);
    }

    private static Employee fromEmployeeDbo(EmployeeDbo employeeDbo) {
        return new Employee(
                employeeDbo.firstName,
                employeeDbo.lastName,
                employeeDbo.birthdayDate,
                employeeDbo.avatarUrl,
                employeeDbo.specialities);
    }

    private static EmployeeDbo toEmployeeDbo(Employee employee) {
        EmployeeDbo employeeDbo = new EmployeeDbo();
        employeeDbo.firstName = employee.getFirstName();
        employeeDbo.lastName = employee.getLastName();
        employeeDbo.birthdayDate = employee.getBirthdayDate();
        employeeDbo.avatarUrl = employee.getAvatarUrl();
        employeeDbo.specialities = employee.getSpecialities();
        return employeeDbo;
    }
}
