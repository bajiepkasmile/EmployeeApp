package com.nodomain.employeeapp.presentation.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.model.Speciality;
import com.nodomain.employeeapp.presentation.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.presentation.mvp.views.EmployeeListMvpView;
import com.nodomain.employeeapp.presentation.navigation.EmployeeListNavigator;
import com.nodomain.employeeapp.presentation.ui.activities.MainActivity;
import com.nodomain.employeeapp.presentation.ui.listeners.OnItemClickListener;
import com.nodomain.employeeapp.presentation.ui.recyclerviews.adapters.EmployeesAdapter;
import com.nodomain.employeeapp.utils.FormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;


public class EmployeeListFragment
        extends BaseMvpFragment<EmployeeListMvpPresenter, EmployeeListNavigator>
        implements EmployeeListMvpView, OnItemClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinner_specialities)
    Spinner spinnerSpecialities;
    @BindView(R.id.rv_employees)
    RecyclerView rvEmployees;
    @BindView(R.id.tv_list_is_empty)
    TextView tvListIsEmpty;
    @BindView(R.id.pb_load_employees)
    ProgressBar pbLoadEmployees;
    @BindView(R.id.tv_load_employees)
    TextView tvLoadEmployees;

    @BindString(R.string.loading)
    String titleLoading;

    @Inject
    FormatUtil formatUtil;

    private EmployeesAdapter employeesAdapter;
    private ArrayAdapter<Speciality> specialitiesAdapter;
    private List<Employee> employees;
    private List<Speciality> specialities;
    private int selectedSpecialityPosition;
    private boolean inUpdatingProgress;

    public static EmployeeListFragment newInstance() {
        return new EmployeeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity.getMainActivitySubComponent(getActivity()).inject(this);
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null)
            mvpPresenter.updateEmployees();
        else if (!inUpdatingProgress) {
            setTitle(null);
            spinnerSpecialities.setVisibility(View.VISIBLE);
            showSpecialities();
            showEmployeesWithSelectedSpecialityOrEmptyList();
        }
    }

    @Override
    public void showUpdatedEmployees(List<Employee> updatedEmployees) {
        specialities = pickSpecialitiesFromEmployees(updatedEmployees);
        selectedSpecialityPosition = calculateNewSelectedSpecialityPosition();
        showSpecialities();
        employees = updatedEmployees;
        showEmployeesWithSelectedSpecialityOrEmptyList();
    }

    private int calculateNewSelectedSpecialityPosition() {
        String previousSpeciality = (String) spinnerSpecialities.getSelectedItem();
        if (specialities.contains(previousSpeciality))
            return specialities.indexOf(previousSpeciality);
        else
            return 0;
    }

    @Override
    public void showUpdatingProgress() {
        inUpdatingProgress = true;
        setTitle(titleLoading);
        rvEmployees.setVisibility(View.GONE);
        tvListIsEmpty.setVisibility(View.GONE);
        spinnerSpecialities.setVisibility(View.GONE);
        pbLoadEmployees.setVisibility(View.VISIBLE);
        tvLoadEmployees.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUpdatingProgress() {
        inUpdatingProgress = false;
        setTitle(null);
        spinnerSpecialities.setVisibility(View.VISIBLE);
        pbLoadEmployees.setVisibility(View.GONE);
        tvLoadEmployees.setVisibility(View.GONE);
    }

    @Override
    public void notifyUpdatingSuccess() {
        Toast.makeText(getContext(), R.string.message_updating_success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmployeeDetailsView(Employee employee) {
        navigator.navigateToEmployeeDetails(employee);
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
        Employee employee = employeesAdapter.getItem(position);
        mvpPresenter.navigateToEmployeeDetails(employee);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedSpecialityPosition = i;
        showEmployeesWithSelectedSpecialityOrEmptyList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void showSpecialities() {
        if (specialitiesAdapter == null) {
            specialitiesAdapter = new ArrayAdapter<>(getContext(), R.layout.item_speciality_selected, specialities);
            specialitiesAdapter.setDropDownViewResource(R.layout.item_speciality);
            spinnerSpecialities.setAdapter(specialitiesAdapter);
        } else if (spinnerSpecialities.getAdapter() == null) {
            spinnerSpecialities.setAdapter(specialitiesAdapter);
        } else {
            specialitiesAdapter.clear();
            specialitiesAdapter.addAll(specialities);
            specialitiesAdapter.notifyDataSetChanged();
        }

        spinnerSpecialities.setOnItemSelectedListener(this);
        spinnerSpecialities.setSelection(selectedSpecialityPosition);
    }

    private void showEmployeesWithSelectedSpecialityOrEmptyList() {
        Speciality speciality = (Speciality) spinnerSpecialities.getSelectedItem();
        List<Employee> pickedEmployees = pickEmployeesBySpeciality(speciality);
        if (pickedEmployees.size() != 0)
            showEmployees(pickedEmployees);
        else
            showEmptyList();
    }

    private void showEmployees(List<Employee> employees) {
        rvEmployees.setVisibility(View.VISIBLE);
        tvListIsEmpty.setVisibility(View.GONE);

        if (employeesAdapter == null) {
            employeesAdapter = new EmployeesAdapter(formatUtil, employees, this);
            rvEmployees.setAdapter(employeesAdapter);
        } else if (rvEmployees.getAdapter() == null)
            rvEmployees.setAdapter(employeesAdapter);
        else
            employeesAdapter.setEmployees(employees);
    }

    private void showEmptyList() {
        rvEmployees.setVisibility(View.GONE);
        tvListIsEmpty.setVisibility(View.VISIBLE);
    }

    private List<Speciality> pickSpecialitiesFromEmployees(List<Employee> employees) {
        Set<Speciality> specialities = new TreeSet<>();
        for (Employee employee : employees)
            specialities.addAll(employee.getSpecialities());
        return new ArrayList<>(specialities);
    }

    private List<Employee> pickEmployeesBySpeciality(Speciality speciality) {
        List<Employee> pickedEmployees = new ArrayList<>();
        for (Employee employee : employees)
            for (Speciality employeeSpeciality : employee.getSpecialities())
                if (employeeSpeciality.equals(speciality))
                    pickedEmployees.add(employee);
        return pickedEmployees;
    }
}
