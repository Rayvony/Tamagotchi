package com.tallerwebi.dominio;

import java.time.LocalDateTime;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.presentacion.MascotaDTO;

public class Mascota {
    private String nombre;
    private Double energia;
    private Double energiaADescontarPorJuego;
    private Double hambre;
    private LocalDateTime ultimaAlimentacion;

    public Mascota(String nombre) {
        this.nombre = nombre;
        this.energia = 100.00;
        this.energiaADescontarPorJuego = 25.00;
        this.hambre = 70.00;
        this.ultimaAlimentacion = LocalDateTime.MIN; // 0000-01-01T00:00
    }

    // NO LE ESTOY PASANDO LOS VALORES ACUTALIZADOS // PODRIA RESOLVERLO PASANDO POR PARAMETRO EL OBJETO
    public MascotaDTO aDTO(){
        MascotaDTO mascotaDTO = new MascotaDTO(this.nombre);
        return mascotaDTO;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return this.energia;
    }

    public Double getHambre() {
        return this.hambre;
    }

    public void setHambre(Double hambre) {
        this.hambre = hambre;
    }

     public void setUltimaAlimentacion(LocalDateTime now) {
        this.ultimaAlimentacion = now;
    }
    public LocalDateTime getUltimaAlimentacion() {
        return this.ultimaAlimentacion;
    }

    public void jugar() {
        if(this.energia >= energiaADescontarPorJuego) {
            this.energia -= energiaADescontarPorJuego;
        } else{
            throw new EnergiaInsuficiente("No podés jugar, te falta energía");
        }
    }
}
