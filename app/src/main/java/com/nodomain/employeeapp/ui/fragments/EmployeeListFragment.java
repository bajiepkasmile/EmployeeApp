package com.nodomain.employeeapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeListMvpView;
import com.nodomain.employeeapp.navigation.EmployeeListNavigator;
import com.nodomain.employeeapp.ui.listeners.OnItemClickListener;
import com.nodomain.employeeapp.ui.recyclerviews.adapters.EmployeesAdapter;

import java.util.List;

import butterknife.BindView;


public class EmployeeListFragment
        extends BaseMvpFragment<EmployeeListMvpPresenter, EmployeeListNavigator>
        implements EmployeeListMvpView, OnItemClickListener {

    @BindView(R.id.rv_employees)
    RecyclerView rvEmployees;
    @BindView(R.id.tv_list_is_empty)
    TextView tvListIsEmpty;
    @BindView(R.id.pb_load_employees)
    TextView pbLoadEmployees;
    @BindView(R.id.tv_load_employees)
    TextView tvLoadEmployees;

    private EmployeesAdapter adapter;

    public static EmployeeListFragment newInstance() {
        return new EmployeeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getString(R.string.loading);
        setTitle(title);
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        if (adapter == null) {
            adapter = new EmployeesAdapter(employees, this);
            rvEmployees.setAdapter(adapter);
        } else
            adapter.setEmployees(employees);
    }

    @Override
    public void showEmployeeDetailsView(Employee employee) {
        navigator.navigateToEmployeeDetails(employee);
    }

    @Override
    public void showProgress() {
        rvEmployees.setVisibility(View.GONE);
        tvListIsEmpty.setVisibility(View.GONE);
        pbLoadEmployees.setVisibility(View.VISIBLE);
        tvLoadEmployees.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoadEmployees.setVisibility(View.GONE);
        tvLoadEmployees.setVisibility(View.GONE);
    }

    @Override
    public void showError(Error error) {
        switch (error) {
            case NETWORK_IS_NOT_AVAILABLE:
                Toast.makeText(getContext(), R.string.error_network_is_not_available, Toast.LENGTH_LONG).show();
                break;
            case CONNECTION_FAILED:
                Toast.makeText(getContext(), R.string.error_connection_failed, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Employee employee = adapter.getItem(position);
        mvpPresenter.navigateToEmployeeDetails(employee);
    }
}
