package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.nodomain.employeeapp.data.datasources.EmployeeGenerator.generateRandomEmployee;
import static com.nodomain.employeeapp.develop.DevelopUtil.sleep;
import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class RemoteStorageImpl implements RemoteStorage {

    private final List<Employee> employees = new ArrayList<>();

    {
        for (int i = 0; i < 7; i++)
            employees.add(generateRandomEmployee(i));
    }

    @Inject
    public RemoteStorageImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        sleep(1500);
        return copyListDeep(employees);  //return deep copy to achieve immutability of remote storage
    }
}
