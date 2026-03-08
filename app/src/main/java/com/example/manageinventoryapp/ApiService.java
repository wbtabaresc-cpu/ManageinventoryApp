package com.example.manageinventoryapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE; // Importante para eliminar
import retrofit2.http.GET;    // Importante para leer
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/api/productos/registrar")
    Call<Void> registrarProducto(@Body Producto producto);

    @GET("/api/productos")
    Call<List<Producto>> obtenerProductos();

    @DELETE("/api/productos/{id}")
    Call<Void> eliminarProducto(@Path("id") String id);

    @POST("/api/login")
    Call<LoginResponse> login(@Body User user);
}