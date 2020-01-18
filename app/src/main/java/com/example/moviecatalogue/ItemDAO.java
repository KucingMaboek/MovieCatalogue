package com.example.moviecatalogue;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviecatalogue.model.Item;

@Dao
public interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Item item);

    @Update
    int updateItem(Item item);

    @Delete
    int deleteItem(Item item);

    @Query("SELECT * FROM tItem")
    Item[] selectAllItem();

    @Query("SELECT * FROM tItem WHERE id = :id LIMIT 1")
    Item selectItemDetail(int id);
}
