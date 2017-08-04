package com.mam.shapp.presentation.heroes;


import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.presentation.common.BaseView;

import java.util.List;

public interface HeroesView extends BaseView {

    void showHeroes(List<Hero> heroes);

    void showDefaultError();

    void showConnectionError();
}
