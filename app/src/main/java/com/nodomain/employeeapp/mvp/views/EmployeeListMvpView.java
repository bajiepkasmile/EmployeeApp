package com.nodomain.employeeapp.mvp.views;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public interface EmployeeListMvpView extends MvpView {

    void showEmployees(List<Employee> employees);

    void showEmployeeDetailsView(Employee employee);

    void showProgress();

    void hideProgress();
}
