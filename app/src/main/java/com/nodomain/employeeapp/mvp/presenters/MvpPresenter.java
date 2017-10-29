package com.nodomain.employeeapp.mvp.presenters;


import com.nodomain.employeeapp.mvp.views.MvpView;


public interface MvpPresenter<V extends MvpView> {

    void attachMvpView(V mvpView);

    void detachMvpView();
}
