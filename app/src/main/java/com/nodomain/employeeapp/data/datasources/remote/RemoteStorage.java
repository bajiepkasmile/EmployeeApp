package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public interface RemoteStorage {

    List<Employee> getEmployees();
}
