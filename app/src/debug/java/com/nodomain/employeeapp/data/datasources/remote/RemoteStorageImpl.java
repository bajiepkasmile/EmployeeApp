package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.model.Employee;

import java.util.Arrays;
import java.util.List;

import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class RemoteStorageImpl implements RemoteStorage {

    private List<Employee> employees = Arrays.asList(
            new Employee("FNaMe1", "LNamE1", 23587209, "", 101, "Менеджер"),
            new Employee("FName2", "lNAme2", 63487203, "", 101, "Менеджер"),
            new Employee("FNAme3", "LName3", 24534203, "", 101, "Менеджер"),
            new Employee("fNaMe4", "lNamE4", 23364203, "", 102, "Разработчик"),
            new Employee("FNaMe5", "LNAme5", 12587203, "", 102, "Разработчик"),
            new Employee("fNAme6", "lName6", 56587203, "", 102, "Разработчик"),
            new Employee("FNamE7", "LNAMe7", 94587203, "", 102, "Разработчик")
    );

    @Override
    public List<Employee> getEmployees() {
        return copyListDeep(employees);  //return deep copy to achieve immutability of remote storage
    }
}
