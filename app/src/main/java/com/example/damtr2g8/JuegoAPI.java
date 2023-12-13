package com.example.damtr2g8;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JuegoAPI {
    @POST("/login")
    Call<RespuestaUsuario> login(@Body UsuarioLogin usuario);
    @POST("/register")
    Call<RespuestaUsuario> register(@Body UsuarioRegistro usuario);
}
