package com.nodomain.employeeapp.utils.collection;


public interface Copyable<T extends Copyable<T>> {

    T copy();
}
