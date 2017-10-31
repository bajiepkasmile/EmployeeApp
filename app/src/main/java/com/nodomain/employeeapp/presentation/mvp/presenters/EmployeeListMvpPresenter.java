package com.nodomain.employeeapp.presentation.mvp.presenters;


import com.nodomain.employeeapp.presentation.mvp.views.EmployeeListMvpView;
import com.nodomain.employeeapp.presentation.navigation.EmployeeListNavigator;


public interface EmployeeListMvpPresenter extends MvpPresenter<EmployeeListMvpView>, EmployeeListNavigator {

    void updateEmployees();

    void getEmployees();
}
