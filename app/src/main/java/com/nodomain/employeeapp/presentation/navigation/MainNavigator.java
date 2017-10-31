package com.nodomain.employeeapp.presentation.navigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.presentation.ui.fragments.EmployeeDetailsFragment;
import com.nodomain.employeeapp.presentation.ui.fragments.EmployeeListFragment;


public class MainNavigator implements EmployeeListNavigator, EmployeeDetailsNavigator {

    private final FragmentManager fragmentManager;

    public MainNavigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void navigateToPreviousView() {
        if (hasPreviousView())
            fragmentManager.popBackStack();
    }

    @Override
    public void navigateToEmployeeDetails(Employee employee) {
        replaceFragmentWithAddingToBackStack(EmployeeDetailsFragment.newInstance(employee), "details");
    }

    public void navigateToEmployeeList() {
        replaceFragment(EmployeeListFragment.newInstance());
    }

    public boolean hasPreviousView() {
        return fragmentManager.getBackStackEntryCount() > 0;
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }

    private void replaceFragmentWithAddingToBackStack(Fragment fragment, String name) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(name)
                .commit();
    }
}
