package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.presentation.mvp.presenters.EmployeeDetailsMvpPresenter;
import com.nodomain.employeeapp.presentation.mvp.presenters.EmployeeListMvpPresenter;
import com.nodomain.employeeapp.presentation.mvp.presentersimpl.EmployeeDetailsMvpPresenterImpl;
import com.nodomain.employeeapp.presentation.mvp.presentersimpl.EmployeeListMvpPresenterImpl;

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
