package com.example.projectand.POJO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectand.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecipeDetailsResponse {
    private String name;
    String thumbnail_url;
    private String yields;
    private Sections sections;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Recipe getRecipeDetails(){
        return new Recipe(name,sections.concat(), R.drawable.placeholder_1200x500, LocalDate.now().toString(),0,yields,"user");
    }



    static class Sections{
        ArrayList<String> name;


        String concat(){
            StringBuilder chain = null;
            for(int i = 0 ; i < name.size() ; i++){
                chain.append(name.get(i)).append("\n");
            }
            return chain.toString();

        }
    }
}
