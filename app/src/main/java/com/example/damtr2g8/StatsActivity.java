package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.damtr2g8.databinding.ActivityClassesBinding;
import com.example.damtr2g8.databinding.ActivityStatsBinding;

public class StatsActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityStatsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        //binding = ActivityClassesBinding.inflate(getLayoutInflater());

        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Configurar la lógica del botón para volver al inicio de sesión
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.VeureClasses:
                Intent intent = new Intent(StatsActivity.this, ClassesActivity.class);
                startActivity(intent);
                return true;
            case R.id.ver_estadisticas:
                Toast.makeText(this, "Estas en la pantalla de estadísticas", Toast.LENGTH_SHORT).show();

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
