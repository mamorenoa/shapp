package com.mam.shapp.data;

import com.google.gson.JsonSyntaxException;
import com.mam.shapp.data.api.response.ServerHeroResponse;
import com.mam.shapp.data.api.services.ApiService;
import com.mam.shapp.data.exceptions.heroes.GetHeroesException;
import com.mam.shapp.data.exceptions.io.ConnectionException;
import com.mam.shapp.data.mappers.HeroMapper;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.domain.repository.HeroesRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class HeroesRepositoryImpl extends BaseRepository implements HeroesRepository {

    public HeroesRepositoryImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public List<Hero> get() throws GetHeroesException, ConnectionException {
        List<Hero> heroes;
        ServerHeroResponse serverHeroResponse;
        try {
            Response<ServerHeroResponse> serverResponse = apiService.getHeroes().execute();
            if (serverResponse.isSuccessful()) {
                serverHeroResponse = serverResponse.body();
                heroes = HeroMapper.serverToDomain(serverHeroResponse.getHeroes());
            } else {
                throw new GetHeroesException();
            }
        } catch (IOException ioException) {
            throw new ConnectionException();
        } catch (JsonSyntaxException jsonException) {
            throw new ConnectionException();
        }
        return heroes;
    }
}
