package com.alejion.pass_store.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alejion.pass_store.models.Entrada;

import java.util.ArrayList;
import java.util.List;

public class EntradaHandler {

    Database database;
    SQLiteDatabase db;

    public EntradaHandler(Context context) {
        database = new Database(context);
    }

    public void open() {
        db = database.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void insertarEntrada(Entrada entrada) {
        this.open();
        ContentValues values = new ContentValues();
        values.put("nombre", entrada.getNombre());
        values.put("usuario", entrada.getUsuario());
        values.put("password", entrada.getPassword());

        db.insert("entrada", null, values);

        this.close();
    }

    public void eliminarEntrada(int id) {

        this.open();
        db.execSQL("delete from entrada where id='" + id + "'");
        db.close();
    }

    public boolean isEmpty() {
        String query = "select * from entrada;";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() == 0) return true;
        return false;
    }

    public List<Entrada> getEntradas() {
        List<Entrada> entradas = new ArrayList<>();
        String query = "select * from entrada";
        Cursor cursor = db.rawQuery(query, null);

        if(!isEmpty()) {
            while (cursor.moveToNext()) {
                Entrada entrada = new Entrada();

                entrada.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                entrada.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                entrada.setUsuario(cursor.getString(cursor.getColumnIndexOrThrow("usuario")));
                entrada.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));

                entradas.add(entrada);
            }
        }

        cursor.close();

        return entradas;
    }
}
