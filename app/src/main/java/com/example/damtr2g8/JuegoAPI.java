package com.example.damtr2g8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JuegoAPI {
    @POST("/login")
    Call<RespuestaUsuario> login(@Body UsuarioLogin usuario);
    @POST("/register")
    Call<RespuestaUsuario> register(@Body UsuarioRegistro usuario);
    @GET("/classeProfe/{id}")
    Call<List<Classes.Classe>> getClasses(@Path("id") int id);
    @POST("/crearClasse")
    Call<Void> crearClasse(@Body CrearClasse crearClasse);

}
