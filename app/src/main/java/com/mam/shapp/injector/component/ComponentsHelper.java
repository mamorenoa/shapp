package com.mam.shapp.injector.component;

import com.mam.shapp.HeroesApplication;
import com.mam.shapp.injector.module.AppModule;
import com.mam.shapp.injector.module.NetworkModule;

public class ComponentsHelper {

    private HeroesApplication application;

    private AppComponent appComponent;

    public static ComponentsHelper createInstance(HeroesApplication application) {
        return new ComponentsHelper(application);
    }

    private ComponentsHelper(HeroesApplication application) {
        this.application = application;
    }

    public ComponentsHelper initialize() {
        this.buildAppComponent();
        return this;
    }

    private void buildAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            initialize();
        }
        return appComponent;
    }
}