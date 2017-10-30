package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.navigation.EmployeeDetailsNavigator;
import com.nodomain.employeeapp.navigation.EmployeeListNavigator;
import com.nodomain.employeeapp.navigation.MainNavigator;

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
