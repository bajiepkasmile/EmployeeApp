package com.nodomain.employeeapp.domain.events;


import com.nodomain.employeeapp.domain.Error;


public class BaseFailureEvent {

    private final Error error;

    protected BaseFailureEvent(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
