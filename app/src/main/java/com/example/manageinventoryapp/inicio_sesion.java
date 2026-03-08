package com.example.manageinventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inicio_sesion extends AppCompatActivity {

    EditText inputUsuario, inputPassword;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        inputUsuario = findViewById(R.id.editTextUsuario);
        inputPassword = findViewById(R.id.editTextPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            String usuario = inputUsuario.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "⚠️ Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            }
            else if (usuario.equals("william") && password.equals("1234")) {
                Toast.makeText(this, "✅ Bienvenido al Sistema", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(inicio_sesion.this, principal.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "❌ Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
}