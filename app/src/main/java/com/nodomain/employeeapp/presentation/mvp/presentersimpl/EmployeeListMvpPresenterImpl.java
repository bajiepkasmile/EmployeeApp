package com.nodomain.employeeapp.presentation.mvp.presentersimpl;


import com.nodomain.employeeapp.domain.events.GetEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesFailureEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.interactors.UpdateEmployeesInteractor;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.presentation.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.presentation.mvp.views.EmployeeListMvpView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


public class EmployeeListMvpPresenterImpl
        extends BaseMvpPresenter<EmployeeListMvpView> implements EmployeeListMvpPresenter {

    private final UpdateEmployeesInteractor updateEmployeesInteractor;

    @Inject
    public EmployeeListMvpPresenterImpl(
            EventBus eventBus,
            UpdateEmployeesInteractor updateEmployeesInteractor) {
        super(eventBus);
        this.updateEmployeesInteractor = updateEmployeesInteractor;
    }

    @Override
    public void updateEmployees() {
        mvpView.showUpdatingProgress();
        updateEmployeesInteractor.execute(null);
    }

    @Override
    public void navigateToEmployeeDetails(Employee employee) {
        mvpView.showEmployeeDetailsView(employee);
    }

    @Subscribe
    public void onUpdateEmployeesSuccess(UpdateEmployeesSuccessEvent event) {
        removeStickyEvent(event);
        mvpView.hideUpdatingProgress();
        mvpView.notifyUpdatingSuccess();
        mvpView.showUpdatedEmployees(event.getData());
    }

    @Subscribe
    public void onUpdateEmployeesFailure(UpdateEmployeesFailureEvent event) {
        removeStickyEvent(event);
        mvpView.showError(event.getError());
    }

    @Subscribe
    public void onGetEmployeesSuccess(GetEmployeesSuccessEvent event) {
        removeStickyEvent(event);
        mvpView.hideUpdatingProgress();
        mvpView.showUpdatedEmployees(event.getData());
    }
}
