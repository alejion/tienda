package com.alejion.pass_store.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alejion.pass_store.R;
import com.alejion.pass_store.models.Entrada;

import java.util.List;

public class ListaEntradaAdapter extends ArrayAdapter<Entrada> {

    Context context;
    List<Entrada> objects;

    public ListaEntradaAdapter(@NonNull Context context, int resource, @NonNull List<Entrada> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Entrada entrada = objects.get(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_entradas, null);
        TextView tv_nombre = view.findViewById(R.id.tv_nombre);

        String nombre = entrada.getNombre();
        tv_nombre.setText(nombre);

        return view;
    }
}
