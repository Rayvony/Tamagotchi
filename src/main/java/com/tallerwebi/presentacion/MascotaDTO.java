package com.tallerwebi.presentacion;

import java.time.LocalDateTime;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;

public class MascotaDTO {

    private String nombre;
    private Double energia;
    private Double energiaADescontarPorJuego;
    private Double hambre;
    private Double hambreADescontarPorAlimentar;
    private LocalDateTime ultimaAlimentacion;

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.energia = 100.00;
        this.energiaADescontarPorJuego = 25.00;
        this.hambre = 70.00;
        this.hambreADescontarPorAlimentar = 25.00; 
        this.ultimaAlimentacion = LocalDateTime.MIN; // 0000-01-01T00:00
    }

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return this.energia;
    }

     public void setEnergia(Double energia) {
        this.energia = energia;
    }

    public void jugar() {
        if(this.energia >= energiaADescontarPorJuego) {
            this.energia -= energiaADescontarPorJuego;
        } else{
            throw new EnergiaInsuficiente("No podés jugar, te falta energía");
        }
    }

    public MascotaDTO(Mascota mascota) {
        this.nombre = mascota.getNombre();
        this.hambre = mascota.getHambre();
    }

    // NO LE ESTOY PASANDO LOS VALORES ACUTALIZADOS // PODRIA RESOLVERLO PASANDO POR PARAMETRO EL OBJETO
    public Mascota aEntidad (){
        Mascota mascota = new Mascota(this.nombre);
        return mascota;
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

    public void alimentar() {
        this.hambre -= this.hambreADescontarPorAlimentar;
    }
   
}
