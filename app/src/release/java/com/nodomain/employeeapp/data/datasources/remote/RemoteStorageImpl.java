package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;

import static com.nodomain.employeeapp.develop.DevelopUtil.TODO;


public class RemoteStorageImpl implements RemoteStorage{

    @Override
    public List<Employee> getEmployees() {
        return TODO();  //TODO
    }
}
