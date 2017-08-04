package com.mam.shapp.data;


import com.mam.shapp.data.api.services.ApiService;

public abstract class BaseRepository {

    protected ApiService apiService;

    public BaseRepository(ApiService apiService) {
        this.apiService = apiService;
    }

}
