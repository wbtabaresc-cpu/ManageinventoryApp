package com.example.manageinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productos extends AppCompatActivity {

    CardView btnRegistrarProducto;
    ListView listaProductos; // Añadimos el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);

        // 1. Enlazar componentes
        btnRegistrarProducto = findViewById(R.id.registrar);
        listaProductos = findViewById(R.id.listaProductos); // Asegúrate de tenerlo en productos.xml

        // 2. Navegación para registrar (tu código original)
        btnRegistrarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(productos.this, registrar_producto.class);
            startActivity(intent);
        });

        // 3. Cargar datos desde MongoDB Atlas al abrir la pantalla
        cargarProductos();
    }

    // Ejecutamos la carga cada vez que volvemos a esta pantalla
    @Override
    protected void onResume() {
        super.onResume();
        cargarProductos();
    }

    private void cargarProductos() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Producto>> call = apiService.obtenerProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Usamos el Adaptador que creamos para llenar la lista
                    ProductoAdapter adapter = new ProductoAdapter(productos.this, response.body());
                    listaProductos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(productos.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}