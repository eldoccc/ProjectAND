package com.example.projectand.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectand.POJO.Favorite;
import com.example.projectand.repositories.FavoriteRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteRepository repository;

    public FavoriteViewModel(Application app) {
        super(app);
        repository = FavoriteRepository.getInstance(app);
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return repository.getAllFavorites();
    }

    public void insert(Favorite favorite) {
        repository.insert(favorite);
    }

    public void delete(Favorite favorite) {
        repository.delete(favorite);
    }


    public void deleteAllFavorites(){
        repository.deleteAllFavorites();
    }


}