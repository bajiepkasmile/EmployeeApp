package com.nodomain.employeeapp.domain.events;


public class BaseSuccessEvent<D> {

    private final D data;

    protected BaseSuccessEvent(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
    }
}
