package com.example.damtr2g8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damtr2g8.databinding.ActivityClassesBinding;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassesBinding binding;
    String nomProfe, cognomProfe, correuProfe, passProfe;
    public static int idProfe;

    public Retrofit retrofit;
    public JuegoAPI juegoAPI;
    public static final String URL = "http://mathbattle.dam.inspedralbes.cat:3751/";

    public List<Classes.Classe> classes;
    public RecyclerView recyclerView;
    public ClassesAdapter classesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configurar la lógica del botón para volver al inicio de sesión
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnCrearClasse.setOnClickListener(v -> {
            showConfirmDialog();
            Toast.makeText(this, "asdasd", Toast.LENGTH_SHORT).show();
        });

        DataBaseHelper dbHelper = new DataBaseHelper(ClassesActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] values = {
                DataBaseHelper.COLUMN_NOM,
                DataBaseHelper.COLUMN_PASS,
                DataBaseHelper.COLUMN_COGNOM,
                DataBaseHelper.COLUMN_CORREU,
                DataBaseHelper.COLUMN_ID,
                DataBaseHelper.COLUMN_PASS
        };

        Cursor cursor = db.query(
                DataBaseHelper.TABLE_NAME,
                values,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            nomProfe = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NOM));
            passProfe = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PASS));
            cognomProfe = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_COGNOM));
            correuProfe = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_CORREU));
            idProfe = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_ID));
        }

        if(cursor != null){
            db.close();
        }

        getClasses(idProfe);

    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

        case R.id.ver_estadisticas:
            Intent intent = new Intent(ClassesActivity.this, StatsActivity.class);
            startActivity(intent);
            return true;
        case R.id.VeureClasses:
            Toast.makeText(this, "Estas en la pantalla de clases", Toast.LENGTH_SHORT).show();

        default:
            return super.onOptionsItemSelected(item);
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void getClasses(int idProfe){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        juegoAPI = retrofit.create(JuegoAPI.class);

        Call<List<Classes.Classe>> call = juegoAPI.getClasses(idProfe);

        call.enqueue(new Callback<List<Classes.Classe>>() {
            @Override
            public void onResponse(Call<List<Classes.Classe>> call, Response<List<Classes.Classe>> response) {
                if(response.isSuccessful()){
                    classes = response.body();
                    Log.i("verClasses", classes.toString());
                    for(int i = 0; i < classes.size(); i++){
                        Log.i("verClasses", classes.get(i).getNomClasse());
                    }
                    recyclerView = findViewById(R.id.rvClasses);
                    recyclerView.setLayoutManager(new GridLayoutManager(ClassesActivity.this,3));


                    classesAdapter = new ClassesAdapter(classes, idProfe);
                    recyclerView.setAdapter(classesAdapter);

                    Toast.makeText(ClassesActivity.this, "Clases obtenidas correctamente", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ClassesActivity.this, "Error al obtener las clases", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Classes.Classe>> call, Throwable t) {
            Toast.makeText(ClassesActivity.this, "Error al obtener las clases onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showConfirmDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.crear_classe_dialog, null);

        dialogBuilder.setView(dialogView);
        AlertDialog dialog = dialogBuilder.create();

        Button btnConfirmar = dialogView.findViewById(R.id.btnCrearClasse);
        Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvNomClasse = dialogView.findViewById(R.id.etNomClasse);
                String nomClasse = tvNomClasse.getText().toString();


                CrearClasse classe = new CrearClasse(nomClasse, idProfe);

                Log.i("crearClasse", classe.getNomClasse()+" "+classe.getIdPropietari());

                retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                        .build();

                juegoAPI = retrofit.create(JuegoAPI.class);

                Call<Void> call = juegoAPI.crearClasse(classe);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            getClasses(idProfe);
                            Toast.makeText(ClassesActivity.this, "Clase creada correctamente", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(ClassesActivity.this, "Error al crear la clase", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ClassesActivity.this, "Error on Failure", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}



