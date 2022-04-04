package com.tarea.cartillafamiliar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar mtoolbar = findViewById(R.id.inicio_toolbar);
        setSupportActionBar(mtoolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.munu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_vacuna:
                return true;

            case R.id.action_cerraSesión:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void anniadirVacuna(MenuItem item){
        Intent anniadirvacuna = new Intent(this, ListaVacunas.class);
        startActivity(anniadirvacuna);
    }

    public void cerrarSession(MenuItem item){
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        finish();
        startActivity(new Intent(Inicio.this, MainActivity.class));
        Toast.makeText(Inicio.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}