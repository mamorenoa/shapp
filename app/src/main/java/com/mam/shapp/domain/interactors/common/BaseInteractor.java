package com.mam.shapp.domain.interactors.common;

public abstract class BaseInteractor {

    public UiThreadHandler uiThreadHandler;

    public BaseInteractor(UiThreadHandler threadHandler) {
        this.uiThreadHandler = threadHandler;
    }

    public abstract void executeInteractor();

    public void onConnectionError(final BaseInteractorCallback callback) {
        this.uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.connectionError();
            }
        });
    }

    public void onDefaultError(final BaseInteractorCallback callback) {
        this.uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.defaultError();
            }
        });
    }
}

