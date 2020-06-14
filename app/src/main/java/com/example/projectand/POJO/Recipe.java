package com.example.projectand.POJO;

public class Recipe {
    private String nameR;
    private String descR;
    private int imgR;
    private String dateR;
    private int nbFav;
    private String nameU;
    private String idU;

    public Recipe(String nameR, String descR, int imgR, String dateR, int nbFav, String nameU, String idU) {
        this.nameR = nameR;
        this.descR = descR;
        this.imgR = imgR;
        this.dateR = dateR;
        this.nbFav = nbFav;
        this.nameU = nameU;
        this.idU = idU;
    }

    public Recipe() {
    }

    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    public String getDescR() {
        return descR;
    }

    public void setDescR(String descR) {
        this.descR = descR;
    }

    public int getImgR() {
        return imgR;
    }

    public void setImgR(int imgR) {
        this.imgR = imgR;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public int getNbFav() {
        return nbFav;
    }

    public void setNbFav(int nbFav) {
        this.nbFav = nbFav;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "nameR='" + nameR + '\'' +
                ", descR='" + descR + '\'' +
                ", imgR=" + imgR +
                ", dateR='" + dateR + '\'' +
                ", nbFav=" + nbFav +
                ", nameU='" + nameU + '\'' +
                ", idU='" + idU + '\'' +
                '}';
    }
}
