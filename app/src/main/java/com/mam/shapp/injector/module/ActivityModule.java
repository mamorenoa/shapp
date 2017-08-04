package com.mam.shapp.injector.module;


import com.mam.shapp.presentation.common.BaseActivity;
import com.mam.shapp.presentation.widget.dialog.DialogAbstract;
import com.mam.shapp.presentation.widget.dialog.DialogAbstractImpl;
import com.mam.shapp.presentation.widget.spinner.SpinnerLoading;
import com.mam.shapp.presentation.widget.spinner.SpinnerLoadingImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity provideActivity() {
        return this.activity;
    }

    @Provides
    public SpinnerLoading provideSpinnerLoading() {
        return new SpinnerLoadingImpl(activity);
    }

    @Provides
    public DialogAbstract provideDialog() {
        return new DialogAbstractImpl();
    }
}
