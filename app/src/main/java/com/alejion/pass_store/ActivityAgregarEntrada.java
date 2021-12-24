package com.alejion.pass_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alejion.pass_store.db.EntradaHandler;
import com.alejion.pass_store.models.Entrada;

public class ActivityAgregarEntrada extends AppCompatActivity {

    EditText et_nombre;
    EditText et_usuario;
    EditText et_password;
    Button btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_entrada);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Agregar entrada");

        et_nombre = findViewById(R.id.et_nombre);
        et_usuario = findViewById(R.id.et_usuario);
        et_password = findViewById(R.id.et_password);
        btn_agregar = findViewById(R.id.btn_agregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldsEmpty()) {
                    Toast.makeText(ActivityAgregarEntrada.this, "Existen campos vacíos",Toast.LENGTH_SHORT).show();
                }
                else {
                    save();
                    Intent intent = new Intent(ActivityAgregarEntrada.this, ActivityListaEntradas.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void save() {
        EntradaHandler data_source;
        data_source = new EntradaHandler(ActivityAgregarEntrada.this);

        Entrada entrada = new Entrada();
        entrada.setNombre(et_nombre.getText().toString());
        entrada.setUsuario(et_usuario.getText().toString());
        entrada.setPassword(et_password.getText().toString());

        data_source.insertarEntrada(entrada);;
    }

    private boolean fieldsEmpty() {

        boolean vacio = false;
        if(TextUtils.isEmpty(et_nombre.getText().toString())) vacio = true;
        if(TextUtils.isEmpty(et_usuario.getText().toString())) vacio = true;
        if(TextUtils.isEmpty(et_password.getText().toString())) vacio = true;

        return vacio;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}