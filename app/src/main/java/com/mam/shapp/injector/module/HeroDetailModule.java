package com.mam.shapp.injector.module;

import com.mam.shapp.presentation.common.BaseActivity;

import dagger.Module;

@Module
public class HeroDetailModule extends ActivityModule {

    public HeroDetailModule(BaseActivity baseActivity) {
        super(baseActivity);
    }
}
