package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.presentation.navigation.EmployeeDetailsNavigator;
import com.nodomain.employeeapp.presentation.navigation.EmployeeListNavigator;
import com.nodomain.employeeapp.presentation.navigation.MainNavigator;

import dagger.Module;
import dagger.Provides;


@Module
public class NavigatorsModule {

    @Provides
    EmployeeListNavigator provideEmployeeListNavigator(MainNavigator navigator) {
        return navigator;
    }

    @Provides
    EmployeeDetailsNavigator provideEmployeeDetailsNavigator(MainNavigator navigator) {
        return navigator;
    }
}
