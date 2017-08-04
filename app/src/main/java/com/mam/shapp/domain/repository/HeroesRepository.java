package com.mam.shapp.domain.repository;

import com.mam.shapp.data.exceptions.heroes.GetHeroesException;
import com.mam.shapp.data.exceptions.io.ConnectionException;
import com.mam.shapp.domain.model.Hero;

import java.util.List;

public interface HeroesRepository {
    List<Hero> get() throws ConnectionException, GetHeroesException;
}

