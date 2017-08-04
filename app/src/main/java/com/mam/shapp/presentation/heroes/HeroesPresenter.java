package com.mam.shapp.presentation.heroes;

import com.mam.shapp.domain.interactors.common.InteractorExecutor;
import com.mam.shapp.domain.interactors.heroes.GetHeroesInteractor;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.presentation.common.AbstractPresenter;
import com.mam.shapp.presentation.common.BaseView;

import java.util.List;

public class HeroesPresenter extends AbstractPresenter implements GetHeroesInteractor.Callback {

    private InteractorExecutor executor;
    private HeroesView heroesView;
    private GetHeroesInteractor getHeroesInteractor;

    public HeroesPresenter(InteractorExecutor executor, GetHeroesInteractor getHeroesInteractor) {
        this.executor = executor;
        this.getHeroesInteractor = getHeroesInteractor;
    }

    public void onViewAttached(BaseView view) {
        heroesView = (HeroesView) view;
    }

    public void onViewDetached() {
        heroesView = null;
    }

    public void getHeroes() {
        heroesView.showLoading();
        getHeroesInteractor.setCallback(this);
        executor.execute(getHeroesInteractor);
    }

    @Override
    public void connectionError() {
        heroesView.hideLoading();
        heroesView.showConnectionError();
    }

    @Override
    public void defaultError() {
        heroesView.hideLoading();
        heroesView.showDefaultError();
    }

    @Override
    public void onGetHeroesSuccess(List<Hero> heroes) {
        heroesView.hideLoading();
        heroesView.showHeroes(heroes);
    }

    @Override
    public void onGetHeroesError() {
        heroesView.hideLoading();
        heroesView.showDefaultError();
    }
}
