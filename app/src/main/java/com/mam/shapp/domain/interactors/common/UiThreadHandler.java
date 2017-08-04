package com.mam.shapp.domain.interactors.common;

public interface UiThreadHandler {
    void post(Runnable r);
}
