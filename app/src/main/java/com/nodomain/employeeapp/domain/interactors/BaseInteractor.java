package com.nodomain.employeeapp.domain.interactors;


import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;


public abstract class BaseInteractor<Args> {

    private final ExecutorService executorService;
    private final Handler mainThreadHandler;
    private final EventBus eventBus;

    protected BaseInteractor(ExecutorService executorService, Handler mainThreadHandler, EventBus eventBus) {
        this.executorService = executorService;
        this.mainThreadHandler = mainThreadHandler;
        this.eventBus = eventBus;
    }

    abstract public void execute(Args args);

    protected void inBackground(Runnable runnable) {
        executorService.execute(runnable);
    }

    protected void onMainThread(Runnable runnable) {
        mainThreadHandler.post(runnable);
    }

    protected void postStickyEvent(Object event) {
        eventBus.postSticky(event);
    }
}
