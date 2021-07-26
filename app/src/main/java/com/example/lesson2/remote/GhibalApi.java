package com.example.lesson2.remote;

import com.example.lesson2.data.model.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibalApi {

    @GET("/films")
    Call<List<Films>> getFilms();


    @GET("/films/{id}")
    Call<Films> getFilmById(
            @Path("id") String filmId
    );
}
