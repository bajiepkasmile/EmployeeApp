package com.nodomain.employeeapp.mvp.presentersimpl;


import com.nodomain.employeeapp.domain.interactors.GetEmployeesInteractor;
import com.nodomain.employeeapp.domain.interactors.UpdateEmployeesInteractor;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;

import org.greenrobot.eventbus.EventBus;


public class EmployeeListMvpPresenterImpl
        extends BaseMvpPresenter<EmployeeListMvpView> implements EmployeeListMvpPresenter {

    private final UpdateEmployeesInteractor updateEmployeesInteractor;
    private final GetEmployeesInteractor getEmployeesInteractor;

    public EmployeeListMvpPresenterImpl(
            EventBus eventBus,
            UpdateEmployeesInteractor updateEmployeesInteractor,
            GetEmployeesInteractor getEmployeesInteractor) {
        super(eventBus);
        this.updateEmployeesInteractor = updateEmployeesInteractor;
        this.getEmployeesInteractor = getEmployeesInteractor;
    }

    @Override
    public void getEmployees() {
        mvpView.showProgress();
        updateEmployeesInteractor.execute(null);
    }

    @Override
    public void navigateToEmployeeDetails(Employee employee) {
        mvpView.showEmployeeDetailsView(employee);
    }
}
