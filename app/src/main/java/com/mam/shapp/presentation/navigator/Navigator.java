package com.mam.shapp.presentation.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.presentation.heroes.HeroesActivity;
import com.mam.shapp.presentation.heroes.detail.HeroDetailActivity;


public class Navigator {

    public interface From {
        int NOT_FOLLOW = -1;
    }

    public void navigate(@NonNull Context context, int requestCode, ActivityOptionsCompat options, @Nullable Intent intent) {
        ((HeroesActivity) context).startActivityForResult(intent, requestCode, options.toBundle());
    }

    public void navigateToHeroDetail(Context context, Hero hero, View viewShared) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, viewShared, "hero");
        navigate(context, From.NOT_FOLLOW, options, HeroDetailActivity.buildIntent(context, hero));
    }
}
