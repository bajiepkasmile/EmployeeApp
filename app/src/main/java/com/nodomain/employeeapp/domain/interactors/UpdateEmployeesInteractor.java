package com.nodomain.employeeapp.domain.interactors;


import android.os.Handler;

import com.nodomain.employeeapp.data.DataSourceType;
import com.nodomain.employeeapp.data.repositories.EmployeesRepository;
import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.domain.events.GetEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesFailureEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.exceptions.ConnectionFailedException;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;


public class UpdateEmployeesInteractor extends BaseInteractor<Void> {

    private final EmployeesRepository repository;
    private final NetworkUtil networkUtil;

    @Inject
    public UpdateEmployeesInteractor(
            ExecutorService executorService,
            Handler mainThreadHandler,
            EventBus eventBus, EmployeesRepository repository,
            NetworkUtil networkUtil) {
        super(executorService, mainThreadHandler, eventBus);
        this.repository = repository;
        this.networkUtil = networkUtil;
    }

    @Override
    public void execute(Void aVoid) {
        if (networkUtil.networkIsAvailable())
            processNetworkIsAvailableCase();
        else
            processNetworkIsNotAvailableCase();

    }

    private void processNetworkIsAvailableCase() {
        inBackground(() -> {
            try {
                List<Employee> employees = repository.getEmployees(DataSourceType.REMOTE);
                onMainThread(() -> postStickyEvent(new UpdateEmployeesSuccessEvent(employees)));
            } catch (ConnectionFailedException e) {
                onMainThread(() -> postStickyEvent(new UpdateEmployeesFailureEvent(Error.CONNECTION_FAILED)));

                if (repository.hasCachedEmployees()) {
                    List<Employee> employees = repository.getEmployees(DataSourceType.CACHE);
                    onMainThread(() -> postStickyEvent(new GetEmployeesSuccessEvent(employees)));
                    return;
                }

                List<Employee> employees = repository.getEmployees(DataSourceType.LOCAL);
                onMainThread(() -> postStickyEvent(new GetEmployeesSuccessEvent(employees)));
            }
        });
    }

    private void processNetworkIsNotAvailableCase() {
        postStickyEvent(new UpdateEmployeesFailureEvent(Error.NETWORK_IS_NOT_AVAILABLE));

        if (repository.hasCachedEmployees()) {
            List<Employee> employees = repository.getEmployees(DataSourceType.CACHE);
            postStickyEvent(new GetEmployeesSuccessEvent(employees));
            return;
        }

        inBackground(() -> {
            List<Employee> employees = repository.getEmployees(DataSourceType.LOCAL);
            onMainThread(() -> postStickyEvent(new GetEmployeesSuccessEvent(employees)));
        });
    }
}
