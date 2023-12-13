package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button btnIrRegistro, btnLogin;
    public Retrofit retrofit;
    //private static final String URL = "http://192.168.16.1:3751/";
    private static final String URL = "http://mathbattle.dam.inspedralbes.cat:3751/";
    EditText etEmail, etPass;
    public JuegoAPI juegoAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIrRegistro = findViewById(R.id.btnIrRegistro);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);

        btnIrRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            //loginPrueba(v);
            login();
        });
    }

    public void loginPrueba(View view) {
        Intent intent = new Intent(LoginActivity.this, ClassesActivity.class);
        startActivity(intent);
    }
    public void login(){
        Log.i("login", "entro al login");
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        UsuarioLogin user = new UsuarioLogin(email, pass);
        Log.i("verUsuario1", user.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        juegoAPI = retrofit.create(JuegoAPI.class);

        Call<RespuestaUsuario> call = juegoAPI.login(user);
        call.enqueue(new Callback<RespuestaUsuario>() {

            @Override
            public void onResponse(Call<RespuestaUsuario> call, Response<RespuestaUsuario> response) {
                Log.i("HTTP Status Code", String.valueOf(response.code()));
                if(response.isSuccessful()){
                    RespuestaUsuario respuestaUsuario = response.body();
                    Log.i("verUsuarioCorrecto", respuestaUsuario.toString());
                    String nom = respuestaUsuario.userData.getNom();
                    String cognom = respuestaUsuario.userData.getCognom();
                    String correu = respuestaUsuario.userData.getCorreu();
                    String pass = respuestaUsuario.userData.getPass();
                    int idUsu = respuestaUsuario.userData.getIdUsu();

                    DataBaseHelper dbHelper = new DataBaseHelper(LoginActivity.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(DataBaseHelper.COLUMN_NOM, nom);
                    values.put(DataBaseHelper.COLUMN_COGNOM, cognom);
                    values.put(DataBaseHelper.COLUMN_CORREU, correu);
                    values.put(DataBaseHelper.COLUMN_PASS, pass);
                    values.put(DataBaseHelper.COLUMN_ID, idUsu);

                    //ESTO HACE EL INSERT
                    long newRowId = db.insert(DataBaseHelper.TABLE_NAME, null, values);

                    //Y CERRAR CONEXION
                    db.close();

                    Intent intent = new Intent(LoginActivity.this, ClassesActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this, "El correo o la contraseña no son correctos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaUsuario> call, Throwable t) {
                Log.i("verUsuarioError", "error "+t.getMessage()+" "+call.toString());

            }
        });
    }

}