package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class CrearDificultadActivity extends AppCompatActivity {

    EditText etNomDificultat;
    Button btnContinuarDificultat, btnAgregar;

    LinearLayout llFilaDificultadFacil;

    Spinner spinnerDificultad, spinnerOperadores;
    EditText minNum1, maxNum2, minNum2, maxNum1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_dificultad);

        llFilaDificultadFacil = findViewById(R.id.llFilaDificultadFacil);
        etNomDificultat = findViewById(R.id.etNomDificultat);
        btnContinuarDificultat = findViewById(R.id.btnContinuarDificultat);
        spinnerDificultad = findViewById(R.id.spinnerDificultad);
        spinnerOperadores = findViewById(R.id.spinnerOperadores);
        minNum1 = findViewById(R.id.etMinNum1);
        maxNum1 = findViewById(R.id.etMaxNum1);
        minNum2 = findViewById(R.id.etMinNum2);
        maxNum2 = findViewById(R.id.etMaxNum2);
        btnAgregar = findViewById(R.id.btnAgregar);

        llFilaDificultadFacil.setVisibility(View.GONE);
        btnAgregar.setVisibility(View.GONE);

        btnContinuarDificultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNomDificultat.getText().toString().isEmpty()) {
                    etNomDificultat.setError("Introdueix un nom");
                } else {
                    llFilaDificultadFacil.setVisibility(View.VISIBLE);
                    btnAgregar.setVisibility(View.VISIBLE);
                    btnContinuarDificultat.setVisibility(View.GONE);
                    etNomDificultat.setEnabled(false);
                    iniciarSpinners();
                }
            }
        });


    }

    public void iniciarSpinners(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.niveles,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificultad.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.operadores,
                android.R.layout.simple_spinner_item
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperadores.setAdapter(adapter2);
    }
}