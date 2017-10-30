package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.model.Employee;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class RemoteStorageImpl implements RemoteStorage {

    private final List<Employee> employees = Arrays.asList(
            new Employee("FNaMe1", "LNamE1", null, "", 101, "Менеджер"),
            new Employee("FName2", "lNAme2", new Date(63487203), "", 101, "Менеджер"),
            new Employee("FNAme3", "LName3", new Date(24534203), "", 101, "Менеджер"),
            new Employee("fNaMe4", "lNamE4", null, "", 102, "Разработчик"),
            new Employee("FNaMe5", "LNAme5", new Date(12587203), "", 102, "Разработчик"),
            new Employee("fNAme6", "lName6", new Date(56587203), "", 102, "Разработчик"),
            new Employee("FNamE7", "LNAMe7", new Date(94587203), "", 102, "Разработчик")
    );

    @Inject
    public RemoteStorageImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        return copyListDeep(employees);  //return deep copy to achieve immutability of remote storage
    }
}
