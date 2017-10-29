package com.nodomain.employeeapp.domain.events;


import com.nodomain.employeeapp.model.Employee;

import java.util.List;


public class GetEmployeesSuccessEvent extends BaseSuccessEvent<List<Employee>> {

    public GetEmployeesSuccessEvent(List<Employee> data) {
        super(data);
    }
}
