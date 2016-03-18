package com.lukaszbyjos.popularmovies.network;


import com.lukaszbyjos.popularmovies.OkHttpSingleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RestClient {
    private static final String BASE_URL = "https://api.themoviedb.org";

    private static MoviesService moviesService;

    static {
        setupRestClient();
    }

    private RestClient() {
    }


    public static MoviesService get() {
        return moviesService;
    }

    private static void setupRestClient() {
        final OkHttpClient okHttpClient = OkHttpSingleton.getOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
        moviesService = retrofit.create(MoviesService.class);
    }

    public MoviesService getApiService() {
        return moviesService;
    }
}
