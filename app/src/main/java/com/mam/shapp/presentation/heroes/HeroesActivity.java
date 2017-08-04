package com.mam.shapp.presentation.heroes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mam.shapp.R;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.injector.module.HeroesModule;
import com.mam.shapp.presentation.common.BaseActivity;
import com.mam.shapp.presentation.navigator.Navigator;
import com.mam.shapp.presentation.widget.dialog.DialogAbstract;
import com.mam.shapp.presentation.widget.spinner.SpinnerLoading;
import com.mam.shapp.utils.ImageLoader;
import com.mam.shapp.utils.animations.AnimationsUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class HeroesActivity extends BaseActivity implements HeroesView {

    @Inject
    Navigator navigator;
    @Inject
    HeroesPresenter heroesPresenter;
    @Inject
    SpinnerLoading spinnerLoading;
    @Inject
    DialogAbstract dialog;
    @Inject
    ImageLoader imageLoader;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerHeroes)
    RecyclerView recyclerHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        heroesPresenter.onViewAttached(this);
        initToolBar();
        heroesPresenter.getHeroes();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        heroesPresenter.onViewDetached();
    }

    @Override
    protected void setModule() {
        getComponentsHelper().getAppComponent().plus(new HeroesModule(this)).inject(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_heroes;
    }

    @Override
    public void showHeroes(List<Hero> heroes) {
        recyclerHeroes.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerHeroes.setLayoutManager(layoutManager);
        HeroesAdapter heroesAdapter = new HeroesAdapter(heroes, imageLoader);
        heroesAdapter.setClickListener(new HeroesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hero hero, ImageView imageShared) {
                navigator.navigateToHeroDetail(HeroesActivity.this, hero, imageShared);
            }
        });
        recyclerHeroes.setAdapter(heroesAdapter);
        AnimationsUtils.animateRecycler(recyclerHeroes);
    }

    @Override
    public void showLoading() {
        spinnerLoading.show();
    }

    @Override
    public void hideLoading() {
        spinnerLoading.dismiss();
    }

    @Override
    public void showConnectionError() {
        dialog.showSimple(this, getString(R.string.common_info_dialog), getString(R.string.common_connection_error_dialog), getString(R.string.common_continue_dialog));
    }

    @Override
    public void showDefaultError() {
        dialog.showSimple(this, getString(R.string.common_info_dialog), getString(R.string.common_default_error_dialog), getString(R.string.common_continue_dialog));
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.app_name));
    }
}
