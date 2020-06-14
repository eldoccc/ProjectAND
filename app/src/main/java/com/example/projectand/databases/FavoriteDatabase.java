package com.example.projectand.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectand.Dao.FavoriteDao;
import com.example.projectand.POJO.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;
    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "favorite_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}