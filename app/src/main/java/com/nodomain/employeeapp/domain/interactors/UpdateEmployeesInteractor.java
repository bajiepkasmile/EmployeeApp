package com.nodomain.employeeapp.domain.interactors;


import android.os.Handler;

import com.nodomain.employeeapp.data.repositories.EmployeesRepository;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesFailureEvent;
import com.nodomain.employeeapp.domain.events.UpdateEmployeesSuccessEvent;
import com.nodomain.employeeapp.domain.exceptions.ConnectionFailedException;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import static com.nodomain.employeeapp.data.DataSourceType.REMOTE;
import static com.nodomain.employeeapp.domain.Error.CONNECTION_FAILED;
import static com.nodomain.employeeapp.domain.Error.NETWORK_IS_NOT_AVAILABLE;


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
        boolean networkIsNotAvailable = !networkUtil.networkIsAvailable();

        if (networkIsNotAvailable) {
            postStickyEvent(new UpdateEmployeesFailureEvent(NETWORK_IS_NOT_AVAILABLE));
            return;
        }

        inBackground(() -> {
            try {
                List<Employee> employees = repository.getEmployees(REMOTE);
                onMainThread(() -> postStickyEvent(new UpdateEmployeesSuccessEvent(employees)));
            } catch (ConnectionFailedException e) {
                onMainThread(() -> postStickyEvent(new UpdateEmployeesFailureEvent(CONNECTION_FAILED)));
            }
        });
    }
}
