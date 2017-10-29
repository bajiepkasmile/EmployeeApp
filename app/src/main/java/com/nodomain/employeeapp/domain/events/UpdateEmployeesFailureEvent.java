package com.nodomain.employeeapp.domain.events;


import com.nodomain.employeeapp.domain.Error;


public class UpdateEmployeesFailureEvent extends BaseFailureEvent {

    public UpdateEmployeesFailureEvent(Error error) {
        super(error);
    }
}
