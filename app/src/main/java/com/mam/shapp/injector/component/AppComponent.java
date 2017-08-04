package com.mam.shapp.injector.component;

import android.app.Application;

import com.mam.shapp.data.api.services.ApiService;
import com.mam.shapp.domain.interactors.common.InteractorExecutor;
import com.mam.shapp.domain.interactors.common.UiThreadHandler;
import com.mam.shapp.injector.module.AppModule;
import com.mam.shapp.injector.module.HeroDetailModule;
import com.mam.shapp.injector.module.HeroesModule;
import com.mam.shapp.injector.module.NetworkModule;
import com.mam.shapp.presentation.navigator.Navigator;
import com.mam.shapp.utils.ImageLoader;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class}
)
public interface AppComponent {

    //Submodules
    HeroesComponent plus(HeroesModule module);

    HeroDetailComponent plus(HeroDetailModule module);

    //App general modules
    Application getApplication();

    Navigator getNavigator();

    InteractorExecutor getInteractorExecutor();

    UiThreadHandler getUIThreadHandler();

    ApiService getApiService();

    ImageLoader getImageLoader();
}
