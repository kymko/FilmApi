package com.example.lesson2.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder(){}

    private static GhibalApi instance;

    public static GhibalApi getInstance(){
        if (instance == null){
            instance = createRetrofit();
        }
        return instance;
    }

    private static GhibalApi createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GhibalApi.class);

    }

}
