package com.nodomain.employeeapp.data.datasources.local;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;

import javax.inject.Inject;

import static com.nodomain.employeeapp.develop.DevelopUtil.TODO;


public class LocalStorageImpl implements LocalStorage {

    @Inject
    public LocalStorageImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        return TODO();  //TODO
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        TODO(); //TODO()
    }
}
