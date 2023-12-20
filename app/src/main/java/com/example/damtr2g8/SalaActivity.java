package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class SalaActivity extends AppCompatActivity {

    public Sala sala;
    public TextView tvCodiClasse, tvStatus;
    public Button btnIniciar;
    public RecyclerView recyclerView;
    public JugadoresAdapter jugadorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        tvCodiClasse = findViewById(R.id.tvCodiClasse);
        tvStatus = findViewById(R.id.tvStatus);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(v -> {
            iniciarPartida();
        });

        int idClasse = getIntent().getIntExtra("idClasse", -1);
        int idProfe = getIntent().getIntExtra("idProfe", -1);

        recibirSala(idClasse, idProfe);
    }

    public void recibirSala(int idClasse, int idProfe){
        SocketManager socket = new SocketManager();
        socket.connect();

        socket.getSala(idClasse, idProfe, new SocketManager.SalaCallback() {
            @Override
            public void onSalaReceived(Sala receivedSala) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sala = receivedSala;
                        tvCodiClasse.setText(sala.getCodi());
                        if(sala.getStatus().equals("waiting")){
                            tvStatus.setText("esperant jugadors");
                        }else{
                            tvStatus.setText("Iniciada");
                            btnIniciar.setVisibility(Button.GONE);
                        }
                        Log.i("ReciboSala", "Sala actualizada - ID: " + sala.getIdSala());

                        // Ahora puedes acceder a los jugadores de la sala actualizada
                        for (Sala.Jugador jugador : sala.getJugadores()) {
                            Log.i("ReciboSala", "Jugador: " + jugador.getNombre() + " - ID avatar: " + jugador.getIdAvatar());
                        }

                        recyclerView = findViewById(R.id.rvJugadors);
                        recyclerView.setLayoutManager(new GridLayoutManager(SalaActivity.this,3));

                        // Configurar el adaptador con la lista de jugadores
                        jugadorsAdapter = new JugadoresAdapter(sala.getJugadores());
                        recyclerView.setAdapter(jugadorsAdapter);
                    }
                });
            }
        });
    }

    public void iniciarPartida(){
        SocketManager socket = new SocketManager();
        socket.connect();

        socket.iniciarPartida(sala.getIdSala());

        tvStatus.setText("Iniciada");
        btnIniciar.setVisibility(Button.GONE);
    }
}