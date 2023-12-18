package com.example.damtr2g8;

import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JugadoresAdapter extends RecyclerView.Adapter<JugadoresAdapter.ViewHolder>{

    public List<Sala.Jugador> jugadores;

    public JugadoresAdapter(List<Sala.Jugador> jugadores) {
        if (jugadores == null) {
            this.jugadores = null;
        } else {
            this.jugadores = jugadores;
        }
    }
    @NonNull
    @Override
    public JugadoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jugador_item, parent, false);
        return new JugadoresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadoresAdapter.ViewHolder holder, int position) {
        Sala.Jugador jugador = jugadores.get(position);
        holder.nombre.setText(jugador.getNombre());
        // https://api.dicebear.com/7.x/big-smile/svg?seed=
        final String url = "https://api.dicebear.com/7.x/big-smile/svg?seed=" + jugador.getIdAvatar();
        Log.i("URL", url + " Id del jugador: " + jugador.getIdAvatar());

        // Utilizar un Thread para realizar la operación de red en segundo plano
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Carga la imagen SVG con AndroidSVG
                    SVG svg = SVG.getFromInputStream(new URL(url).openStream());

                    // Actualizar la interfaz de usuario en el hilo principal
                    holder.imgJugador.post(new Runnable() {
                        @Override
                        public void run() {
                            // Obtén un Drawable del SVG
                            PictureDrawable pictureDrawable = new PictureDrawable(svg.renderToPicture());
                            // Establece el Drawable en el ImageView
                            holder.imgJugador.setImageDrawable(pictureDrawable);
                        }
                    });
                } catch (SVGParseException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    @Override
    public int getItemCount() {
        if (jugadores != null) {
            return jugadores.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imgJugador;

        public ViewHolder(@NonNull View parent) {
            super(parent);
            nombre = parent.findViewById(R.id.tvNombreJugador);
            imgJugador = parent.findViewById(R.id.ivAvatar);
        }
    }
}
