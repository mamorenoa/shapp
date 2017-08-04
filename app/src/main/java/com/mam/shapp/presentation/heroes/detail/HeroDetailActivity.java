package com.mam.shapp.presentation.heroes.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mam.shapp.R;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.injector.module.HeroDetailModule;
import com.mam.shapp.presentation.common.BaseActivity;
import com.mam.shapp.utils.ImageLoader;

import javax.inject.Inject;

import butterknife.Bind;

public class HeroDetailActivity extends BaseActivity {

    public static final String KEY_HEROE_DETAIL = "com.mam.shapp.presentation.heroes.detail.HERO_DETAIL";

    @Inject
    ImageLoader imageLoader;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textNameValue)
    TextView textName;
    @Bind(R.id.textRealNameValue)
    TextView textRealName;
    @Bind(R.id.textHeighValue)
    TextView textHeigh;
    @Bind(R.id.textPowerValue)
    TextView textPower;
    @Bind(R.id.textAbilitiesValue)
    TextView textAbilities;
    @Bind(R.id.textGroupsValue)
    TextView textGroups;
    @Bind(R.id.imageHero)
    ImageView imageHero;

    public static Intent buildIntent(Context context, Hero heroe) {
        Intent intentServiceDetail = new Intent(context, HeroDetailActivity.class);
        Bundle bundleData = new Bundle();
        bundleData.putSerializable(KEY_HEROE_DETAIL, heroe);
        intentServiceDetail.putExtras(bundleData);
        return intentServiceDetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeroData(getDataFromBundle());
    }

    @Override
    protected void setModule() {
        getComponentsHelper().getAppComponent().plus(new HeroDetailModule(this)).inject(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_hero_detail;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setHeroData(Hero hero) {
        if (hero != null) {
            initToolBar("");
            textName.setText(hero.getName());
            textRealName.setText(hero.getRealName());
            textHeigh.setText(hero.getHeight());
            textPower.setText(hero.getPower());
            textAbilities.setText(hero.getAbilities());
            textGroups.setText(hero.getGroups());
            if (!TextUtils.isEmpty(hero.getPhoto())) {
                imageLoader.loadImage(hero.getPhoto(), imageHero, R.drawable.marvelplaceholder, true);
            }
        }
    }

    private void initToolBar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(title);
    }

    private Hero getDataFromBundle() {
        Bundle bundleData = getIntent().getExtras();
        Hero heroe = null;
        if (bundleData != null) {
            heroe = (Hero) bundleData.getSerializable(KEY_HEROE_DETAIL);
        }
        return heroe;
    }
}
