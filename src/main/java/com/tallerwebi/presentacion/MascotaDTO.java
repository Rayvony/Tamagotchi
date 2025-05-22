package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;

import java.time.LocalDateTime;

public class MascotaDTO {

    private String nombre;
    private double higiene;

    private LocalDateTime ultimaHigiene;
    private Double energia;
    private Double energiaADescontarPorJuego;

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.higiene = 100.0;
        this.ultimaHigiene = LocalDateTime.now();
        this.energia = 100.00;
        this.energiaADescontarPorJuego = 25.00;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getHigiene() {
        return higiene;
    }

    public void setHigiene(double higiene) {
        this.higiene = higiene;
    }

    public LocalDateTime getUltimaHigiene() {
        return ultimaHigiene;
    }

    public void setUltimaHigiene(LocalDateTime ultimaHigiene) {
        this.ultimaHigiene = ultimaHigiene;
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


