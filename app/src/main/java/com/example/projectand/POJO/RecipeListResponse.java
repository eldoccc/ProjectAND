package com.example.projectand.POJO;

import com.example.projectand.R;

import java.util.ArrayList;

public class RecipeListResponse {
   private static int count;
   private static ArrayList<RecipeResponse> results;

   public RecipeListResponse(int count, ArrayList<RecipeResponse> results) {
      RecipeListResponse.count = count;
      RecipeListResponse.results = results;
   }

   public ArrayList<ItemListRecipe> getListRecipe(){
      ArrayList<ItemListRecipe> ilR = new ArrayList<>();
      for(int i=0 ; i<count ; i++){
         RecipeResponse  rec = results.get(i);
         ItemListRecipe item = new ItemListRecipe(String.valueOf(rec.id),rec.name,rec.yields,0,R.drawable.placeholder_1200x500);
         ilR.add(item);
      }
      return  ilR;
   }

   static class RecipeResponse{
      private int id;
      private String name;
      private String yields;
      String thumbnail_url;

      public RecipeResponse(int id, String name, String yields, String thumbnail_url) {
         this.id = id;
         this.name = name;
         this.yields = yields;
         this.thumbnail_url = thumbnail_url;
      }

      public RecipeResponse(int id, String name, String yields) {
         this.id = id;
         this.name = name;
         this.yields = yields;
      }

   }
}
