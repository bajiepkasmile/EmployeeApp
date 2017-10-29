package com.nodomain.employeeapp.mvp.presenters;


import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;
import com.nodomain.employeeapp.navigation.EmployeeListNavigator;


public interface EmployeeListMvpPresenter extends MvpPresenter<EmployeeListMvpView>, EmployeeListNavigator {

    void getEmployees();
}
