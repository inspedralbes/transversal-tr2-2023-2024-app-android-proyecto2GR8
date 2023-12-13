package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    Button btnIrLogin, btnRegistro;
    EditText etEmail, etPass, etNom, etCognom;
    public Retrofit retrofit;
    public JuegoAPI juegoAPI;
    private static final String URL = "http://mathbattle.dam.inspedralbes.cat:3751/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnIrLogin = findViewById(R.id.btnIrLogin);
        btnRegistro = findViewById(R.id.btnRegistre);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        etNom = findViewById(R.id.etNom);
        etCognom = findViewById(R.id.etCognom);

        btnIrLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnRegistro.setOnClickListener(v -> {
            //registroPrueba(v);
            registro();
        });
    }
    public void registro(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String nom = etNom.getText().toString();
        String cognom = etCognom.getText().toString();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        juegoAPI = retrofit.create(JuegoAPI.class);

        UsuarioRegistro user = new UsuarioRegistro(email, pass, nom, cognom);

        Call<RespuestaUsuario> call = juegoAPI.register(user);

        call.enqueue(new Callback<RespuestaUsuario>() {
            @Override
            public void onResponse(Call<RespuestaUsuario> call, Response<RespuestaUsuario> response) {
                if(response.isSuccessful()){
                    RespuestaUsuario respuesta = response.body();
                    Toast.makeText(RegisterActivity.this, "Usuario registrado!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespuestaUsuario> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error al registrar usuario onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}