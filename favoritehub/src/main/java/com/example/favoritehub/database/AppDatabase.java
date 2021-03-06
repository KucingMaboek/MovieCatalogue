package com.example.favoritehub.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.favoritehub.model.Item;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO itemDAO();
}
