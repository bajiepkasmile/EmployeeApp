package com.nodomain.employeeapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;

import java.util.List;


public class EmployeeListFragment
        extends BaseMvpFragment<EmployeeListMvpPresenter> implements EmployeeListMvpView {

    public static EmployeeListFragment newInstance() {
        return new EmployeeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showEmployees(List<Employee> employees) {

    }

    @Override
    public void showEmployeeDetailsView(Employee employee) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Error error) {

    }
}
