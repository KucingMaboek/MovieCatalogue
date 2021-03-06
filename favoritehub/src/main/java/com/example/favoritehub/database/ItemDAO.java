package com.example.favoritehub.database;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.favoritehub.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Item item);

    @Update
    int updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM tItem WHERE category = 'movie'")
    Item[] selectAllMovie();

    @Query("SELECT * FROM tItem WHERE category = 'movie'")
    Cursor selectMovieProvider();

    @Query("SELECT * FROM tItem WHERE category = 'tvshow'")
    Item[] selectAllTvShow();

    @Query("SELECT * FROM tItem WHERE category = 'tvshow'")
    Cursor selectTvShowProvider();


    @Query("SELECT * FROM tItem WHERE title = :nama")
    List<Item> favoriteChecker(String nama);
}
