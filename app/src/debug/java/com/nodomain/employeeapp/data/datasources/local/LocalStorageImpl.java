package com.nodomain.employeeapp.data.datasources.local;


import com.nodomain.employeeapp.model.Employee;

import java.util.Collections;
import java.util.List;

import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class LocalStorageImpl implements LocalStorage {

    private List<Employee> employees;

    @Override
    public List<Employee> getEmployees() {
        if (employees == null)
            return Collections.emptyList();
        else
            return copyListDeep(employees);  //return deep copy to achieve immutability of local storage
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        this.employees = copyListDeep(employees);  //return deep copy to achieve immutability of local storage
    }
}
