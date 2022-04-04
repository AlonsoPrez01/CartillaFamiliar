package com.tarea.cartillafamiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class RegistrarUsuario extends AppCompatActivity {

    String[] items = {"Masculino", "Femenino"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    String[] tiposSangre = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    AutoCompleteTextView autoCompleteSangre;
    ArrayAdapter<String> adapterSangre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        autoCompleteSangre = findViewById(R.id.auto_complete_txtSangre);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoCompleteTxt.setAdapter(adapterItems);

        adapterSangre = new ArrayAdapter<String>(this, R.layout.tipos_sangre, tiposSangre);
        autoCompleteSangre.setAdapter(adapterSangre);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        autoCompleteSangre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sangre = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    public void Atras(View view){
        Intent atras = new Intent(this, ConfirmarRegistro.class);
        startActivity(atras);
    }

    public void Registrarse(View view){
        Intent registrado = new Intent(this, RegistroExitoso.class);
        startActivity(registrado);
    }
}