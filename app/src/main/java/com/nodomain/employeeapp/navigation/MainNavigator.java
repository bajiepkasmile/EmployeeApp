package com.nodomain.employeeapp.navigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.ui.fragments.EmployeeDetailsFragment;
import com.nodomain.employeeapp.ui.fragments.EmployeeListFragment;


public class MainNavigator implements EmployeeListNavigator, EmployeeDetailsNavigator {

    private final FragmentManager fragmentManager;

    public MainNavigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void navigateToEmployeeList() {
        replaceFragment(EmployeeListFragment.newInstance());
    }

    @Override
    public void navigateToEmployeeDetails(Employee employee) {
        replaceFragment(EmployeeDetailsFragment.newInstance(employee));
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }
}
