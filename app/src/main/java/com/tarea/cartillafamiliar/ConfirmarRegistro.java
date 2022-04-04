package com.tarea.cartillafamiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmarRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_registro);
    }

    public void Cancelar(View view){
        Intent cancel = new Intent(this, MainActivity.class);
        startActivity(cancel);
    }

    public void Comenzar(View view){
        Intent comenzar = new Intent(this, RegistrarUsuario.class);
        startActivity(comenzar);
    }
}