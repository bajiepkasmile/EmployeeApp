package com.nodomain.employeeapp.presentation.mvp.presenters;


import com.nodomain.employeeapp.presentation.mvp.views.MvpView;


public interface MvpPresenter<V extends MvpView> {

    void attachMvpView(V mvpView);

    void detachMvpView();
}
