package com.example.projectand.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectand.POJO.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insert(Favorite fav);

    @Delete
    void delete(Favorite fav);

    @Query("DELETE FROM favorite_table")
    void deleteAllFavorites();

    @Query("SELECT * FROM favorite_table ORDER BY priority DESC")
    LiveData<List<Favorite>> getAllFavorites();

}
