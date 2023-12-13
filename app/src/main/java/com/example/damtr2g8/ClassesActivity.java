package com.example.damtr2g8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.damtr2g8.databinding.ActivityClassesBinding;

import org.w3c.dom.Text;

public class ClassesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassesBinding binding;
    TextView tvPrueba;

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
            //aqui irá la funcionalidad para crear una clase
        });

        tvPrueba = findViewById(R.id.tvPrueba);

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
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NOM));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PASS));
            String cognom = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_COGNOM));

            tvPrueba.setText(nombre + " " + cognom);
        }

        if(cursor != null){
            db.close();
        }
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
}

