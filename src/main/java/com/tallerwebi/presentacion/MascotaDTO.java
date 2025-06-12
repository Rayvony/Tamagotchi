package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;

import java.time.LocalDateTime;

public class MascotaDTO {

    private Long id;
    private String nombre;
    private Double energia;
    private Double higiene;
    private Double salud;
    private Double felicidad;
    private Double hambre;
    private Boolean estaVivo;
    private LocalDateTime ultimaSiesta;


    public void setEnergia(Double energia) {
        this.energia = energia;
    }

    public void setFelicidad(Double felicidad) { this.felicidad = felicidad; }

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.energia = 100.00;
        this.higiene=100.00;
        this.salud=100.00;
        this.felicidad=100.00;
        this.hambre= 100.00;
        this.estaVivo = true;
        this.ultimaSiesta = LocalDateTime.now() ;
    }

    public MascotaDTO(Mascota mascota) {
        this.id = mascota.getId();
        this.nombre = mascota.getNombre();
        this.energia = mascota.getEnergia();
        this.higiene = mascota.getHigiene();
        this.salud = mascota.getSalud();
        this.felicidad=mascota.getFelicidad();
        this.hambre= mascota.getHambre();
        this.estaVivo = mascota.getEstaVivo();
    }

    public Long getId(){return this.id;};

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return this.energia;
    }

    public Double getHigiene() {
        return higiene;
    }

    public Double getSalud() {
        return salud;
    }

    public Double getFelicidad() {
        return felicidad;
    }

    public Double getHambre() {
        return hambre;
    }

    public Boolean getEstaVivo() {
        return estaVivo;
    }

    public Mascota obtenerEntidad(){
        Mascota mascota = new Mascota();
        return this.obtenerEntidad(mascota);
    }

    public Mascota obtenerEntidad(Mascota mascota){
        if(this.id != null){
            mascota.setId(this.id);
        }
        mascota.setNombre(this.nombre);
        mascota.setEnergia(this.energia);
        mascota.setHigiene(this.higiene);
        mascota.setSalud(this.salud);
        mascota.setFelicidad(this.felicidad);
        mascota.setHambre(this.hambre);
        mascota.setEstaVivo(this.estaVivo);
        mascota.setUltimaSiesta(this.ultimaSiesta);
        return mascota;
    }

    public void setUltimaSiesta(LocalDateTime ultimaSiesta) {
        this.ultimaSiesta = ultimaSiesta;
    }
}
