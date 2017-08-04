package com.mam.shapp.data.api.services;

import com.mam.shapp.data.api.response.ServerHeroResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    //Endpoint
    String API = "https://api.myjson.com";

    //Headers
    String CONTENT_TYPE = "Content-Type: application/json";
    String ACCEPT = "Accept: application/json";

    @Headers({CONTENT_TYPE, ACCEPT})
    @GET("bins/bvyob")
    Call<ServerHeroResponse> getHeroes();

}