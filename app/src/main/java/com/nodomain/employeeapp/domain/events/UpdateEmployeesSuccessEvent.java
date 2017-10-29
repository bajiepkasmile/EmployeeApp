package com.nodomain.employeeapp.domain.events;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public class UpdateEmployeesSuccessEvent extends BaseSuccessEvent<List<Employee>> {

    public UpdateEmployeesSuccessEvent(List<Employee> data) {
        super(data);
    }
}
