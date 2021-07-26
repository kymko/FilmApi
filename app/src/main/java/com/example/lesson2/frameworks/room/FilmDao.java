package com.example.lesson2.frameworks.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lesson2.data.model.Films;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM Films")
    LiveData<List<Films>> getAll();

    @Insert
    void insert(Films films);

}
