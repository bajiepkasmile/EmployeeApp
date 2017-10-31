package com.nodomain.employeeapp.data.datasources.local;


import com.nodomain.employeeapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.nodomain.employeeapp.data.datasources.EmployeeGenerator.generateRandomEmployee;
import static com.nodomain.employeeapp.develop.DevelopUtil.sleep;
import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class LocalStorageImpl implements LocalStorage {

    private List<Employee> employees = new ArrayList<>();

    {
        if (Math.random() < 0.5)  //emulate empty or filled database
            for (int i = 0; i < 4; i++)
                employees.add(generateRandomEmployee(i));
    }

    @Inject
    public LocalStorageImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        sleep(1500);
        return copyListDeep(employees);  //return deep copy to achieve immutability of local storage
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        sleep(1500);
        this.employees = copyListDeep(employees);  //save deep copy to achieve immutability of local storage
    }
}
