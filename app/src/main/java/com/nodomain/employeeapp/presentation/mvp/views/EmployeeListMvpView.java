package com.nodomain.employeeapp.presentation.mvp.views;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public interface EmployeeListMvpView extends MvpView {

    void showUpdatedEmployees(List<Employee> employees);

    void showUpdatingProgress();

    void hideUpdatingProgress();

    void notifyUpdatingSuccess();

    void showEmployeeDetailsView(Employee employee);
}
