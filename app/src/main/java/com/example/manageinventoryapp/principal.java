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

        cardProductos = findViewById(R.id.productos);
        cardMovimientos = findViewById(R.id.movimientos);

        cardProductos.setOnClickListener(v -> {
            Intent intent = new Intent(principal.this, productos.class);
            startActivity(intent);
        });

        cardMovimientos.setOnClickListener(v -> {
            Intent intent = new Intent(principal.this, movimientos.class);
            startActivity(intent);
        });
    }
}
