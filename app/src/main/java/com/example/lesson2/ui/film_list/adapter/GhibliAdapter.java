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

import java.util.ArrayList;
import java.util.List;

public class GhibliAdapter extends RecyclerView.Adapter<GhibliAdapter.ViewHolder> {

    List<Films> list = new ArrayList<>();
    private  Callback callback;
    private SaveRoom saveRoom;

    public GhibliAdapter(Callback callback,SaveRoom saveRoom) {
        this.callback = callback;
        this.saveRoom = saveRoom;
    }
    public GhibliAdapter(){}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view, callback,saveRoom);
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
        list.clear();
        list.addAll(film);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitleFilm;
        private final TextView textDirectorFilm;
        private ImageView imageView;
        private Callback callback;
        private SaveRoom saveRoom;

        public ViewHolder(@NonNull View itemView,Callback callback,SaveRoom saveRoom) {
            super(itemView);
            this.callback = callback;
            this.saveRoom = saveRoom;

            textTitleFilm = itemView.findViewById(R.id.text_title_film);
            textDirectorFilm = itemView.findViewById(R.id.text_director_film);
            imageView = itemView.findViewById(R.id.image_save_to_room);

        }

        public void onBind(Films film) {
            textTitleFilm.setText(film.getTitle());
            textDirectorFilm.setText(film.getProducer());


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveRoom.click(film);
                }
            });
            textTitleFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.filmClick(film);
                }
            });

        }
    }
   public interface Callback{
        void filmClick(Films films);
    }
    public interface SaveRoom{
        void click(Films films);
    }
}
