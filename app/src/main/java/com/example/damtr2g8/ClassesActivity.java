package com.example.damtr2g8;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.damtr2g8.databinding.ActivityClassesBinding;

public class ClassesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassesBinding binding;

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

    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

        case R.id.action_ver_estadisticas:
            Intent intent = new Intent(ClassesActivity.this, StatsActivity.class);
            startActivity(intent);
            return true;

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

