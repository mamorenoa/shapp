package com.mam.shapp.data.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerHeroResponse {
    @SerializedName("superheroes")
    private List<ServerHero> heroes;

    public List<ServerHero> getHeroes() {
        return heroes;
    }
}
