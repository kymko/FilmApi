package com.example.lesson2.remote.film;


import android.util.Log;

import com.example.lesson2.data.model.Films;
import com.example.lesson2.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmStorage {

    public void getFilm(String filmId, Callbacks<Films> callbacks) {
        RetrofitBuilder.getInstance().getFilmById(filmId).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callbacks.success(response.body());
                } else {
                    callbacks.failure("Status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                callbacks.failure(t.getLocalizedMessage());
            }
        });
    }

  public interface Callbacks<Data> {
        void success(Data films);
       default void failure(String message){
           Log.e("Callbacks", message);
       }
    }
}
