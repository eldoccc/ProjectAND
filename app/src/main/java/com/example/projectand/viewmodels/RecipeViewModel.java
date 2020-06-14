package com.example.projectand.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.repositories.RecipeRepository;

import java.util.ArrayList;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository repository;

    public RecipeViewModel(){
        repository = RecipeRepository.getInstance();
    }

    public LiveData<ArrayList<ItemListRecipe>> getRecipe() {
        return repository.getRecipe();
    }

    public void updateRecipe(String s) {
        repository.updateRecipe(s);
    }
}
