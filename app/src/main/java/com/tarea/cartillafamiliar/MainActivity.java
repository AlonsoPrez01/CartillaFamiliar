package com.tarea.cartillafamiliar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btn_iniciarSesion;
    EditText correo, contra;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.userMail);
        contra = findViewById(R.id.userPassword);
        btn_iniciarSesion = findViewById(R.id.iniciarSesion);


        btn_iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoUsuario = correo.getText().toString().trim();
                String contraUsuario = contra.getText().toString().trim();

                if (correoUsuario.isEmpty() && contraUsuario.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingrese los campos rqueridos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(correoUsuario, contraUsuario);
                }
            }
        });
    }

    private void loginUser(String correoUsuario, String contraUsuario) {
        mAuth.signInWithEmailAndPassword(correoUsuario, contraUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(MainActivity.this, Inicio.class));
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void registrarUser(View view) {
        Intent registrar = new Intent(this, ConfirmarRegistro.class);
        startActivity(registrar);
    }

    public void recuperarCuenta(View view) {
        Intent recuperar = new Intent(this, RecuperarCuenta.class);
        startActivity(recuperar);
    }

}