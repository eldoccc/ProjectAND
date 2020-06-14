package com.example.projectand.POJO;

public class User {
    private int idU;
    private String nameU;
    private String emailU;
    private String password;

    public User(int idU, String nameU, String emailU, String password) {
        this.idU = idU;
        this.nameU = nameU;
        this.emailU = emailU;
        this.password = password;
    }

    public User(int idU, String nameU) {
        this.idU = idU;
        this.nameU = nameU;
    }

    public User(String emailU, String password) {
        this.emailU = emailU;
        this.password = password;
    }

    public User() {
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
