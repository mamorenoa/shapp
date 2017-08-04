package com.mam.shapp;

import android.app.Application;

import com.mam.shapp.injector.component.ComponentsHelper;


public class HeroesApplication extends Application {

    private ComponentsHelper componentsHelper;
    public static HeroesApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    static public HeroesApplication getInstance() {
        return mInstance;
    }

    public ComponentsHelper getComponentsHelper() {
        if (componentsHelper == null) {
            componentsHelper = ComponentsHelper.createInstance(this).initialize();
        }
        componentsHelper.getAppComponent();
        return componentsHelper;
    }
}
