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
import com.example.lesson2.databinding.FragmentInformationBinding;
import com.example.lesson2.databinding.FragmentListRecyclerBinding;
import com.example.lesson2.remote.RetrofitBuilder;
import com.example.lesson2.ui.film_list.adapter.GhibliAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationFragment extends Fragment {

    private String filmId;
    private FragmentInformationBinding binding;

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
        binding = FragmentInformationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitBuilder.getInstance().getFilmById(filmId).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful() && response.body() !=null){
                    binding.textFilmTitle.setText(response.body().getTitle());
                    binding.originalTextFilmTitle.setText(response.body().getOriginalTitle());
                    binding.descriptionTextFilm.setText(response.body().getDescription());

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
}