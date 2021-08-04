package com.example.lesson2.ui.room_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.lesson2.databinding.FragmentRoomBinding;
import com.example.lesson2.ui.film_list.adapter.GhibliAdapter;
import com.example.lesson2.ui.room_fragment.adapter.RoomAdapter;

import java.util.List;

public class RoomFragment extends Fragment implements RoomAdapter.clickRoom {

    private FragmentRoomBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoomBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        roomInit();
    }

    private void roomInit() {
        App.getAppDatabase().filmDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<Films>>() {
            @Override
            public void onChanged(List<Films> films) {
                initRecycler(films);
            }
        });
    }
    public void initRecycler(List<Films>list){
        RoomAdapter adapter = new RoomAdapter(this);
        adapter.addItems(list);
        binding.filmRecyclerView.setAdapter(adapter);
    }

    @Override
    public void click(Films films) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        Bundle bundle = new Bundle();
        bundle.putString("filmId", films.getId());
        Log.d("tag", "success: " + films.getId());
        navController.navigate(R.id.informationFragment, bundle);
    }
}