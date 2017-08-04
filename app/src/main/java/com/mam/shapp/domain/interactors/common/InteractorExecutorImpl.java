package com.mam.shapp.domain.interactors.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InteractorExecutorImpl implements InteractorExecutor {

    private static final int POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int TIMEOUT = 15;
    private ThreadPoolExecutor mThreadPoolExecutor;

    public InteractorExecutorImpl() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }

    public void execute(final BaseInteractor interactor) {
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.executeInteractor();
            }
        });
    }
}
