package com.alejion.pass_store.models;

import java.io.Serializable;

public class Entrada implements Serializable {

    private int id;
    private String nombre;
    private String usuario;
    private String password;

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setPassword(String password) { this.password = password; }
}
