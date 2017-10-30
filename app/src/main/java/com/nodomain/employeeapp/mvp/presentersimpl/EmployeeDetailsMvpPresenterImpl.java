package com.nodomain.employeeapp.mvp.presentersimpl;


import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeDetailsMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeDetailsMvpView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;


public class EmployeeDetailsMvpPresenterImpl
        extends BaseMvpPresenter<EmployeeDetailsMvpView> implements EmployeeDetailsMvpPresenter {

    @Inject
    public EmployeeDetailsMvpPresenterImpl(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public void getEmployeeDetails(Employee employee) {
        mvpView.showEmployeeDetails(employee);
    }

    @Override
    public void navigateToEmployeeList() {
        mvpView.showEmployeeListView();
    }
}
