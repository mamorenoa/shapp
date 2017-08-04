package com.mam.shapp.injector.module;

import com.mam.shapp.data.HeroesRepositoryImpl;
import com.mam.shapp.data.api.services.ApiService;
import com.mam.shapp.domain.interactors.common.InteractorExecutor;
import com.mam.shapp.domain.interactors.common.UiThreadHandler;
import com.mam.shapp.domain.interactors.heroes.GetHeroesInteractor;
import com.mam.shapp.domain.repository.HeroesRepository;
import com.mam.shapp.presentation.common.BaseActivity;
import com.mam.shapp.presentation.heroes.HeroesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HeroesModule extends ActivityModule {

    public HeroesModule(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Provides
    public HeroesPresenter provideHeroesPresenter(InteractorExecutor executor, GetHeroesInteractor getHeroesInteractor) {
        return new HeroesPresenter(executor, getHeroesInteractor);
    }

    @Provides
    public GetHeroesInteractor providesGetHeroesInteractor(HeroesRepository heroesRepository, UiThreadHandler uiThreadHandler) {
        return new GetHeroesInteractor(heroesRepository, uiThreadHandler);
    }

    @Provides
    public HeroesRepository providesUserRepository(ApiService apiHeroes) {
        HeroesRepository heroesRepository = new HeroesRepositoryImpl(apiHeroes);
        return heroesRepository;
    }
}
