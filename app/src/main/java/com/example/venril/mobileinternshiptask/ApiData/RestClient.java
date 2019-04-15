package com.example.venril.mobileinternshiptask.ApiData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static volatile Retrofit INSTANCE;

    private RestClient() {
    }

    public static Retrofit getInstance() {
        if(INSTANCE == null) {
            synchronized (RestClient.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
