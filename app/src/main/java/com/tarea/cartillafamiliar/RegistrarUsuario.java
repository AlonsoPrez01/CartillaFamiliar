package com.tarea.cartillafamiliar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity {

    Button btnRegistrarse;
    EditText registroNombre, registroApellidoP, registroApellidoM, registroCorreo, registroContra;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

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

        registroNombre = findViewById(R.id.txtNombreUser);
        registroCorreo = findViewById(R.id.registroMail);
        registroContra = findViewById(R.id.registroContra);
        btnRegistrarse = findViewById(R.id.registrarse);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

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

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCorreo = registroCorreo.getText().toString().trim();
                String newContra = registroContra.getText().toString().trim();

                if(newCorreo.isEmpty() && newContra.isEmpty()){
                    Toast.makeText(RegistrarUsuario.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(newCorreo, newContra);
                }
            }
        });
    }

    private void registerUser(String newCorreo, String newContra) {
        mAuth.createUserWithEmailAndPassword(newCorreo, newContra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("nuevoCorreo", newCorreo);
                map.put("nuevaContra", newContra);

                mFirestore.collection("Usuarios").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegistrarUsuario.this, RegistroExitoso.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarUsuario.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrarUsuario.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
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