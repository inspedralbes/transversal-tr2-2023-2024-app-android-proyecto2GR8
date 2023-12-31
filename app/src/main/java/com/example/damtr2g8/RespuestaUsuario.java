package com.example.damtr2g8;

import com.google.gson.annotations.SerializedName;

public class RespuestaUsuario {
    @SerializedName("userData")
    public DataUser userData;

    public RespuestaUsuario(DataUser dataUser){
        this.userData = dataUser;
    }

    public DataUser getUserData() {
        return userData;
    }

    public class DataUser{
        @SerializedName("idUsu")
        private int idUsu;
        @SerializedName("nom")
        private String nom;
        @SerializedName("cognom")
        private String cognom;
        @SerializedName("correu")
        private String correu;
        @SerializedName("pass")
        private String pass;

        public DataUser(int idUsu, String nom, String cognom, String correu, String pass) {
            this.idUsu = idUsu;
            this.nom = nom;
            this.cognom = cognom;
            this.correu = correu;
            this.pass = pass;
        }

        public int getIdUsu() {
            return idUsu;
        }

        public void setIdUsu(int idUsu) {
            this.idUsu = idUsu;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCognom() {
            return cognom;
        }

        public void setCognom(String cognom) {
            this.cognom = cognom;
        }

        public String getCorreu() {
            return correu;
        }

        public void setCorreu(String correu) {
            this.correu = correu;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

    }

}


