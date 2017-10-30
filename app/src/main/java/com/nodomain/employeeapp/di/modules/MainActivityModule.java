package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.di.scopes.PerActivity;
import com.nodomain.employeeapp.navigation.MainNavigator;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    private final MainNavigator navigator;

    public MainActivityModule(MainNavigator navigator) {
        this.navigator = navigator;
    }

    @PerActivity
    @Provides
    MainNavigator provideMainNavigator() {
        return navigator;
    }
}
