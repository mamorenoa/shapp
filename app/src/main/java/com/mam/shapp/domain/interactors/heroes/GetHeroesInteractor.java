
package com.mam.shapp.domain.interactors.heroes;

import com.mam.shapp.data.exceptions.heroes.GetHeroesException;
import com.mam.shapp.data.exceptions.io.ConnectionException;
import com.mam.shapp.domain.interactors.common.BaseInteractor;
import com.mam.shapp.domain.interactors.common.BaseInteractorCallback;
import com.mam.shapp.domain.interactors.common.UiThreadHandler;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.domain.repository.HeroesRepository;

import java.util.List;

public class GetHeroesInteractor extends BaseInteractor {

    private HeroesRepository heroesRepository;
    private Callback callback;


    public interface Callback extends BaseInteractorCallback {
        void onGetHeroesSuccess(List<Hero> heroes);

        void onGetHeroesError();
    }

    public GetHeroesInteractor(HeroesRepository heroesRepository, UiThreadHandler uiThreadHandler) {
        super(uiThreadHandler);
        this.heroesRepository = heroesRepository;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    @Override
    public void executeInteractor() {
        try {
            List<Hero> heroes = heroesRepository.get();
            if (heroes != null) {
                notifyGetHeroesSuccess(heroes);
            } else {
                onDefaultError(callback);
            }
        } catch (GetHeroesException getHeroesException) {
            notifyGetHeroesError();
        } catch (ConnectionException connectionException) {
            onConnectionError(callback);
        }
    }

    private void notifyGetHeroesSuccess(final List<Hero> heroes) {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetHeroesSuccess(heroes);
            }
        });
    }

    private void notifyGetHeroesError() {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetHeroesError();
            }
        });
    }
}
