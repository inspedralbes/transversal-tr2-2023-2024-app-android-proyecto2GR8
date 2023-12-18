package com.example.damtr2g8;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {
    public Socket socket;

    public SocketManager() {
        try {
            socket = IO.socket("http://mathbattle.dam.inspedralbes.cat:3751/");

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() {
        socket.connect();
        Log.i("socket", "socket conectado");

    }

    public void disconnect() {
        socket.disconnect();
        Log.i("socket", "socket desconectado");

    }

    public void createSala(int idClasse, int idUser) {
        Log.i("SocketLog", "CreateSala Hecho");
        // Enviar el evento createSala al servidor con los parámetros necesarios
        socket.emit("createSala", idClasse, idUser);

    }

    public void getSala(int idClasse, int idUser, SalaCallback callback){
        Sala sala = new Sala();
        Log.i("SocketLog", "getSala Hecho" + idClasse + " " + idUser);

        socket.emit("getSala", idUser, idClasse);

        socket.on("join", args -> {
            JSONObject data = (JSONObject) args[0];
            Gson gson = new Gson();

            Sala finalSala = gson.fromJson(data.toString(), Sala.class);

            Log.i("SocketLog", "join Hecho");
            Log.i("SocketLog", " " + finalSala.getIdSala());

            // Llama a la devolución de llamada cuando la sala esté lista
            callback.onSalaReceived(finalSala);
        });
    }

    public void iniciarPartida(int idSala){
        socket.emit("iniciarPartida", idSala);
    }

    public interface SalaCallback {
        void onSalaReceived(Sala sala);
    }

}
