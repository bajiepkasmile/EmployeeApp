package com.nodomain.employeeapp.data.datasources.local;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public interface LocalStorage {

    List<Employee> getEmployees();

    void setEmployees(List<Employee> employees);
}
