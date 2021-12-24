package com.alejion.pass_store.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 2;
    private static String DATABASE_NAME = "store.db";

    private String sql_create_entrada = "create table entrada (" +
            "id integer primary key autoincrement," +
            "nombre text," +
            "usuario text," +
            "password text);";

    private String sql_create_usuario = "create table usuario (" +
            "id integer primary key autoincrement," +
            "nombre text," +
            "password text);";

    private String sql_insert_usuario = "insert into usuario(nombre, password) values ('yo','123');";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sql_create_entrada);
        sqLiteDatabase.execSQL(sql_create_usuario);
        sqLiteDatabase.execSQL(sql_insert_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table entrada;");
        sqLiteDatabase.execSQL("drop table usuario;");
        onCreate(sqLiteDatabase);
    }
}

