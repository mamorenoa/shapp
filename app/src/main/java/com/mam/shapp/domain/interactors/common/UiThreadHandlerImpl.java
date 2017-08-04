package com.mam.shapp.domain.interactors.common;
import android.os.Handler;

public class UiThreadHandlerImpl implements UiThreadHandler {

    private Handler handler;

    public UiThreadHandlerImpl(){
        handler = new Handler();
    }

    @Override
    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }
}
