package com.nodomain.employeeapp.data.repositories;


import com.nodomain.employeeapp.data.DataSourceType;
import com.nodomain.employeeapp.data.datasources.cache.Cache;
import com.nodomain.employeeapp.data.datasources.local.LocalStorage;
import com.nodomain.employeeapp.data.datasources.remote.RemoteStorage;
import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public class EmployeesRepository {

    private RemoteStorage remoteStorage;
    private LocalStorage localStorage;
    private Cache cache;

    public boolean hasCachedEmployees() {
        return cache.hasEmployees();
    }

    public List<Employee> getEmployees(DataSourceType dataSourceType) {
        List<Employee> employees;

        switch (dataSourceType) {
            case REMOTE:
                employees = remoteStorage.getEmployees();
                localStorage.setEmployees(employees);
                cache.setEmployees(employees);
                return employees;
            case LOCAL:
                employees = localStorage.getEmployees();
                cache.setEmployees(employees);
                return employees;
            default:
                return cache.getEmployees();
        }
    }
}
