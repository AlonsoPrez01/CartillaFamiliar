package com.tarea.cartillafamiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrarUser(View view){
        Intent registrar = new Intent(this, ConfirmarRegistro.class);
        startActivity(registrar);
    }

    public void recuperarCuenta(View view){
        Intent recuperar = new Intent(this, RecuperarCuenta.class);
        startActivity(recuperar);
    }

    public void iniciarSesion(View view){
        Intent iniciarsesion = new Intent(this, Inicio.class);
        startActivity(iniciarsesion);
    }
}