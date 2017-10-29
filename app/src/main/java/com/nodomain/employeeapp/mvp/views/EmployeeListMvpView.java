package com.nodomain.employeeapp.mvp.views;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.navigation.EmployeeListNavigator;

import java.util.List;


public interface EmployeeListMvpView extends MvpView {

    void showEmployees(List<Employee> employees);

    void showSpecialities(List<String> specialities);

    void showEmployeeDetailsView(Employee employee);

    void showProgress();

    void hideProgress();
}
