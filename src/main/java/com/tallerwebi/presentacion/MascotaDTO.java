package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;

public class MascotaDTO {

    private String nombre;
    private Double energia;
    private Double energiaADescontarPorJuego;

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.energia = 100.00;
        this.energiaADescontarPorJuego = 30.00;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return this.energia;
    }

    public void jugar() {
        if(this.energia >= energiaADescontarPorJuego) {
            this.energia -= energiaADescontarPorJuego;
        } else{
            throw new EnergiaInsuficiente("No podés jugar, te falta energía");
        }
    }
}
