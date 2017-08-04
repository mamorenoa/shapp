package com.mam.shapp.presentation.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mam.shapp.HeroesApplication;
import com.mam.shapp.injector.component.ComponentsHelper;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setModule();
        setLayout();
        injectViews();
    }

    public ComponentsHelper getComponentsHelper() {
        return ((HeroesApplication) getApplication()).getComponentsHelper();
    }

    protected void setLayout() {
        setContentView(getActivityLayout());
    }

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected abstract void setModule();

    protected abstract int getActivityLayout();
}
