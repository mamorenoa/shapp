package com.mam.shapp.injector.component;


import com.mam.shapp.injector.module.HeroDetailModule;
import com.mam.shapp.presentation.heroes.detail.HeroDetailActivity;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                HeroDetailModule.class
        }
)
public interface HeroDetailComponent {
    void inject(HeroDetailActivity heroDetailActivity);
}
