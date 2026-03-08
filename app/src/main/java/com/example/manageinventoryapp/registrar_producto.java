package com.example.manageinventoryapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrar_producto extends AppCompatActivity {

    EditText Idproducto, nombreproducto, proveedor, descripcion;
    Spinner spinnercategoria, spinnerunidadmedida, spinnerubicacion;
    Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_producto);

        Idproducto = findViewById(R.id.Idproducto);
        nombreproducto = findViewById(R.id.nombreproducto);
        proveedor = findViewById(R.id.proveedor);
        descripcion = findViewById(R.id.descripcion);
        spinnercategoria = findViewById(R.id.spinnercategoria);
        spinnerunidadmedida = findViewById(R.id.spinnerunidadmedida);
        spinnerubicacion = findViewById(R.id.spinnerubicacion);
        btnGuardar = findViewById(R.id.guardar);
        btnCancelar = findViewById(R.id.cancelar);

        String[] categorias = {"Selecciona categoría", "Plasticos", "Fribrosa", "Cajas", "Malla"};
        String[] unidadmedida = {"Selecciona unidad de medida","Metros", "kilogramos", "gramos", "unidades"};
        String[] ubicacion = {"Selecciona la ubicación", "Bodega 1", "Bodega 2", "Bodega 3", "Bodega 4"};

        configurarSpinner(spinnercategoria, categorias);
        configurarSpinner(spinnerunidadmedida, unidadmedida);
        configurarSpinner(spinnerubicacion, ubicacion);

        btnGuardar.setOnClickListener(v -> {
            guardarProductoEnAtlas();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }

    private void configurarSpinner(Spinner spinner, String[] datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, datos);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);
    }

    private void guardarProductoEnAtlas() {
        String id = Idproducto.getText().toString();
        String nombre = nombreproducto.getText().toString();
        String prov = proveedor.getText().toString();
        String desc = descripcion.getText().toString();
        String cat = spinnercategoria.getSelectedItem().toString();
        String uni = spinnerunidadmedida.getSelectedItem().toString();
        String ubi = spinnerubicacion.getSelectedItem().toString();

        if (id.isEmpty() || nombre.isEmpty() || cat.equals("Selecciona categoría")) {
            Toast.makeText(this, "Por favor completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        Producto producto = new Producto(id, nombre, cat, uni, ubi, prov, desc);

        ApiService apiService = RetrofitClient.getApiService();
        Call<Void> call = apiService.registrarProducto(producto);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(registrar_producto.this, "¡Guardado en MongoDB Atlas! 🚀", Toast.LENGTH_LONG).show();
                    finish(); // Cierra la pantalla al terminar
                } else {
                    Toast.makeText(registrar_producto.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(registrar_producto.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}