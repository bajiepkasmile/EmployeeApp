package com.nodomain.employeeapp.mvp.presenters;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.views.EmployeeDetailsMvpView;
import com.nodomain.employeeapp.navigation.EmployeeDetailsNavigator;


public interface EmployeeDetailsMvpPresenter
        extends MvpPresenter<EmployeeDetailsMvpView>, EmployeeDetailsNavigator {

    void getEmployeeDetails(Employee employee);
}
