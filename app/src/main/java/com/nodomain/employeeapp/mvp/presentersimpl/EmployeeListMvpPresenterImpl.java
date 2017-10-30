package com.nodomain.employeeapp.mvp.presentersimpl;


import com.nodomain.employeeapp.domain.events.GetEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesFailureEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.interactors.GetEmployeesInteractor;
import com.nodomain.employeeapp.domain.interactors.UpdateEmployeesInteractor;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


public class EmployeeListMvpPresenterImpl
        extends BaseMvpPresenter<EmployeeListMvpView> implements EmployeeListMvpPresenter {

    private final UpdateEmployeesInteractor updateEmployeesInteractor;
    private final GetEmployeesInteractor getEmployeesInteractor;

    @Inject
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

    @Subscribe
    public void onUpdateEmployeesSuccess(UpdateEmployeesSuccessEvent event) {
        removeStickyEvent(event);
        mvpView.hideProgress();
        mvpView.showEmployees(event.getData());
    }

    @Subscribe
    public void onUpdateEmployeesFailure(UpdateEmployeesFailureEvent event) {
        removeStickyEvent(event);
        mvpView.showError(event.getError());
        getEmployeesInteractor.execute(null);
    }

    @Subscribe
    public void onGetEmployeesSuccess(GetEmployeesSuccessEvent event) {
        removeStickyEvent(event);
        mvpView.hideProgress();
        mvpView.showEmployees(event.getData());
    }
}
