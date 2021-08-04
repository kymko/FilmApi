package com.example.lesson2.ui.film_list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson2.R;
import com.example.lesson2.data.model.Films;
import com.example.lesson2.databinding.ItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class GhibliAdapter extends RecyclerView.Adapter<GhibliAdapter.ViewHolder> {

    List<Films> list = new ArrayList<>();
    private Callback callback;
    private SaveRoom saveRoom;
    private ItemListBinding binding;

    public GhibliAdapter(Callback callback, SaveRoom saveRoom) {
        this.callback = callback;
        this.saveRoom = saveRoom;
    }

    public GhibliAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, callback, saveRoom, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Films> film) {
        list.addAll(film);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemListBinding binding;
        private Callback callback;
        private SaveRoom saveRoom;

        public ViewHolder(@NonNull ItemListBinding itemView, Callback callback, SaveRoom saveRoom, ItemListBinding binding) {
            super(itemView.getRoot());
            this.callback = callback;
            this.saveRoom = saveRoom;
            this.binding = binding;
        }

        public void onBind(Films film) {
            binding.textTitleFilm.setText(film.getTitle());
            binding.textDirectorFilm.setText(film.getProducer());
            binding.imageSaveToRoom.setOnClickListener(v -> saveRoom.click(film));
            binding.textTitleFilm.setOnClickListener(v -> callback.filmClick(film));

        }
    }

    public interface Callback {
        void filmClick(Films films);
    }

    public interface SaveRoom {
        void click(Films films);
    }
}
