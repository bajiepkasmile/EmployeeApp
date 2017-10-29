package com.nodomain.employeeapp.mvp.presenters;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;
import com.nodomain.employeeapp.navigation.EmployeeListNavigator;


public interface EmployeeListMvpPresenter extends MvpPresenter<EmployeeListMvpView>, EmployeeListNavigator {

    void getEmployees();

    void getEmployeeDetails(Employee employee);
}
