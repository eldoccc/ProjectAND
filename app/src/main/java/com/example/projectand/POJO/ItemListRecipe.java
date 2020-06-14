package com.example.projectand.POJO;

public class ItemListRecipe {
    private String idI;
    private String textName;
    private String textAuthor;
    private int nbFav;
    private int imgRessource;  //Todo refractor type

    public ItemListRecipe(String idI, String textName, String textAuthor, int nbFav, int imgRessource) {
        this.idI = idI;
        this.textName = textName;
        this.textAuthor = textAuthor;
        this.nbFav = nbFav;
        this.imgRessource = imgRessource;
    }

    public ItemListRecipe(String textName, String textAuthor, int nbFav, int imgRessource) {
        this.textName = textName;
        this.textAuthor = textAuthor;
        this.nbFav = nbFav;
        this.imgRessource = imgRessource;
    }

    public ItemListRecipe(ItemListRecipe itemListRecipe) {
    }

    @Override
    public String toString() {
        return "ItemListRecipe{" +
                "idI='" + idI + '\'' +
                ", textName='" + textName + '\'' +
                ", textAuthor='" + textAuthor + '\'' +
                ", nbFav=" + nbFav +
                ", imgRessource=" + imgRessource +
                '}';
    }

    public String getIdI() {
        return idI;
    }

    public void setIdI(String idI) {
        this.idI = idI;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTextAuthor() {
        return textAuthor;
    }

    public void setTextAuthor(String textAuthor) {
        this.textAuthor = textAuthor;
    }

    public int getNbFav() {
        return nbFav;
    }

    public void setNbFav(int nbFav) {
        this.nbFav = nbFav;
    }

    public int getImgRessource() {
        return imgRessource;
    }

    public void setImgRessource(int imgRessource) {
        this.imgRessource = imgRessource;
    }
}
