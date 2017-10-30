package com.nodomain.employeeapp.di.components;


import com.nodomain.employeeapp.di.modules.ApplicationModule;
import com.nodomain.employeeapp.di.modules.DataSourcesModule;
import com.nodomain.employeeapp.di.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataSourcesModule.class
        }
)
public interface ApplicationComponent {

    MainActivitySubComponent plusMainActivitySubComponent(MainActivityModule module);
}
