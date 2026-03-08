package com.example.manageinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class principal extends AppCompatActivity {

    CardView cardProductos, cardMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        // Referencias a los CardView del XML
        cardProductos = findViewById(R.id.productos);
        cardMovimientos = findViewById(R.id.movimientos);

        // Listener para Productos
        cardProductos.setOnClickListener(v -> {
            Intent intent = new Intent(principal.this, productos.class);
            startActivity(intent);
        });

        // Listener para Movimientos
        cardMovimientos.setOnClickListener(v -> {
            Intent intent = new Intent(principal.this, movimientos.class);
            startActivity(intent);
        });
    }
}
