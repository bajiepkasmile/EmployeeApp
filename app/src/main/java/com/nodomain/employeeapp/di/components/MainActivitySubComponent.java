package com.nodomain.employeeapp.di.components;


import com.nodomain.employeeapp.di.modules.MainActivityModule;
import com.nodomain.employeeapp.di.modules.NavigatorsModule;
import com.nodomain.employeeapp.di.modules.PresentersModule;
import com.nodomain.employeeapp.di.scopes.PerActivity;
import com.nodomain.employeeapp.presentation.ui.fragments.EmployeeDetailsFragment;
import com.nodomain.employeeapp.presentation.ui.fragments.EmployeeListFragment;

import dagger.Subcomponent;


@PerActivity
@Subcomponent(
        modules = {
                MainActivityModule.class,
                PresentersModule.class,
                NavigatorsModule.class
        }
)
public interface MainActivitySubComponent {

    void inject(EmployeeListFragment fragment);

    void inject(EmployeeDetailsFragment fragment);
}
