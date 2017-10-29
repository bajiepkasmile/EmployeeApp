package com.nodomain.employeeapp.utils;


public interface Copyable<T extends Copyable<T>> {

    T copy();
}
