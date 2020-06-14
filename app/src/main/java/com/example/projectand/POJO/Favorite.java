package com.example.projectand.POJO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    private int idFav;
    private String idUser;
    private String idRecipe;
    private int priority;

    public Favorite(String idUser, String idRecipe, int priority) {
        this.idUser = idUser;
        this.idRecipe = idRecipe;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIdFav() {
        return idFav;
    }

    public void setIdFav(int idFav) {
        this.idFav = idFav;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(String idRecipe) {
        this.idRecipe = idRecipe;
    }


    @Override
    public String toString() {
        return "Favorite{" +
                "idFav=" + idFav +
                ", idUser='" + idUser + '\'' +
                ", idRecipe='" + idRecipe + '\'' +
                ", priority=" + priority +
                '}';
    }
}
