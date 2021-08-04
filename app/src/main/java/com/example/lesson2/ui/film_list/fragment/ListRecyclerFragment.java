package com.example.lesson2.ui.film_list.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson2.App;
import com.example.lesson2.R;
import com.example.lesson2.data.model.Films;
import com.example.lesson2.databinding.FragmentListRecyclerBinding;
import com.example.lesson2.remote.RetrofitBuilder;
import com.example.lesson2.ui.film_list.adapter.GhibliAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRecyclerFragment extends Fragment implements GhibliAdapter.Callback, GhibliAdapter.SaveRoom {

    private final GhibliAdapter adapter = new GhibliAdapter(this, this);
    private FragmentListRecyclerBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListRecyclerBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initView(view);
        getFilmsFromRetrofit();

    }

    private void getFilmsFromRetrofit() {
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.addItems(response.body());
                    Log.d("tag", "success:" + response.body());

                } else {
                    Log.d("tag", "error!:" + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Log.e("tag", t.getLocalizedMessage());
            }
        });
    }

    private void initView(View view) {
        binding.filmRecyclerView.setAdapter(adapter);
    }

    @Override
    public void filmClick(Films films) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        Bundle bundle = new Bundle();
        bundle.putString("filmId", films.getId());
        Log.d("tag", "success: " + films.getId());
        navController.navigate(R.id.informationFragment, bundle);
    }

    @Override
    public void click(Films films) {
        App.getAppDatabase().filmDao().insert(new Films(films.getTitle(), films.getProducer(),films.getDescription(),films.getOriginalTitle()));
        Log.d("tag", "success: " + films.getId());

    }
}