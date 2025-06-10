package com.tallerwebi.dominio.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.presentacion.MascotaDTO;

@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    //private static Long contadorId = 0L;
    private Long id;
    @Column(length = 20, nullable = false) // RESTRICCIONES EN BD
    private String nombre;
    private Double energia;
    private Double energiaADescontarPorJuego;
    private Double hambre;
    private LocalDateTime ultimaAlimentacion;
    private Double hambreADescontarPorAlimentar; 
     
   
    public Mascota(){}

    public Mascota(String nombre) {
        //this.id = ++contadorId;
        this.nombre = nombre;
        this.energia = 100.00;
        this.energiaADescontarPorJuego = 25.00;
        this.hambre = 70.00;
        this.hambreADescontarPorAlimentar = 25.00; 
        this.ultimaAlimentacion = LocalDateTime.of(1970, 1, 1, 0, 0); // Fecha segura
        // CREO QUE ME SALTA ERROR this.ultimaAlimentacion = LocalDateTime.MIN; // 0000-01-01T00:00
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

    public void alimentar() {
        this.hambre -= this.hambreADescontarPorAlimentar;
    }
}
