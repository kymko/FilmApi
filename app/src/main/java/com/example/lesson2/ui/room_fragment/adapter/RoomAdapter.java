package com.example.lesson2.ui.room_fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson2.R;
import com.example.lesson2.data.model.Films;
import com.example.lesson2.databinding.ListRoomBinding;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.VH>{

    private ListRoomBinding binding;
    private final List<Films>list = new ArrayList<>();
    private clickRoom clickRoom;

    public RoomAdapter(clickRoom clickRoom){
        this.clickRoom = clickRoom;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListRoomBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItems(List<Films>lis){
        list.addAll(lis);
        notifyDataSetChanged();
    }


    public class VH extends RecyclerView.ViewHolder {

        private final ListRoomBinding binding;
        private clickRoom clickRoom;

        public VH(ListRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Films films) {
            binding.filmTitle.setText(films.getTitle());
            binding.filmProducer.setText(films.getProducer());
            binding.getRoot().setOnClickListener(v -> {clickRoom.click(films);});

        }
    }

    public interface clickRoom{
        void click(Films films);
    }
}
