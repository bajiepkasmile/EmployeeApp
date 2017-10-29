package com.nodomain.employeeapp.domain.interactors;


import android.os.Handler;

import com.nodomain.employeeapp.data.repositories.EmployeesRepository;
import com.nodomain.employeeapp.domain.events.GetEmployeesSuccessEvent;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.nodomain.employeeapp.data.DataSourceType.*;


public class GetEmployeesInteractor extends BaseInteractor<Void> {

    private final EmployeesRepository repository;

    public GetEmployeesInteractor(
            Handler mainThreadHandler,
            EventBus eventBus,
            EmployeesRepository repository) {
        super(mainThreadHandler, eventBus);
        this.repository = repository;
    }

    @Override
    void execute(Void args) {
        if (repository.hasCachedEmployees()) {
            List<Employee> employees = repository.getEmployees(CACHE);
            postStickyEvent(new GetEmployeesSuccessEvent(employees));
            return;
        }

        inBackground(() -> {
            List<Employee> employees = repository.getEmployees(LOCAL);
            onMainThread(() -> postStickyEvent(new GetEmployeesSuccessEvent(employees)));
        });
    }
}
