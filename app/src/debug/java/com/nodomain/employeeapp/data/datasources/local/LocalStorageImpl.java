package com.nodomain.employeeapp.data.datasources.local;


import com.nodomain.employeeapp.model.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.nodomain.employeeapp.utils.ListUtil.copyListDeep;


public class LocalStorageImpl implements LocalStorage {

    private List<Employee> employees = Arrays.asList(
            new Employee("FNaMe1", "LNamE1", new Date(23587209), "", 101, "Менеджер"),
            new Employee("FName2", "lNAme2", null, "", 101, "Менеджер"),
            new Employee("fNaMe4", "lNamE4", new Date(23364203), "", 102, "Разработчик"),
            new Employee("FNamE7", "LNAMe7", null, "", 102, "Разработчик")
    );

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
