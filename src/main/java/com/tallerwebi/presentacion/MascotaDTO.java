package com.tallerwebi.presentacion;

public class MascotaDTO {

    private String nombre;
    private Integer hambre;

    public MascotaDTO(String nombre) {
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
