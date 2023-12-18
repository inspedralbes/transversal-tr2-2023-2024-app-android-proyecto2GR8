package com.example.damtr2g8;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sala {
    @SerializedName("owner_id")
    private int ownerId;

    @SerializedName("owner")
    private String owner;

    @SerializedName("id_sala")
    private int idSala;

    @SerializedName("id_classe")
    private int idClasse;

    @SerializedName("jugadores")
    private List<Jugador> jugadores;

    @SerializedName("status")
    private String status;

    @SerializedName("codi")
    private String codi;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public class Jugador{
        public int idJugador;
        public String nombre;
        public boolean winner;
        public int idAvatar;

        public Jugador(int idJugador, String nombre, boolean winner, int idAvatar) {
            this.idJugador = idJugador;
            this.nombre = nombre;
            this.winner = winner;
            this.idAvatar = idAvatar;
        }

        public int getIdJugador() {
            return idJugador;
        }

        public void setIdJugador(int idJugador) {
            this.idJugador = idJugador;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public boolean isWinner() {
            return winner;
        }

        public void setWinner(boolean winner) {
            this.winner = winner;
        }

        public int getIdAvatar() {
            return idAvatar;
        }

        public void setIdAvatar(int idAvatar) {
            this.idAvatar = idAvatar;
        }
    }
}

