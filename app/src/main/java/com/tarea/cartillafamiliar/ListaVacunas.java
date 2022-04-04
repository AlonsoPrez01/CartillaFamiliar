package com.tarea.cartillafamiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaVacunas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacunas);
    }

    public void cerrarLista(View view){
        Intent cerrar = new Intent(this, Inicio.class);
        startActivity(cerrar);
    }

    public void guardarLista(View view){
        Intent guardar = new Intent(this, Inicio.class);
        startActivity(guardar);
    }
}