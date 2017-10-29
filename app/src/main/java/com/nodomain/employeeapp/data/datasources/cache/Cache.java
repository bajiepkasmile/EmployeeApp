package com.nodomain.employeeapp.data.datasources.cache;


import com.nodomain.employeeapp.model.Employee;

import java.util.Collections;
import java.util.List;

import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class Cache {

    private List<Employee> employees;

    public boolean hasEmployees() {
        return employees != null;
    }

    public List<Employee> getEmployees() {
        if (employees == null)
            return Collections.emptyList();
        else
            return copyListDeep(employees);  //return deep copy to achieve immutability of cache
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = copyListDeep(employees);  //save deep copy to achieve immutability of cache
    }
}
