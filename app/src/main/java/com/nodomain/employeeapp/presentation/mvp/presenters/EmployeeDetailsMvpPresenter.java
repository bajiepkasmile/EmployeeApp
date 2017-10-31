package com.nodomain.employeeapp.presentation.mvp.presenters;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.presentation.mvp.views.EmployeeDetailsMvpView;
import com.nodomain.employeeapp.presentation.navigation.EmployeeDetailsNavigator;


public interface EmployeeDetailsMvpPresenter
        extends MvpPresenter<EmployeeDetailsMvpView>, EmployeeDetailsNavigator {

    void getEmployeeDetails(Employee employee);
}
