package com.mam.shapp.data.mappers;


import com.mam.shapp.data.api.response.ServerHero;
import com.mam.shapp.domain.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroMapper {

    public static List<Hero> serverToDomain(List<ServerHero> serverHeroes){
        List<Hero> heroes = new ArrayList<>();
        for (ServerHero serverHero : serverHeroes){
            heroes.add(serverToDomain(serverHero));
        }
        return heroes;
    }

    public static Hero serverToDomain(ServerHero serverHero) {
        Hero heroe = new Hero();
        heroe.setName(serverHero.getName());
        heroe.setRealName(serverHero.getRealName());
        heroe.setAbilities(serverHero.getAbilities());
        heroe.setGroups(serverHero.getGroups());
        heroe.setHeight(serverHero.getHeight());
        heroe.setPhoto(serverHero.getPhoto());
        heroe.setPower(serverHero.getPower());
        return heroe;
    }

}
