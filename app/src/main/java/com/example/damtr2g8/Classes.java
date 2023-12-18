package com.example.damtr2g8;

import java.util.List;

public class Classes {

    public List<Classe> classes;


    public Classes(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public class Classe{
        public int idClasse;
        public String nomClasse;
        public int idPropietari;

        public Classe(int idClasse, String nomClasse, int idPropietari) {
            this.idClasse = idClasse;
            this.nomClasse = nomClasse;
            this.idPropietari = idPropietari;
        }

        public int getIdClasse() {
            return idClasse;
        }

        public void setIdClasse(int idClasse) {
            this.idClasse = idClasse;
        }

        public String getNomClasse() {
            return nomClasse;
        }

        public void setNomClasse(String nomClasse) {
            this.nomClasse = nomClasse;
        }

        public int getIdPropietari() {
            return idPropietari;
        }

        public void setIdPropietari(int idPropietari) {
            this.idPropietari = idPropietari;
        }
    }
}
