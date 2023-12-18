package com.example.damtr2g8;

import com.google.gson.annotations.SerializedName;

public class CrearClasse {
    @SerializedName("nomClasse")
    private String nomClasse;
    @SerializedName("idUsu")
    private int idPropietari;

    public CrearClasse(String nomClasse, int idPropietari) {
        this.nomClasse = nomClasse;
        this.idPropietari = idPropietari;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public int getIdPropietari() {
        return idPropietari;
    }
}
