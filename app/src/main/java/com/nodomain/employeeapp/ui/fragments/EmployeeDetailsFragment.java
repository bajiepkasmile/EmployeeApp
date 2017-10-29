package com.nodomain.employeeapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeDetailsMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeDetailsMvpView;


public class EmployeeDetailsFragment
        extends BaseMvpFragment<EmployeeDetailsMvpPresenter> implements EmployeeDetailsMvpView {

    private static final String EMPLOYEE_ARG = "employee";

    public static EmployeeDetailsFragment newInstance(Employee employee) {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(EMPLOYEE_ARG, employee);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showEmployeeDetails(Employee employee) {

    }

    @Override
    public void showEmployeeListView() {

    }

    @Override
    public void showError(Error error) {

    }
}
