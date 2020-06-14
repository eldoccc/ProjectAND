package com.example.projectand.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.projectand.Dao.FavoriteDao;
import com.example.projectand.POJO.Favorite;
import com.example.projectand.databases.FavoriteDatabase;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDao favoriteDao;
    private static FavoriteRepository instance;
    private LiveData<List<Favorite>> allFavorites;

    private FavoriteRepository(Application application){
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        allFavorites = favoriteDao.getAllFavorites();
    }

    public static synchronized FavoriteRepository getInstance(Application application){
        if(instance == null)
            instance = new FavoriteRepository(application);
        return instance;
    }

    public LiveData<List<Favorite>> getAllFavorites(){
        return allFavorites;
    }


    public void insert(Favorite favorite) {
        new InsertFavoriteAsync(favoriteDao).execute(favorite);
    }


    private static class InsertFavoriteAsync extends AsyncTask<Favorite,Void,Void> {
        private FavoriteDao favoriteDao;

        private InsertFavoriteAsync(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.insert(favorites[0]);
            return null;
        }
    }
    private static class DeleteAllFavoritesAsync extends AsyncTask<Void,Void,Void> {
        private FavoriteDao favoriteDao;

        private DeleteAllFavoritesAsync(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.deleteAllFavorites();
            return null;
        }
    }

    public void deleteAllFavorites(){
        new DeleteAllFavoritesAsync(favoriteDao).execute();
    }


    public void delete(Favorite favorite) {
        new DeleteFavoriteAsync(favoriteDao).execute(favorite);
    }


    private static class DeleteFavoriteAsync extends AsyncTask<Favorite,Void,Void> {
        private FavoriteDao favoriteDao;

        private DeleteFavoriteAsync(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.delete(favorites[0]);
            return null;
        }
    }

}
