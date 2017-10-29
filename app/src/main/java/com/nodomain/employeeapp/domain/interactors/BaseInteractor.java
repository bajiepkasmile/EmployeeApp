package com.nodomain.employeeapp.domain.interactors;


import android.os.Handler;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseInteractor<Args> {

    private final Handler mainThreadHandler;
    private final EventBus eventBus;

    protected BaseInteractor(Handler mainThreadHandler, EventBus eventBus) {
        this.mainThreadHandler = mainThreadHandler;
        this.eventBus = eventBus;
    }

    abstract void execute(Args args);

    protected void inBackground(Runnable runnable) {
        //The application is very simple, so there is no need for any ExecutorService's.
        //Just create a new thread for the task
        new Thread(runnable).start();
    }

    protected void onMainThread(Runnable runnable) {
        mainThreadHandler.post(runnable);
    }

    protected void postStickyEvent(Object event) {
        eventBus.postSticky(event);
    }
}
