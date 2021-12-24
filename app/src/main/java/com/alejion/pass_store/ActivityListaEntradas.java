package com.alejion.pass_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alejion.pass_store.adapters.ListaEntradaAdapter;
import com.alejion.pass_store.db.EntradaHandler;
import com.alejion.pass_store.models.Entrada;

import java.util.List;

public class ActivityListaEntradas extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EntradaHandler data_source;
    ListView lv_entradas;
    List<Entrada> entradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entradas);
        setTitle("Entradas de contraseñas");

        rellenar_entradas();
    }

    private void rellenar_entradas() {
        data_source = new EntradaHandler(this);
        data_source.open();

        if(data_source.isEmpty()) {
            Toast.makeText(ActivityListaEntradas.this, "No existen entradas aun", Toast.LENGTH_SHORT).show();
            data_source.close();
            return;
        }

        entradas = data_source.getEntradas();
        data_source.close();

        lv_entradas = findViewById(R.id.lv_lista_entradas);

        ArrayAdapter<Entrada> adapter = new ListaEntradaAdapter(this, R.layout.item_entradas, entradas);
        lv_entradas.setAdapter(adapter);
        lv_entradas.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.rellenar_entradas();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Entrada entrada = entradas.get(i);

        Intent intent = new Intent(ActivityListaEntradas.this, ActivityDetalleEntrada.class);
        intent.putExtra("entrada", entrada);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_agregar) {
            Intent intent = new Intent(ActivityListaEntradas.this, ActivityAgregarEntrada.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}