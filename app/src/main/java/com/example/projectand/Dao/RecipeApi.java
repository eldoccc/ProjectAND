package com.example.projectand.Dao;

import com.example.projectand.POJO.RecipeDetailsResponse;
import com.example.projectand.POJO.RecipeListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipeApi {

    @Headers({
            "x-rapidapi-host: tasty.p.rapidapi.com",
            "x-rapidapi-key: eb42af94f7msh0a1b1d4e4702d06p1ec701jsn58c8b93f472c"
    })
    @GET("list")
    Call<RecipeListResponse> getRecipeList(
            @Query("tags") String tags,
            @Query("q") String name,
            @Query("from") int from,
            @Query("sizes") int sizes);


    @GET("detail")
    Call<RecipeDetailsResponse>getRecipeDetails(@Query("id") int id);
}
