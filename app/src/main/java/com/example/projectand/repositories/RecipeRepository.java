package com.example.projectand.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectand.Dao.RecipeApi;
import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.POJO.Recipe;
import com.example.projectand.POJO.RecipeDetailsResponse;
import com.example.projectand.POJO.RecipeListResponse;
import com.example.projectand.ServiceGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private static RecipeRepository instance;
    private MutableLiveData<ArrayList<ItemListRecipe>> recipes;
    private MutableLiveData<Recipe> r;

    private RecipeRepository() {
        recipes = new MutableLiveData<>();
        r = new MutableLiveData<>();
    }

    public static synchronized RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<ItemListRecipe>> getRecipe() {
        return recipes;
    }

    public LiveData<Recipe> getR() {
        return r;
    }

    public void updateRecipe(String recipeName) {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipeListResponse> call = recipeApi.getRecipeList("under_30_minutes",recipeName,0,20);
        call.enqueue(new Callback<RecipeListResponse>() {
            @Override
            public void onResponse(Call<RecipeListResponse> call, Response<RecipeListResponse> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    ArrayList<ItemListRecipe> list = response.body().getListRecipe();
                    Log.d("LISTEfinal",list.toString());
                    recipes.postValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecipeListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void updateR(int id){
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipeDetailsResponse> call = recipeApi.getRecipeDetails(id);
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(response.code()==200){
                    
                }
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {

            }
        });
    }
}