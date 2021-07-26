package com.example.lesson2.ui.film_detail.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson2.App;
import com.example.lesson2.R;
import com.example.lesson2.data.model.Films;
import com.example.lesson2.remote.RetrofitBuilder;
import com.example.lesson2.ui.film_list.adapter.GhibliAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationFragment extends Fragment {

    private TextView textFilmTitle;
    private TextView textOriginalFilmTitle;
    private TextView textFilmDescription;
    private String filmId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            filmId = getArguments().getString("filmId");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        RetrofitBuilder.getInstance().getFilmById(filmId).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful() && response.body() !=null){
                    textFilmTitle.setText(response.body().getTitle());
                    textOriginalFilmTitle.setText(response.body().getOriginalTitle());
                    textFilmDescription.setText(response.body().getDescription());

                }else {
                    Toast.makeText(requireActivity(), "Error!" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Toast.makeText(requireActivity(), "Error!" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void initViews(@NonNull View view) {
        textFilmTitle = view.findViewById(R.id.text_film_title);
        textOriginalFilmTitle = view.findViewById(R.id.original_text_film_title);
        textFilmDescription = view.findViewById(R.id.description_text_film);
    }

}