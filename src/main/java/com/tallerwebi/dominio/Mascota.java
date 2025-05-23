package com.tallerwebi.dominio;

public class Mascota {
    private String nombre;
    private Integer hambre;

    public Mascota(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setHambre(Integer hambre) {
        this.hambre = hambre;
    }

    public Integer getHambre() {
        return this.hambre;
    }
}
