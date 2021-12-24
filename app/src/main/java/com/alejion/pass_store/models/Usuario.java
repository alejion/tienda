package com.alejion.pass_store.models;

public class Usuario {
    private String nombre;
    private String password;

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre;}
    public String getPassword() { return password; }
}
