package com.alejion.pass_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alejion.pass_store.db.EntradaHandler;
import com.alejion.pass_store.models.Entrada;

public class ActivityDetalleEntrada extends AppCompatActivity {

    EntradaHandler data_source;
    Entrada entrada;

    TextView tv_nombre;
    TextView tv_usuario;
    TextView tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrada);
        setTitle("Detalle de la entrada");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        entrada = (Entrada) intent.getSerializableExtra("entrada");

        tv_nombre = findViewById(R.id.tv_item_nombre);
        tv_usuario = findViewById(R.id.tv_item_usuario);
        tv_password = findViewById(R.id.tv_item_password);

        tv_nombre.setText(entrada.getNombre());
        tv_usuario.setText(entrada.getUsuario());
        tv_password.setText(entrada.getPassword());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_copiar, menu);
        inflater.inflate(R.menu.menu_eliminar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_eliminar) {

            data_source = new EntradaHandler(ActivityDetalleEntrada.this);
            data_source.eliminarEntrada(entrada.getId());
            Toast.makeText(ActivityDetalleEntrada.this, "Se eliminó la entrada", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ActivityDetalleEntrada.this, ActivityListaEntradas.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId() == R.id.menu_copiar) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("pass", entrada.getPassword());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(ActivityDetalleEntrada.this, "Password copiada", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}