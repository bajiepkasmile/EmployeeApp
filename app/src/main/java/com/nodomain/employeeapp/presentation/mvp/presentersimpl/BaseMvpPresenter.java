package com.nodomain.employeeapp.presentation.mvp.presentersimpl;


import com.nodomain.employeeapp.presentation.mvp.presenters.MvpPresenter;
import com.nodomain.employeeapp.presentation.mvp.views.MvpView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;


public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

    private final EventBus eventBus;
    protected V mvpView;

    protected BaseMvpPresenter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void attachMvpView(V mvpView) {
        this.mvpView = mvpView;

        try {
            eventBus.register(this);
        } catch (EventBusException e) {  //when the subscriber does not have any subscription
            e.printStackTrace();
        }
    }

    @Override
    public void detachMvpView() {
        eventBus.unregister(this);
        mvpView = null;
    }

    protected void removeStickyEvent(Object event) {
        eventBus.removeStickyEvent(event);
    }
}
