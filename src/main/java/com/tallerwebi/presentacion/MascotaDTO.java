package com.tallerwebi.presentacion;

import java.time.LocalDateTime;

public class MascotaDTO {

    private String nombre;
    private double higiene;

    private LocalDateTime ultimaHigiene;

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.higiene = 100.0;
        this.ultimaHigiene = LocalDateTime.now();
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
}


