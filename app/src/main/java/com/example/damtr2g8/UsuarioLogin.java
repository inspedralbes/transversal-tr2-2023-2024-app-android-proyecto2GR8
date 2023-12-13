package com.example.damtr2g8;

import com.google.gson.annotations.SerializedName;

public class UsuarioLogin {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UsuarioLogin(String email, String password) {
        this.email = email;
        this.password = password;
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
