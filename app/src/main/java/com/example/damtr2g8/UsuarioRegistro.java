package com.example.damtr2g8;

import com.google.gson.annotations.SerializedName;

public class UsuarioRegistro {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("nom")
    private String nom;
    @SerializedName("cognom")
    private String cognom;

    public UsuarioRegistro(String email, String password, String nom, String cognom) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.cognom = cognom;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "UsuarioLogin{" +
                "email='" + this.email + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
