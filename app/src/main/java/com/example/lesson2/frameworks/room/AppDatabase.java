package com.example.lesson2.frameworks.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lesson2.data.model.Films;

@Database(entities = {Films.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();
}
