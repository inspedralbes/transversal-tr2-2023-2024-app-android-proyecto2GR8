package com.example.damtr2g8;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder>{

    public List<Classes.Classe> classes;
    public int idProfe;

    public ClassesAdapter(List<Classes.Classe> classes, int idProfe) {
        if (classes == null) {
            this.classes = new ArrayList<>();  // Initialize with an empty list
        } else {
            this.classes = classes;
            this.idProfe = idProfe;
        }
    }
    public List<Classes.Classe> getClasses() {
        return classes;
    }
    @NonNull
    @Override
    public ClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.ViewHolder holder, int position) {
        Classes.Classe classe = classes.get(position);
        holder.nombre.setText(classe.getNomClasse());
        holder.btnUnirse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SocketManager socket = new SocketManager();
                socket.connect();
                socket.createSala(classe.getIdClasse(), idProfe);

                Context context = v.getContext();
                Intent intent = new Intent(context, SalaActivity.class);
                intent.putExtra("idClasse", classe.getIdClasse());
                intent.putExtra("idProfe", idProfe);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (classes != null) {
            return classes.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView nombre;
    Button btnUnirse;

        public ViewHolder(@NonNull View parent) {
            super(parent);
            nombre = parent.findViewById(R.id.tvNombreClasse);
            btnUnirse = parent.findViewById(R.id.btnUnirse);
        }
    }


}
