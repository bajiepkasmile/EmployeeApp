package com.nodomain.employeeapp.mvp.views;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.navigation.EmployeeDetailsNavigator;


public interface EmployeeDetailsMvpView extends MvpView {

    void showEmployeeDetails(Employee employee);

    void showEmployeeListView();
}
