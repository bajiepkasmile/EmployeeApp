package com.nodomain.employeeapp.presentation.mvp.views;


import com.nodomain.employeeapp.model.Employee;


public interface EmployeeDetailsMvpView extends MvpView {

    void showEmployeeDetails(Employee employee);

    void showPreviousView();
}
