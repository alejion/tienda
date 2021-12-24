package com.alejion.pass_store.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alejion.pass_store.models.Usuario;

public class UsuarioHandler {
    Database database;
    SQLiteDatabase db;

    public UsuarioHandler(Context context) {
        database = new Database(context);
    }

    public void open() {
        db = database.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public Usuario get_usuario() {

        this.open();
        String query = "select * from usuario where nombre='yo';";
        Cursor cursor = db.rawQuery(query, null);
        Usuario user = new Usuario();

        // solo hay 1 usuario
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                user.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            }
        }

        cursor.close();
        this.close();
        return user;
    }
}
