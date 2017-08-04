package com.mam.shapp.injector.module;

import android.app.Application;

import com.mam.shapp.R;
import com.mam.shapp.data.api.services.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("socketTimeout")
    public int provideSocketTimeout(Application context) {
        return context.getResources().getInteger(R.integer.socketTimeout);
    }

    @Provides
    @Singleton
    @Named("connectionTimeout")
    public int provideConnectionTimeout(Application context) {
        return context.getResources().getInteger(R.integer.connectionTimeout);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, @Named("socketTimeout") int socketTimeout, @Named("connectionTimeout") int connectionTimeout) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
        clientBuilder.readTimeout(socketTimeout, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(connectionTimeout, TimeUnit.SECONDS);
        OkHttpClient client = clientBuilder.build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(OkHttpClient client) {
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl(ApiService.API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitClient;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}

