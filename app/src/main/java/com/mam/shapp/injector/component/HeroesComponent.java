package com.mam.shapp.injector.component;


import com.mam.shapp.injector.module.HeroesModule;
import com.mam.shapp.presentation.heroes.HeroesActivity;
import com.mam.shapp.presentation.heroes.HeroesPresenter;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                HeroesModule.class
        }
)
public interface HeroesComponent {
    void inject(HeroesActivity heroesActivity);
    HeroesPresenter getHeroesPresenter();
}
