package com.tarea.cartillafamiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);
    }

    public void recuperarAtras(View view){
        Intent cancelar = new Intent(this, MainActivity.class);
        startActivity(cancelar);
    }

    public void Recuperar(View view){
        Intent recuperar = new Intent(this, MainActivity.class);
        startActivity(recuperar);
    }
}