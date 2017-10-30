package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.mvp.presenters.EmployeeDetailsMvpPresenter;
import com.nodomain.employeeapp.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.mvp.presentersimpl.EmployeeDetailsMvpPresenterImpl;
import com.nodomain.employeeapp.mvp.presentersimpl.EmployeeListMvpPresenterImpl;

import dagger.Module;
import dagger.Provides;


@Module
public class PresentersModule {

    @Provides
    EmployeeListMvpPresenter provideEmployeeListMvpPresenter(EmployeeListMvpPresenterImpl presenterImpl) {
        return presenterImpl;
    }

    @Provides
    EmployeeDetailsMvpPresenter provideEmployeeDetailsMvpPresenter(EmployeeDetailsMvpPresenterImpl presenterImpl) {
        return presenterImpl;
    }
}
